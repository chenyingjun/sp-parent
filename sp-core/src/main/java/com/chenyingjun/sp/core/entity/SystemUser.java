package com.chenyingjun.sp.core.entity;

import com.chenyingjun.sp.common.enums.StatusType;
import lombok.Data;

import java.util.Date;


@Data
public class SystemUser extends BaseEntity {

    //0:禁止登录
    public static final int STATUS_0 = Integer.parseInt(StatusType.STATUS_0.getKey());
    //1:有效
    public static final int STATUS_1 = Integer.parseInt(StatusType.STATUS_1.getKey());

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