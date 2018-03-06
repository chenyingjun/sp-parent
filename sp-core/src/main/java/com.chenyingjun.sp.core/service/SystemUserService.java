package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        return systemUserMapper.login(map);
    }
}
