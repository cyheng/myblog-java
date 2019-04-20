package com.doraro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doraro.model.entity.BaseModel;
import com.doraro.model.entity.SysResource;
import com.doraro.service.ISysResourceService;
import com.doraro.utils.EncryptUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScanService {
    private final RequestMappingHandlerMapping handlerMapping;
    private final String[] emptyArray = new String[]{""};
    private static final RequestMethod[] EMPTY_METHOD = new RequestMethod[0];
    private final ISysResourceService sysResourceService;

    @Autowired
    public ScanService(RequestMappingHandlerMapping handlerMapping, ISysResourceService sysResourceService) {
        this.handlerMapping = handlerMapping;
        this.sysResourceService = sysResourceService;
    }

    @PostConstruct
    private void init() {
        scanResource();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean scanResource() {
        final List<SysResource> dbData = sysResourceService.list();
        final List<SysResource> scanData = handlerMapping.getHandlerMethods().values()
                .stream()
                .map(this::getResources)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return compareAndSave(dbData, scanData);
    }


    private boolean compareAndSave(List<SysResource> dbData, List<SysResource> scanData) {
        final Map<String, Long> map = dbData.stream()
                .collect(Collectors.toMap(it -> it.getMethod() + ":" + it.getMapping(), BaseModel::getId));
        for (SysResource resource : scanData) {
            final String key = resource.getMethod() + ":" + resource.getMapping();
            final Long id = map.get(key);
            if (id != null) {
                resource.setId(id);
                map.remove(key);
            }
        }
        //删除旧数据
        if (!map.values().isEmpty()) {
            sysResourceService.removeByIds(map.values());
        }
        //更新新数据
        return sysResourceService.saveOrUpdateBatch(scanData);
    }

    private List<SysResource> getResources(HandlerMethod handlerMethod) {
        final RequestMapping controller = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
        final RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
        if (controller == null && methodAnnotation == null) {
            return Collections.emptyList();
        }
        final String[] basePaths = getPath(controller);
        final String[] paths = getPath(methodAnnotation);
        final RequestMethod[] requestMethods = Objects.nonNull(methodAnnotation) ? methodAnnotation.method() : EMPTY_METHOD;
        Set<String> mappings = new HashSet<>(1);
        for (String basePath : basePaths) {
            for (String path : paths) {
                mappings.add(basePath + path);
            }
        }
        List<SysResource> res = new ArrayList<>();
        final ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
        final RequiresPermissions perm = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        String permStr = getPerm(handlerMethod, perm);
        for (RequestMethod method : requestMethods) {
            for (String path : mappings) {
                final SysResource sysResource = new SysResource();
                sysResource.setResourceName(Objects.nonNull(apiOperation) ? apiOperation.value() : "未命名资源");
                sysResource.setMapping(path);
                sysResource.setMethod(method.name());
                sysResource.setPerm(permStr);
                res.add(sysResource);
            }
        }
        return res;
    }


    private String getPerm(HandlerMethod handlerMethod, RequiresPermissions perm) {
        String permStr = "";
        if (perm != null) {
            final String[] perms = perm.value();
            if (perms.length != 1) {
                throw new BeanCreationException(handlerMethod.getShortLogMessage() + "仅支持长度为1的权限");
            }
            permStr = perms[0];
        }
        return permStr;
    }

    private String[] getPath(RequestMapping controller) {
        final String[] paths = Objects.nonNull(controller) ? controller.value() : emptyArray;
        return ArrayUtils.isEmpty(paths) ? emptyArray : paths;
    }
}
