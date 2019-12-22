package com.itdan.springbootdemo002.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/test01")
    public String test01(){
        return "security--->hello";
    }

    @GetMapping("/admin/test")
    public String admin(){
        return "admin界面";
    }

    @GetMapping("/user/test")
    public String user(){
        return "user界面";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello devtools";
    }
}
