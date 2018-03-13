package com.chenyingjun.sp.core.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SystemRole extends BaseEntity {

    private String code;

    private String name;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}