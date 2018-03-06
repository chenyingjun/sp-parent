package com.chenyingjun.sp.controller;

import com.chenyingjun.sp.common.utils.LoggerUtils;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping("")
    @ResponseBody
    public String test1() {
        LoggerUtils.error(getClass(), "aaaaaaa");
        return "this is a test";
    }

    @RequestMapping("map")
    @ResponseBody
    public Map<String, String> mapTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "11111111");
        map.put("2", "22222222");
        return map;
    }

    @RequestMapping("user")
    @ResponseBody
    public SystemUser findone(String id) {
        return systemUserService.baseSelectByPrimaryKey(id);
    }
}
