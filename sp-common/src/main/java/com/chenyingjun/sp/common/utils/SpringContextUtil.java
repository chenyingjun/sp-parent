package com.chenyingjun.sp.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 静态获取Bean
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 *
 * @update 2018年8月22日
 * @why 端口号重新设置在serverProperties中设置，一开始不可以是application.properties中设置了spring.session.store-type=redis
 * 			现在改为spring.session.store-type=
 */
@Configuration
public class SpringContextUtil implements ApplicationContextAware/*, EmbeddedServletContainerCustomizer*/ {
	private static ApplicationContext applicationContext;

	/**
	 * 重写上下文信息
	 * @param applicationContext 上下文
	 * @throws BeansException e
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 设置上下文及端口号
	 * @param container container
	 */
	/*@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		int port = GlobalUtils.getIntConfig("server.port", 0);
		String contextPath = GlobalUtils.getConfig("server.contextPath", null);
		if (0 != port) {
			container.setPort(port);
		}

		if (StringUtils.isNotBlank(contextPath)) {
			container.setContextPath(contextPath);
		}
	}*/

	/**
	 * 获取上下文
	 * @return 上下文
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取指定的bean
	 * @param name bean名
	 * @return bean对象
	 * @throws BeansException e
	 */
	public static Object getBean(String name) throws BeansException {
		try {
			return applicationContext.getBean(name);
		} catch (Exception e) {
			throw new RuntimeException("获取的Bean不存在！");
		}
	}

	public static <T> T getBean(String name, Class<T> requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	public static Class<? extends Object> getType(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

}