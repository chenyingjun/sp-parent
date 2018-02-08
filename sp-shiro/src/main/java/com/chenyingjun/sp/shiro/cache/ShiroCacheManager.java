package com.chenyingjun.sp.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * shiro cache manager 接口
 *
 * @author chenyingjun
 * @version 2018年02月08日
 * @since 1.0
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
