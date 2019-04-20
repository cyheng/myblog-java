package com.doraro.aop;

import com.doraro.model.entity.SysLogModel;
import com.doraro.model.entity.SysUser;
import com.doraro.redis.RedisUtils;
import com.doraro.service.ISysLogService;
import com.doraro.service.ISysUserService;
import com.doraro.utils.HttpContextUtils;
import com.doraro.utils.ShiroUserUtil;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
public class SysLogAop {
    private final ISysLogService logService;

    @Autowired
    public SysLogAop(ISysLogService logService) {
        this.logService = logService;
    }


    @Pointcut("@annotation(SysLog)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handleSysLog(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = pjp.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(pjp, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        final SysLogModel sysLog = new SysLogModel();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(request.getRemoteAddr());

        //用户id
        Long userId = ShiroUserUtil.getCurrentUserId();
        sysLog.setUid(userId);
        sysLog.setTime(time);
        sysLog.setCreateTime(LocalDateTime.now());
        //保存系统日志
        logService.save(sysLog);
    }
}
