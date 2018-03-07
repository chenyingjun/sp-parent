package com.chenyingjun.sp.controller.system;

import com.chenyingjun.sp.common.constant.CommonConsts;
import com.chenyingjun.sp.common.utils.IPUtil;
import com.chenyingjun.sp.common.utils.LoggerUtils;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.service.SystemUserService;
import com.chenyingjun.sp.shiro.token.manager.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class CommonController {

    @Autowired
    SystemUserService systemUserService;

    @RequestMapping("login")
    public String login(Map<String,Object> model) {
        model.put("time",new Date());
        model.put("message","this is a message");
        LoggerUtils.error(getClass(), "this login url");
        return "sign";
    }

    @RequestMapping(value = "")
    public String main(ModelMap map) {
        return userMain(map);
    }

    @RequestMapping(value = "main")
    public String userMain(ModelMap map) {
        Date date = new Date();
        map.put("nowTime", date);
        return "main";
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
        SystemUser user = null;
        try {
            user = TokenManager.login(entity,rememberMe);
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
                url = request.getContextPath() + "/main";
            }
            //跳转地址
            resultMap.put("back_url", url);
            //更新登录时间 last login time
            user.setUpdateTime(new Date());
            user.setLastTime(user.getLoginTime());
            user.setLoginTime(new Date());
            user.setLastIp(user.getLoginIp());
            String ip = IPUtil.getRequestIP(request);
            user.setLoginIp(ip);
            user.setFailNum(0);
        } catch (DisabledAccountException e) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号已经禁用。");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号或密码错误");
            int failNum = user.getFailNum() + 1;
            user.setFailNum(failNum);
            if (failNum >= CommonConsts.LOGIN_FAIL_NUM) {
                user.setStatus(SystemUser.STATUS_0);
            }
        }
        try {
            if (null != user) {
                systemUserService.baseUpdateByPrimaryKeySelective(user);
            }
        }catch (Exception e) {
            LoggerUtils.error(getClass(), "用户登录后信息更新失败，用户信息：" + user.toString());
        }
        return resultMap;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            TokenManager.logout();
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            LoggerUtils.error(getClass(), "errorMessage:" + e.getMessage());
            LoggerUtils.fmtError(getClass(), e, "退出出现错误，%s。", e.getMessage());
        }
        return resultMap;
    }
}
