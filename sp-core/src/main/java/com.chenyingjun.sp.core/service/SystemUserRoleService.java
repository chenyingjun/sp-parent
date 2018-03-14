package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.core.entity.SystemRole;
import com.chenyingjun.sp.core.entity.SystemUserRoleKey;
import com.chenyingjun.sp.core.mapper.SystemUserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 用户角色 服务
 *
 * @author chenyingjun
 * @version 2017年12月21日
 * @since 1.0
 */
@Service
public class SystemUserRoleService extends BaseService<SystemRole>{

    /**
     * 用户角色 dao
     */
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    public String roleIdsByUserId(String userId) {
        List<SystemUserRoleKey> list = listByUserId(userId);
        if (list == null || list.size() <= 0) {
            return null;
        }

        StringBuffer roleIds = new StringBuffer(list.size() * 45);
        for (SystemUserRoleKey key: list) {
            roleIds = roleIds.append(",").append(key.getRoleId());
        }
        if (roleIds.length() > 0) {
            return roleIds.toString().substring(1);
        } else {
            return roleIds.toString();
        }
    }

    public List<SystemUserRoleKey> listByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return systemUserRoleMapper.listByUserId(userId);
    }
}
