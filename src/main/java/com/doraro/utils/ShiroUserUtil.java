package com.doraro.utils;

import org.apache.shiro.SecurityUtils;

public class ShiroUserUtil {

    public static Long getCurrentUserId() {
        return (Long) SecurityUtils.getSubject().getPrincipal();
    }


}
