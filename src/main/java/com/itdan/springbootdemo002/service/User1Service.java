package com.itdan.springbootdemo002.service;


import com.itdan.springbootdemo002.mapper.UserMapper;
import com.itdan.springbootdemo002.pojo.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class User1Service implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 检测登入用户是否存在
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User1 user1= userMapper.loadUserByUsername(username);
         if(null==user1){
             throw new UsernameNotFoundException("用户不存在");
         }
         //添加用户权限
         user1.setRoleList(userMapper.getRoleById(user1.getId()));

        return user1;
    }
}
