package com.chenyingjun.sp.controller.system;


import com.chenyingjun.sp.common.bean.JsonResponse;
import com.chenyingjun.sp.core.dto.SystemUserEdit;
import com.chenyingjun.sp.core.dto.SystemUserPageFind;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.service.SystemUserRoleService;
import com.chenyingjun.sp.core.service.SystemUserService;
import com.chenyingjun.sp.core.vo.SystemUserVo;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/systemuser")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserRoleService systemUserRoleService;
    /**
     *
     * @Title: goPage
     * @Description: 进入列表页面
     * @return 系统用户信息页面
     */
    @RequestMapping(value = "/")
    public String goPage() {
        return "system/systemUser";
    }

    /**
     *
     * 进入新增系统用户页面
     * @return 系统用户信息页面
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "system/systemUserEdit";
    }


    @RequestMapping("page")
    @ResponseBody
    public PageInfo<SystemUser> page(@Valid SystemUserPageFind find, @RequestParam int pageNum, @RequestParam int
            pageSize) {
        return systemUserService.page(find, pageNum, pageSize);
    }

    /**
     * 根据主键查询用户信息
     * @param id 用户主键
     * @return 用户信息
     */
    @RequestMapping("{id}")
    public String info(@PathVariable String id, ModelMap map) {
        SystemUserVo user = systemUserService.info(id);
        map.put("user", user);
        String roleIds = systemUserRoleService.roleIdsByUserId(id);
        map.put("roleIds", roleIds);
        return "system/systemUserEdit";
    }

    @RequestMapping("edit")
    @ResponseBody
    public Object edit(@Valid SystemUserEdit edit) {
        return systemUserService.edit(edit);
    }
}
