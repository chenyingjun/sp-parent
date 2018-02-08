package com.chenyingjun.sp.shiro.cache.impl;

import com.chenyingjun.sp.shiro.cache.JedisManager;
import com.chenyingjun.sp.shiro.cache.JedisShiroCache;
import com.chenyingjun.sp.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

/**
 * JRedis管理
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
public class JedisShiroCacheManager implements ShiroCacheManager {

    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }

    @Override
    public void destroy() {
    	//如果和其他系统，或者应用在一起就不能关闭
    	getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
