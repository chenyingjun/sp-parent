package com.chenyingjun.sp.core.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class SystemUserPageFind implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String nickName;

    private String phone;

    private String sex;

    private Integer failNum;

    private Integer userStatus;

    @Pattern(regexp = "([1-2]{1}[0-9]{3})((1[0-2]{1})|(0[1-9]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))",
            message = "{data.input}")
    private String startTime;

    @Pattern(regexp = "([1-2]{1}[0-9]{3})((1[0-2]{1})|(0[1-9]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))",
            message = "{data.input}")
    private String endTime;
}
