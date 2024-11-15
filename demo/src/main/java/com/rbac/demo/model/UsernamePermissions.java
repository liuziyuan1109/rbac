package com.rbac.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class UsernamePermissions {
    String username;
    List<String> permissions;
}
