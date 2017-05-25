package com.misaki.account.user.service;

import com.misaki.account.user.entity.User;
import com.misaki.core.service.BaseService;

public interface UserService extends BaseService<User> {
	public User login(User user);
}