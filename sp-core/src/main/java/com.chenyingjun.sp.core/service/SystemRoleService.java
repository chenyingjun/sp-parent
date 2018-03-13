package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.core.dto.SystemRolePageFind;
import com.chenyingjun.sp.core.entity.SystemRole;
import com.chenyingjun.sp.core.mapper.SystemRoleMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务
 *
 * @author chenyingjun
 * @version 2017年12月21日
 * @since 1.0
 */
@Service
public class SystemRoleService extends BaseService<SystemRole>{

    /**
     * 用户 dao
     */
    @Autowired
    private SystemRoleMapper systemRoleMapper;


    /**
     * 分页查询
     *
     * @param find 查询信息
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @return 组织列表
     */
    public PageInfo<SystemRole> page(SystemRolePageFind find, int pageNum, int pageSize) {
        List<SystemRole> list = systemRoleMapper.page(find);
        return this.basePageByExample(list, pageNum, pageSize);
    }

    /**
     * 根据主键查询角色信息
     *
     * @param id 查询信息主键
     * @return 组织列表
     */
    public SystemRole info(String id) {
        SystemRole role = systemRoleMapper.selectByPrimaryKey(id);
        return role;
    }
}
