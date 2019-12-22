package com.itdan.springbootdemo002;

import com.itdan.springbootdemo002.pojo.User;
import com.itdan.springbootdemo002.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class Springbootdemo002ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testGetById() throws Exception{
        //通过调用两次去测试缓存
        User user= userService.getById(1);
        User user1= userService.getById(1);
        System.out.println(user);
    }

    @Test
    public void testGetByIddemo02() throws Exception{
        //通过调用两次去测试缓存
        User user= userService.getByIddemo02(1,"aa");
        User user1= userService.getByIddemo02(1,"bb");
        System.out.println(user);
    }

    @Test
    public void testGetByIddemo03() throws Exception{
        //通过调用两次去测试缓存
        User user= userService.getByIddemo03(1,"aa");
        User user1= userService.getByIddemo03(1,"bb");
        System.out.println(user);
    }

    @Test
    public void testPassword() throws Exception{
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        for (int i = 0; i <10 ; i++) {
            System.out.println(passwordEncoder.encode("123"));
        }
    }


}
