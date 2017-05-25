package com.misaki.account.user.dao;

import com.misaki.account.user.entity.User;
import com.misaki.core.dao.BaseDao;

public interface UserDao extends BaseDao<User> {
	public User login(User user);
}
