package com.chenyingjun.sp.core.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
