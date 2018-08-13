package com.chenyingjun.sp.common.utils;

/**
 * 配置信息获取
 * @author chenyingjun
 * @date 2018年8月13日
 */
public class GlobalUtils {

    /**
     * 配置参数信息
     */
    private static PropertiesLoaderUtils propertiesLoaderUtils;

    /**
     * 构建函数
     */
    public GlobalUtils() {
    }

    /**
     * 根据key值获取配置信息
     * @param key key
     * @return 配置信息
     */
    public static String getConfig(String key) {
        return getPropertiesLoaderUtils().getProperty(key);
    }

    /**
     * 根据key值获取配置信息
     * @param key key
     * @param defaultValue 默认值
     * @return 配置信息
     */
    public static String getConfig(String key, String defaultValue) {
        return getPropertiesLoaderUtils().getProperty(key, defaultValue);
    }

    public static int getIntConfig(String key) {
        return Integer.valueOf(getConfig(key)).intValue();
    }

    public static int getIntConfig(String key, int defaultValue) {
        return Integer.valueOf(getConfig(key, String.valueOf(defaultValue))).intValue();
    }

    public static boolean getBooleanConfig(String key) {
        return Boolean.valueOf(getConfig(key)).booleanValue();
    }

    public static boolean getBooleanConfig(String key, boolean defaultValue) {
        return Boolean.valueOf(getConfig(key, String.valueOf(defaultValue))).booleanValue();
    }

    public static long getLongConfig(String key) {
        return Long.valueOf(getConfig(key)).longValue();
    }

    public static long getLongConfig(String key, long defaultValue) {
        return Long.valueOf(getConfig(key, String.valueOf(defaultValue))).longValue();
    }


    /**
     * 加载配置文件
     * @return 配置信息
     */
    private static PropertiesLoaderUtils getPropertiesLoaderUtils() {
        if (null == propertiesLoaderUtils) {
            propertiesLoaderUtils =  new PropertiesLoaderUtils();
        }
        return propertiesLoaderUtils;
    }
}
