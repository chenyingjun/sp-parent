package com.chenyingjun.sp.controller.system;


import com.chenyingjun.sp.core.dto.SystemRolePageFind;
import com.chenyingjun.sp.core.entity.SystemRole;
import com.chenyingjun.sp.core.service.SystemRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/systemrole")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;
    /**
     *
     * @Title: goPage
     * @Description: 进入列表页面
     * @return 系统角色列表信息页面
     */
    @RequestMapping(value = "/")
    public String goPage() {
        return "system/systemRole";
    }

    /**
     *
     * 进入新增系统用户页面
     * @return 系统角色信息页面
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "system/systemRoleEdit";
    }


    @RequestMapping("page")
    @ResponseBody
    public PageInfo<SystemRole> page(@Valid SystemRolePageFind find, @RequestParam int pageNum, @RequestParam int
            pageSize) {
        return systemRoleService.page(find, pageNum, pageSize);
    }

    /**
     * 根据主键查询用户信息
     * @param id 用户主键
     * @return 用户信息
     */
    @RequestMapping("{id}")
    public String info(@PathVariable String id, ModelMap map) {
        SystemRole role = systemRoleService.info(id);
        map.put("role", role);
        return "system/systemRoleEdit";
    }

    /**
     * 根据主键查询用户信息
     * @param id 用户主键
     * @return 用户信息
     */
    @RequestMapping("data/{id}")
    @ResponseBody
    public SystemRole dataId(@PathVariable String id) {
        return systemRoleService.info(id);
    }
}
