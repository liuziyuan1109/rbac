package com.rbac.demo.controller;

import com.rbac.demo.model.LoginRequest;
import com.rbac.demo.model.TokenResponse;
import com.rbac.demo.model.User;
import com.rbac.demo.model.UsernamePermissions;
import com.rbac.demo.service.UserService;
import com.rbac.demo.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * 用户登录接口
     *
     * @param loginRequest 包含用户名和密码的请求对象
     * @return 包含 JWT Token 的响应
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String token = jwtTokenUtil.generateAccessToken(user.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @param request HTTP 请求对象，用于获取用户的 JWT Token
     * @return 当前用户的详细信息
     */
    @GetMapping("/me")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // 去掉 "Bearer "
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * 用户退出接口，清除 Session
     *
     * @param request HTTP 请求对象，用于清除用户的 Session
     * @return 退出成功信息
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByName(username);

        List<String> permissions = userService.getUserPermissions(user.getUserId());
        UsernamePermissions username_permissions = new UsernamePermissions();
        username_permissions.setUsername(username);
        username_permissions.setPermissions(permissions);
        return ResponseEntity.ok(username_permissions);
    }
}

