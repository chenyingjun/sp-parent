package com.chenyingjun.sp.core.dto;

import com.chenyingjun.sp.common.validator.NotXss;
import com.chenyingjun.sp.core.entity.SystemUser;
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
    private String account;

    @NotXss(maxLength = 20)
    private String name;

    @NotXss(maxLength = 20)
    private String nickName;

    @NotXss(maxLength = 11)
    private String phone;

    /** 性别,0.未知;1.男;2.女 */
    @Pattern(regexp = "[0,1,2]{1}", message = "{data.input}")
    private String sex;

    private String passWord;

    @NotXss(maxLength = 45)
    private String email;
;
    private Integer failNum;

    private Integer userStatus;

    private List<String> roleIds;

    @NotXss(maxLength = 1000)
    private String remark;

    private Date updateTime;

    public SystemUser thisToSystemUser() {
        SystemUser user = new SystemUser();
        user.setAccount(this.account);
        user.setName(this.name);
        user.setNickName(this.nickName);
        user.setPhone(this.phone);
        user.setSex(this.sex);
        user.setEmail(this.email);
        user.setFailNum(this.failNum);
        user.setStatus(this.userStatus);
        user.setRemark(this.remark);
        user.setUpdateTime(new Date());
        return user;
    }

}
