package com.rbac.demo.service;

import com.rbac.demo.mapper.MenuMapper;
import com.rbac.demo.mapper.UserMapper;
import com.rbac.demo.model.Menu;
import com.rbac.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Menu> getMenusByUsername(String username) {
        User user = userMapper.findByUsername(username);
        List<Menu> menus = menuMapper.getMenusByUserId(user.getUserId());
        return buildMenuTree(menus);
    }

    private List<Menu> buildMenuTree(List<Menu> menus) {
        // 创建一个 Map，以便快速找到菜单的子节点列表
        Map<Integer, Menu> menuMap = new HashMap<>();
        List<Menu> rootMenus = new ArrayList<>();

        // 将所有菜单存入 Map 中，以 ID 为键
        for (Menu menu : menus) {
            menu.setChildren(new ArrayList<>()); // 初始化子菜单列表
            menuMap.put(menu.getMenuId(), menu);
        }

        // 遍历菜单列表，构建父子关系
        for (Menu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                // 如果是根菜单，直接加入 rootMenus
                rootMenus.add(menu);
            } else {
                // 如果是子菜单，找到父菜单并加入父菜单的子菜单列表
                Menu parent = menuMap.get(menu.getParentId());
                if (parent != null) {
                    parent.getChildren().add(menu);
                }
            }
        }

        return rootMenus; // 返回根菜单列表，包含完整的菜单树
    }
}

