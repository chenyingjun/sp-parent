package com.chenyingjun.sp.core.mapper;

import com.chenyingjun.sp.core.entity.SystemUserRoleExample;
import com.chenyingjun.sp.core.entity.SystemUserRoleKey;
import com.chenyingjun.sp.core.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserRoleMapper {
    int deleteByPrimaryKey(SystemUserRoleKey key);

    int insert(SystemUserRoleKey record);

    int insertSelective(SystemUserRoleKey record);

    int updateByExampleSelective(@Param("record") SystemUserRoleKey record, @Param("example") SystemUserRoleExample example);

    int updateByExample(@Param("record") SystemUserRoleKey record, @Param("example") SystemUserRoleExample example);

    List<SystemUserRoleKey> listByUserId(String userId);

    int deleteByUserId(String userId);
}