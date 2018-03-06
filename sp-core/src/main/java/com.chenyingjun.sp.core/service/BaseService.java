package com.chenyingjun.sp.core.service;

import com.chenyingjun.sp.core.utils.MyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  基础服务类
 *
 * @author chenyingjun
 * @version 2017年12月22日
 * @since 1.0
 */
public class BaseService<C> {

    @Autowired
    private MyMapper<C> myMapper;

    /**
     * 新增信息
     * @param c 信息
     */
    public int baseInsert(C c) {
        return myMapper.insert(c);
    }

    /**
     * 选择性更新信息
     * @param c 信息
     */
    public int baseUpdateByPrimaryKeySelective(C c) {
        return myMapper.updateByPrimaryKeySelective(c);
    }

    /**
     * 查询列表
     * @param o 查询信息
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @return page
     */
    public PageInfo<C> basePageByExample(Object o, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<C> list = baseListByExample(o);
        return new PageInfo<C> (list);
    }

    /**
     * 查询列表
     * @param list 列表信息
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @return page
     */
    public PageInfo<C> basePageByExample(List list, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<C> (list);
    }

    /**
     * 查询信息
     * @param o 查询条件
     * @return 信息列表
     */
    public List<C> baseListByExample(Object o) {
        return myMapper.selectByExample(o);
    }

    /**
     * 根据主键查询
     * @param id 主键
     * @return 组织信息
     */
    public C baseSelectByPrimaryKey(String id) {
        return myMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主体查询信息
     * @param c 主体
     * @return 信息
     */
    public C baseSelectOne(C c) {
        return myMapper.selectOne(c);
    }

}
