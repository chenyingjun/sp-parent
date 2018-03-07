package com.chenyingjun.sp.core.entity;

import lombok.Data;

import java.util.Date;


@Data
public class SystemUser extends BaseEntity {

    private String name;

    private String account;

    private String nickName;

    private String passWord;

    private String email;

    private String phone;

    private String sex;

    private Integer failNum;

    private Integer status;

    private Date loginTime;

    private String loginIp;

    private Date lastTime;

    private String lastIp;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public SystemUser() {
    }

    public SystemUser(SystemUser user) {
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", failNum=" + failNum +
                ", status=" + status +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                ", lastTime=" + lastTime +
                ", lastIp='" + lastIp + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}