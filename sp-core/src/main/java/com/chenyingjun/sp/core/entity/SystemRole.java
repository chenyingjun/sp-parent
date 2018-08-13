package com.chenyingjun.sp.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户角色
 */
@Data
public class SystemRole extends BaseEntity {

    /**
     * 角色编号
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 状态  1.可用;0.禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}