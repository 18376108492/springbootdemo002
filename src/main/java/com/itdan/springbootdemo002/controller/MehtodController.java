package com.itdan.springbootdemo002.controller;


import com.itdan.springbootdemo002.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MehtodController {

    @Autowired
    private MethodService methodService;

    @GetMapping("/test1")
    public String  test1(){
       return methodService.test1();
    }

    @GetMapping("/test2")
    public String test2(){
        return methodService.test2();
    }

    @GetMapping("/test3")
    public String test3(){
        return methodService.test3();
    }

}
