package com.chenyingjun.sp.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 基础信息
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
@Data
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    //0:禁止登录
    public static final Long STATUS_0 = new Long(0);
    //1:有效
    public static final Long STATUS_1 = new Long(1);

    //0:已删除
    public static final Long DEL_FLAG_0 = new Long(0);
    //1:可用
    public static final Long DEL_FLAG_1 = new Long(1);
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="UUID")
//    @GeneratedValue(strategy=GenerationType.IDENTITY[,generator="Mysql"])
    private String id;

    /**
     * 是否删除   0.已删除；1.可用
     */
    private int delFlag;
}
