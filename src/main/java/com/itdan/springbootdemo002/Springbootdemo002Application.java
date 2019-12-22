package com.itdan.springbootdemo002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching//添加缓存
public class Springbootdemo002Application {


    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo002Application.class, args);
    }

}
