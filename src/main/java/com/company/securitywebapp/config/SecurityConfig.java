package com.company.securitywebapp.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    //String: Role
    //List <String>: urlPatterns
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();


    static {
        init();
    }

    private static void init(){
    // Конфигурация для роли "EMPLOYEE".
        List<String> urlPatters1 = new ArrayList<String>();

        urlPatters1.add("/userInfo");
        urlPatters1.add("/employeeTask");

        mapConfig.put(ROLE_EMPLOYEE, urlPatters1);

     // Конфигурация для роли "MANAGER".
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/managerTask");

        mapConfig.put(ROLE_MANAGER, urlPatterns2);
    }

    public static Set<String> getAllAppRoles(){
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role){
        return mapConfig.get(role);
    }
}
