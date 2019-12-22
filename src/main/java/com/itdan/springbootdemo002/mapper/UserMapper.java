package com.itdan.springbootdemo002.mapper;

import com.itdan.springbootdemo002.pojo.Role;
import com.itdan.springbootdemo002.pojo.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User1 loadUserByUsername(@Param(value = "username") String username);

    @Select("select * from role where id in (select rid from user_role where uid=#{id}) ")
    List<Role> getRoleById(@Param(value = "id") Integer id);
}
