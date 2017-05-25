package com.misaki.core.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.misaki.account.user.dao.UserDao;
import com.misaki.core.dao.BaseDao;
import com.misaki.core.page.Page;
import com.misaki.core.service.BaseService;
import com.misaki.ls.nurse.dao.NurseDao;
import com.misaki.qa.faq.dao.FaqDao;

public class BaseServiceImpl<T> implements BaseService<T> {
	protected BaseDao<T> baseDao;
	
	@Autowired
	protected FaqDao faqDao;
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected NurseDao nurseDao;
	
	// 在构造方法后，初化前执行
	@PostConstruct  
	private void initBaseMapper() throws Exception {
		// this关键字指对象本身，这里指的是调用此方法的实现类（子类）
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		// 获取第一个参数的class
		Class<?> clazz = (Class<?>)type.getActualTypeArguments()[0];
		
		// 转化为属性名（相关的Dao子类的引用名）Supplier  supplierDao
		String localField = clazz.getSimpleName().substring(0,1).toLowerCase() + clazz.getSimpleName().substring(1) + "Dao";
		
		// getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
		Field field = this.getClass().getSuperclass().getDeclaredField(localField);
		Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");
		
		// field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseDao所指向的对象为其子类型SupplierDao对象，子类型对象已被spring实例化于容器中		
		baseField.set(this, field.get(this));		
	}

	@Override
	public int insert(T entity) throws Exception {
		return baseDao.insert(entity);
	}

	@Override
	public T selectByPk(T entity) {
		return baseDao.selectByPk(entity);
	}

	@Override
	public int deleteByPk(T entity) throws Exception {
		return baseDao.deleteByPk(entity);
	}
	
	@Override
	public int deleteByPks(String [] pks) throws Exception {
		return baseDao.deleteByPks(pks);
	}

	@Override
	public int updateByPk(T entity) throws Exception {
		return baseDao.updateByPk(entity);
	}

	@Override
	public List<T> selectUseDyc(T entity) {
		return baseDao.selectUseDyc(entity);
	}

	@Override
	public Page<T> selectPage(Page<T> page) {
		page.setList(baseDao.selectPageList(page));
		page.setTotalRecord(baseDao.selectCount(page));
		return page;
	}

}
