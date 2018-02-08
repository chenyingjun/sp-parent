package com.chenyingjun.sp.shiro.service;

/**
 * 用户Session 手动管理
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
public interface ShiroManager {

	 /**
    * 加载过滤配置信息
    * @return
    */
   String loadFilterChainDefinitions();
   
   /**
    * 重新构建权限过滤器
    * 一般在修改了用户角色、用户等信息时，需要再次调用该方法
    */
   void reCreateFilterChains();
}
