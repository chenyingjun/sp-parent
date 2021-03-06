package com.chenyingjun.sp.shiro.bo;

import com.chenyingjun.sp.core.entity.SystemUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录信息
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
@Data
public class UserOnlineBo extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Session Id
	 */
	private String sessionId;

	/**
	 * Session Host
	 */
	private String host;

	/**
	 * Session创建时间
	 */
	private Date startTime;

	/**
	 * Session最后交互时间
	 */
	private Date lastAccess;

	/**
	 * Session timeout
	 */
	private long timeout;

	/**
	 * session 是否踢出
	 */
	private boolean sessionStatus = Boolean.TRUE;

	/**
	 * 构建函数
	 */
	public UserOnlineBo(SystemUser user) {
		super(user);
	}
}
