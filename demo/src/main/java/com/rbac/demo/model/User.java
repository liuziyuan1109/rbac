package com.rbac.demo.model;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String status;
}
