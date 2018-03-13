package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.core.dto.SystemUserPageFind;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.mapper.SystemUserMapper;
import com.chenyingjun.sp.core.vo.SystemUserPageVo;
import com.chenyingjun.sp.core.vo.SystemUserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务
 *
 * @author chenyingjun
 * @version 2017年12月21日
 * @since 1.0
 */
@Service
public class SystemUserService extends BaseService<SystemUser>{

    /**
     * 用户 dao
     */
    @Autowired
    private SystemUserMapper systemUserMapper;

    public SystemUser login(String account, String passWord) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        map.put("passWord", passWord);
        SystemUser user = systemUserMapper.login(map);
        user.setPassWord(null);
        return user;
    }

    /**
     * 分页查询
     *
     * @param find 查询信息
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @return 组织列表
     */
    public PageInfo<SystemUser> page(SystemUserPageFind find, int pageNum, int pageSize) {
        List<SystemUserPageVo> list = systemUserMapper.page(find);
        return this.basePageByExample(list, pageNum, pageSize);
    }

    /**
     * 分页查询
     *
     * @param id 查询信息主键
     * @return 组织列表
     */
    public SystemUserVo info(String id) {
        SystemUserVo user = systemUserMapper.info(id);
        user.setPassWord(null);
        return user;
    }
}
