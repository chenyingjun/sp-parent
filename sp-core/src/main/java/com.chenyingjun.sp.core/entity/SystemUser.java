package com.chenyingjun.sp.core.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户信息
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
@Data
public class SystemUser  implements Serializable {

    private static final long serialVersionUID = 1L;

    //0:禁止登录
    public static final Long _0 = new Long(0);
    //1:有效
    public static final Long _1 = new Long(1);

    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String username;

    /**
     * 登录名
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户名评音
     */
    private String pinyin;

    /**
     * 用户名评音简写
     */
    private String pinyinSim;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    public SystemUser() {
    }

    public SystemUser(String username, String password) {
        this.username = username;
        this.password = password;

        this.id = "1";
        this.email = "2";
        this.createDate = new Date();
        this.lastLoginTime = new Date();
    }
    public SystemUser(SystemUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createDate = user.getCreateDate();
        this.lastLoginTime = user.getLastLoginTime();
    }

}