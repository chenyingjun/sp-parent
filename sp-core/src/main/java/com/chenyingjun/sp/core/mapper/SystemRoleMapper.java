package com.chenyingjun.sp.core.mapper;

import com.chenyingjun.sp.core.dto.SystemRolePageFind;
import com.chenyingjun.sp.core.entity.SystemRole;
import com.chenyingjun.sp.core.utils.MyMapper;

import java.util.List;

public interface SystemRoleMapper extends MyMapper<SystemRole> {

    List<SystemRole> page(SystemRolePageFind find);

}