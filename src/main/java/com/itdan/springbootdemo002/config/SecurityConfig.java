package com.itdan.springbootdemo002.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdan.springbootdemo002.service.User1Service;
import com.itdan.springbootdemo002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private User1Service user1Service;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    /**
     * 自定义登录用户
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("dan")
//                .password("$2a$10$X785GJSjre4AphXRZ19CQOFyaQYA1j00JynQ3hT3DOMyP7Gq/Xlqq")
//                .roles("admin")
//                .and()
//                .withUser("xiaolan")
//                .password("$2a$10$xa.7ir4T6476IH7g5WPlHOtINsOBPlMYthPCfHSkEInosegB8z/sG")
//                .roles("user");

        //获取数据中的用户
        auth.userDetailsService(user1Service);

    }

    /**
     * 自定义登入路径和权限
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //访问admin下的页面，必须具备admin权限
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("admin")
                .mvcMatchers("/dds/**").hasRole("dds")
                //访问user下的页面，必须具备admin,user权限
                // .mvcMatchers("/user/**").hasAnyRole("admin", "user")
                .mvcMatchers("/user/**").access("hasAnyRole('admin','user')")
                //访问下面的页面，都可以访问
                .mvcMatchers().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")//配置登入地址
               // .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    //登入成功后，返回想要的json字符串
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        Authentication authentication) throws IOException, ServletException {
                          //authentication里面存储着登入成功的信息
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter printWriter=res.getWriter();
                        Map<String,Object> map=new HashMap<>();
                        map.put("status","200");
                        map.put("msg",authentication.getPrincipal());
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    //失败异常处理
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter printWriter=res.getWriter();
                        Map<String,Object> map=new HashMap<>();
                        map.put("status","4001");
                                if(e instanceof LockedException){
                                     map.put("msg","用户被锁定，登入失败");
                                }else if(e instanceof BadCredentialsException){
                                     map.put("msg","用户名或登入密码错误");
                                }else if(e instanceof DisabledException){
                                    map.put("msg","账户被禁用");
                                }else if(e instanceof AccountExpiredException){
                                    map.put("msg","账户过期");
                                }else if(e instanceof CredentialsExpiredException){
                                    map.put("msg","密码过期");
                                }else {
                                    map.put("msg","登入失败");
                                }
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .permitAll()//跟登录有关的接口都让过
                .and()
                .logout()//退出登入
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    //退出登入成功信息
                    @Override
                    public void onLogoutSuccess(HttpServletRequest rep,
                                                HttpServletResponse res,
                                                Authentication authentication) throws IOException, ServletException {
                        //authentication里面存储着登入成功的信息
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter printWriter=res.getWriter();
                        Map<String,Object> map=new HashMap<>();
                        map.put("status","200");
                        map.put("msg","注销用户成功");
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .and().csrf().disable();//关闭csrf工具
    }




}
