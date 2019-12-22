package com.itdan.springbootdemo002.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MethodService {

    @PreAuthorize("hasRole('admin')")
    public String  test1(){
         return "admin";
    }

    @Secured("ROLE_user")
    public String test2(){
        return "user";
    }

    @PreAuthorize("hasAnyRole('admin','user')")
    public String test3(){
        return "user/admin";
    }
}
