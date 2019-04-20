package com.doraro.shiro;

import com.doraro.filter.TokenFilter;
import com.doraro.shiro.OauthRealm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    private ObjectMapper mapper;


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition filterMap = new DefaultShiroFilterChainDefinition();
        filterMap.addPathDefinition("/webjars/**", "anon");
        filterMap.addPathDefinition("/druid/**", "anon");
        filterMap.addPathDefinition("/sys/login", "anon");
        filterMap.addPathDefinition("/swagger/**", "anon");
        filterMap.addPathDefinition("/v2/api-docs", "anon");
        filterMap.addPathDefinition("/swagger-ui.html", "anon");
        filterMap.addPathDefinition("/swagger-resources/**", "anon");
        filterMap.addPathDefinition("/captcha.jpg", "anon");
        filterMap.addPathDefinition("/api/admin/**", "oauth2");
        filterMap.addPathDefinition("/**", "anon");
        return filterMap;
    }


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager sessionManager, ShiroFilterChainDefinition definition) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(sessionManager);
        //oauth过滤
        Map<String, Filter> filters = new HashMap<>(1);
        final TokenFilter filter = new TokenFilter();
        filter.setMapper(mapper);
        filters.put("oauth2", filter);
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitionMap(definition.getFilterChainMap());
        return shiroFilter;
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }


}
