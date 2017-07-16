package com.misaki.core.service;

import java.util.List;

import com.misaki.core.page.Page;

public interface BaseService<T> {
	// 添加实体对象信息到表
	public int insert(T entity) throws Exception;
 
	// 根据对象主键查询
	public T selectByPk(T entity);
 
	// 根据对象主键删除
	public int deleteByPk(T entity) throws Exception;
	
	// 根据对象主键数据批量删除
	public int deleteByPks(String[] pks) throws Exception;
 
	// 根据对象主键修改
	public int updateByPk(T entity) throws Exception;

	// 根据对象动态查询对象列表
	public List<T> selectUseDyc(T entity);
	
	//分页查询
	public Page<T> selectPage(Page<T> page);
}

