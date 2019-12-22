package com.itdan.springbootdemo002.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * 自定义rest配置
 */
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
           config.setDefaultPageSize(2)
                   .setBasePath("/dan");
    }
}
