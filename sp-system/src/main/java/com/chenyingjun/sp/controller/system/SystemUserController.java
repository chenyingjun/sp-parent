package com.chenyingjun.sp.controller.system;


import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.service.SystemUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemuser")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;
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


    @RequestMapping("page")
    @ResponseBody
    public PageInfo<SystemUser> page(@RequestParam int pageNum, @RequestParam int
            pageSize) {
        return systemUserService.page(pageNum, pageSize);
    }
}
