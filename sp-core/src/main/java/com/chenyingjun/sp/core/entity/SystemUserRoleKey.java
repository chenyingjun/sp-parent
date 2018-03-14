package com.chenyingjun.sp.core.entity;

import lombok.Data;

@Data
public class SystemUserRoleKey {
    private String userId;

    private String roleId;

    public SystemUserRoleKey(String userId) {
        this.userId = userId;
    }

    public SystemUserRoleKey(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}