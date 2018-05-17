package com.yevhenii.kpi.readmore.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

//    get username from security context
    public static String getUsername() {

        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
