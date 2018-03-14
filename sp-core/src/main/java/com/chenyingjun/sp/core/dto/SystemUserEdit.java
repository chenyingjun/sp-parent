package com.chenyingjun.sp.core.dto;

import com.chenyingjun.sp.common.validator.NotXss;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SystemUserEdit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    @NotXss(maxLength = 20)
    private String name;

    @NotXss(maxLength = 20)
    private String nickName;

    @NotXss(maxLength = 11)
    private String phone;

    /** 性别,1.男;2.女 */
    @Pattern(regexp = "[1,2]{1}", message = "{data.input}")
    private String sex;

    @NotXss(maxLength = 20)
    private String passWord;

    @NotXss(maxLength = 45)
    private String email;

    private Integer failNum;

    private Integer userStatus;

    private List<String> roleIds;

    @NotXss(maxLength = 1000)
    private String remark;

    private Date updateTime;

}
