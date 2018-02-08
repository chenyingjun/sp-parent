package com.chenyingjun.sp.controller.system;

import com.chenyingjun.sp.common.utils.LoggerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("")
public class BaseController {

    @RequestMapping("login")
    public String login(Map<String,Object> model) {
        model.put("time",new Date());
        model.put("message","this is a message");
        LoggerUtils.error(getClass(), "this login url");
        return "web";
    }
}
