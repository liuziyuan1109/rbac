package com.rbac.demo.service;

import com.rbac.demo.mapper.UserMapper;
import com.rbac.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    public User getUserByName(String userName) { return userMapper.findByUsername(userName); }

    public List<String> getUserPermissions(Integer userID) { return userMapper.getUserPermissions(userID); }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public int createUser(User user) {
        return userMapper.insertUser(user);
    }

    // 不使用加密，直接明文比对
    public boolean validatePassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    // 其他业务逻辑方法
}

