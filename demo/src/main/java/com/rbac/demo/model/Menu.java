package com.rbac.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private Integer permissionId;
    private String url;
    private Integer order;
    private List<Menu> children;
}
