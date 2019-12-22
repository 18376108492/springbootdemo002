package com.itdan.springbootdemo002.service;

import com.itdan.springbootdemo002.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Cacheable(cacheNames = "c1")
    //默认情况下缓存的结构为（key,value）,c1为key,查询的结果为value,如果查询的key一样就直接给你返回value
    public User getById(Integer id){
        System.out.println("调用getById");
        User user=new User();
        user.setId(id);
        return user;
    }
    @Cacheable(cacheNames = "c1")//前后两次调用，如果name不同，那么不会缓存
    public User getByIddemo02(Integer id,String name){
        System.out.println("调用getById");
        User user=new User();
        user.setId(id);
        return user;
    }
    @Cacheable(cacheNames = "c1",key = "#id")//前后两次调用，如果name不同，也一样会缓存
    public User getByIddemo03(Integer id,String name){
        System.out.println("调用getById");
        User user=new User();
        user.setId(id);
        return user;
    }

    @CacheEvict(cacheNames = "c1")//删除数据库中的资源时，也讲缓存的数据删除掉
    public void deleteById(Integer id){
        System.out.println("删除用户");
    }

    @CachePut(cacheNames = "c1")
    public void update(Integer id){
        User user= getById(id);
        user.setName("小明");
        user.setAddr("addr");
        System.out.println("更新用户");
        System.out.println(user.toString());
    }

}
