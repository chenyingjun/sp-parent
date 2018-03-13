package com.chenyingjun.sp.shiro.token;

import com.chenyingjun.sp.core.entity.BaseEntity;
import com.chenyingjun.sp.core.entity.SystemUser;
import com.chenyingjun.sp.core.service.SystemUserService;
import com.chenyingjun.sp.shiro.token.manager.TokenManager;
import freemarker.template.TemplateModelException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * hiro 认证 + 授权   重写
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
public class SampleRealm extends AuthorizingRealm {

/*	@Autowired
	UserService userService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	OrgService roleService;*/
	@Autowired
	SystemUserService systemUserService;

	/** x */
	@Autowired
	private freemarker.template.Configuration freeMarkerConfiguration;

	public SampleRealm() {
		super();
	}
	/**
	 *  认证信息，主要针对用户登录， 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		ShiroToken token = (ShiroToken) authcToken;
		SystemUser user = systemUserService.login(token.getUsername(), token.getPswd());
//		SystemUser user = new SystemUser(token.getUsername(), token.getPswd());
		if(null == user){
			throw new AccountException("帐号或密码不正确！");
		/**
		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
		 */
		}else if(SystemUser.STATUS_0 == user.getStatus().intValue()){
			throw new DisabledAccountException("帐号已经禁止登录！");
		}
		return new SimpleAuthenticationInfo(user, token.getPswd(), getName());
    }

	 /** 
     * 授权 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	
    	String userId = TokenManager.getUserId();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
//		Set<String> roles = roleService.findRoleByUserId(userId);
		List<String> roleList = new ArrayList<String>();
		roleList.add("chenRole");
		Set<String> roles = new HashSet<String>(roleList);
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		List<String> permissionList = new ArrayList<String>();
		permissionList.add("/test/map");
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
		Set<String> permissions = new HashSet<String>(permissionList);
		info.setStringPermissions(permissions);
        return info;  
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
