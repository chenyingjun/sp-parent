package com.chenyingjun.sp.core.mapper;

import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.utils.MyMapper;

import java.util.Map;

public interface SystemUserMapper extends MyMapper<SystemUser> {

    SystemUser login(Map<String, Object> map);
}