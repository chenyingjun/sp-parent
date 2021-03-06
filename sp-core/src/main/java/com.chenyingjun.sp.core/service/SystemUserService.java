package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.common.bean.ExceptionType;
import com.chenyingjun.sp.common.constant.CommonConsts;
import com.chenyingjun.sp.common.exception.BusinessException;
import com.chenyingjun.sp.common.utils.GlobalUtils;
import com.chenyingjun.sp.common.utils.LoggerUtils;
import com.chenyingjun.sp.common.utils.SpringContextUtil;
import com.chenyingjun.sp.core.dto.SystemUserEdit;
import com.chenyingjun.sp.core.dto.SystemUserPageFind;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.mapper.SystemUserMapper;
import com.chenyingjun.sp.core.vo.SystemUserPageVo;
import com.chenyingjun.sp.core.vo.SystemUserVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    public SystemUser login(String account, String passWord) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        try {
            String md5Pwd = com.chenyingjun.sp.common.utils.StringUtils.md532(CommonConsts.MD5_PWD + passWord);
            map.put("passWord", md5Pwd);
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "密码加密失败");
            throw new BusinessException(ExceptionType.MD5_PASSWORD_ERROR);
        }
        SystemUser user = systemUserMapper.login(map);
        if (user != null) {
            user.setPassWord(null);
        } else {//更新失败次数
            SystemUser user1 = new SystemUser();
            user1.setAccount(account);
            user1 = systemUserMapper.selectOne(user1);
            if (user1 != null) {
                int failNum = user1.getFailNum() + 1;
                user1.setFailNum(failNum);
                if (failNum >= CommonConsts.LOGIN_FAIL_NUM) {
                    user1.setStatus(SystemUser.STATUS_0);
                }
                systemUserMapper.updateByPrimaryKeySelective(user1);
            }
        }
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
     * 根据主键查询用户信息
     *
     * @param id 查询信息主键
     * @return 组织列表
     */
    public SystemUserVo info(String id) {
        SystemUserVo user = systemUserMapper.info(id);
        user.setPassWord(null);
        return user;
    }

    /**
     * 编辑
     * @param edit 用户信息
     * @return 编辑条数
     */
    public int edit(SystemUserEdit edit) {
        String userId = edit.getUserId();
        if (StringUtils.isBlank(userId)) {
            return insert(edit);
        } else {
            return update(edit);
        }
    }

    /**
     * 新增
     * @param edit 用户信息
     * @return 新增条数
     */
    public int insert(SystemUserEdit edit) {
        SystemUser user = edit.thisToSystemUser();
        user.setCreateTime(new Date());
        String passWord = edit.getPassWord();
        if (StringUtils.isBlank(passWord)) {
            throw new BusinessException(ExceptionType.PASSWORD_NULL);
        }
        String md5Pwd = md5Pwd(passWord);
        user.setPassWord(md5Pwd);
        int i = systemUserMapper.insertSelective(user);
        String userId = user.getId();
        List<String> roleIds = edit.getRoleIds();
        systemUserRoleService.insert(userId, roleIds);
        return i;
    }

    /**
     * 更新
     * @param edit 用户信息
     * @return 更新条数
     */
    public int update(SystemUserEdit edit) {
        edit.setUpdateTime(new Date());
        String passWord = md5Pwd(edit.getPassWord());
        edit.setPassWord(passWord);
        String userId = edit.getUserId();
        List<String> roleIds = edit.getRoleIds();
        systemUserRoleService.insert(userId, roleIds);
        return systemUserMapper.edit(edit);
    }

    /**
     * md5加密
     * @param passWord 密码
     * @return 加密结果
     */
    private String md5Pwd(String passWord) {
        String md5Pwd = null;
        if (StringUtils.isNotBlank(passWord)) {
            try {
                md5Pwd = com.chenyingjun.sp.common.utils.StringUtils.md532(CommonConsts.MD5_PWD + passWord);
            } catch (Exception e) {
                LoggerUtils.error(getClass(), "密码加密失败");
                throw new BusinessException(ExceptionType.MD5_PASSWORD_ERROR);
            }
        }
        return md5Pwd;
    }
}
