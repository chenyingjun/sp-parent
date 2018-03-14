package com.chenyingjun.sp.core.mapper;

import com.chenyingjun.sp.core.dto.SystemUserEdit;
import com.chenyingjun.sp.core.dto.SystemUserPageFind;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.utils.MyMapper;
import com.chenyingjun.sp.core.vo.SystemUserPageVo;
import com.chenyingjun.sp.core.vo.SystemUserVo;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper extends MyMapper<SystemUser> {

    SystemUser login(Map<String, Object> map);

    List<SystemUserPageVo> page(SystemUserPageFind find);

    SystemUserVo info(String id);

    int edit(SystemUserEdit edit);
}