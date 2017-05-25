package com.misaki.core.dao;

import java.util.List;

import com.misaki.core.page.Page;

public interface BaseDao<T> {
	// 添加实体对象信息到表
	public int insert(T entity);
	 
	// 根据对象主键查询
	public T selectByPk(T entity);
	 
	// 根据对象主键删除
	public int deleteByPk(T entity);
	
	// 根据对象主键数据批量删除
	public int deleteByPks(String[] pks);
	 
	// 根据对象主键修改
	public int updateByPk(T entity);
	 
	// 根据对象动态生成SQL语句
	public List<T> selectUseDyc(T entity);
	
	// 用于分页查询数据集
	public List<T> selectPageList(Page<T> page);
	 
	// 用于分页查询总记录数
	public int selectCount(Page<T> page);

}
