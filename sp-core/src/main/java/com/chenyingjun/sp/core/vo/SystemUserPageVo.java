package com.chenyingjun.sp.core.vo;

import com.chenyingjun.sp.common.enums.SexType;
import com.chenyingjun.sp.common.enums.StatusType;
import com.chenyingjun.sp.common.utils.DateUtils;
import com.chenyingjun.sp.common.utils.LoggerUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;

@Data
public class SystemUserPageVo {

    private String id;

    private String name;

    private String nickName;

    private String email;

    private String phone;

    private String sex;

    private String failNum;

    private String status;

    private String createTime;

    private String updateTime;

    public void setSex(String sex) {
        this.sex = SexType.getName(sex);
    }

    public void setStatus(String status) {
        this.status = StatusType.getName(status);

    }

    public void setCreateTime(String createTime) {
        if (StringUtils.isNotBlank(createTime)) {
            try {
                this.createTime = DateUtils.parseToFormatStr(createTime, DateUtils.FULL_DATE_STR);
            } catch (ParseException e) {
                LoggerUtils.error(getClass(), "时间格式化失败");
                this.createTime = createTime;
            }
        }
    }

    public void setUpdateTime(String updateTime) {
        if (StringUtils.isNotBlank(updateTime)) {
            try {
                this.updateTime = DateUtils.parseToFormatStr(updateTime, DateUtils.SIMPLE_DATE_STR);
            } catch (ParseException e) {
                LoggerUtils.error(getClass(), "时间格式化失败");
                this.updateTime = updateTime;
            }
        }
    }
}
