package com.rbac.demo.controller;

import com.rbac.demo.model.Menu;
import com.rbac.demo.service.MenuService;
import com.rbac.demo.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @GetMapping("/user")
    public List<Menu> getUserMenus(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        return menuService.getMenusByUsername(username);
    }
}
