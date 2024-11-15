package com.rbac.demo.mapper;

import com.rbac.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(@Param("userId") Integer userId);

    int insertUser(User user);

    // 获取用户的权限列表
    List<String> getUserPermissions(@Param("userId") Integer userId);

    // 根据用户名查找用户
    User findByUsername(@Param("username") String username);

    // 其他需要的方法
}
