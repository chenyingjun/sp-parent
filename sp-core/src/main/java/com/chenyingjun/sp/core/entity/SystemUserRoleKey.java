package com.chenyingjun.sp.core.entity;

import lombok.Data;

/**
 * 用户-角色
 */
@Data
public class SystemUserRoleKey {
    /**
     * 用户主键
     */
    private String userId;

    /**
     * 角色主键
     */
    private String roleId;

    /**
     * 构建函数
     * @param userId 用户主键
     */
    public SystemUserRoleKey(String userId) {
        this.userId = userId;
    }

    /**
     * 构建函数
     * @param userId 用户主键
     * @param roleId 角色主键
     */
    public SystemUserRoleKey(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}