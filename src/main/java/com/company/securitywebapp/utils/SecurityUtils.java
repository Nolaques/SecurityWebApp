package com.company.securitywebapp.utils;

import com.company.securitywebapp.config.SecurityConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    // checking if this 'request' requires enter to system or not.
    public static boolean isSecurityPage(HttpServletRequest request){
        String urlPattern = UrlPatternUtils.getUrlPattern(request);

        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role:roles
             ) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)){
                return true;
            }
        }
        return false;
    }

    // checking if this  'request' requires appropriate role?
    public static boolean hasPermission(HttpServletRequest request){
        String urlPattern = UrlPatternUtils.getUrlPattern(request);

        Set<String> allRoles = SecurityConfig.getAllAppRoles();

        for (String role: allRoles
             ) {
            if (!request.isUserInRole(role)){
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)){
                return true;
            }
        }
        return false;
    }
}
