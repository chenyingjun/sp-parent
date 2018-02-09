package com.chenyingjun.sp.controller.system;

import com.chenyingjun.sp.common.utils.LoggerUtils;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.shiro.token.manager.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping("login")
    public String login(Map<String,Object> model) {
        model.put("time",new Date());
        model.put("message","this is a message");
        LoggerUtils.error(getClass(), "this login url");
        return "sign";
    }


    /**
     * 登录提交
     * @param entity		登录的SystemUser
     * @param rememberMe	是否记住
     * @param request		request，用来取登录之前Url地址，用来登录后跳转到没有登录之前的页面。
     * @return
     */
    @RequestMapping(value="submitLogin",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(SystemUser entity, Boolean rememberMe, HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            entity = TokenManager.login(entity,rememberMe);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");


            /**
             * shiro 获取登录之前的地址
             * 之前0.1版本这个没判断空。
             */
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = null ;
            if(null != savedRequest){
                url = savedRequest.getRequestUrl();
            }
            /**
             * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
             * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
             */
            LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
            //如果登录之前没有地址，那么就跳转到首页。
            if(StringUtils.isBlank(url)){
                url = request.getContextPath() + "/user/index.shtml";
            }
            //跳转地址
            resultMap.put("back_url", url);
            /**
             * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
             */
        } catch (DisabledAccountException e) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号已经禁用。");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号或密码错误");
        }

        return resultMap;
    }
}
