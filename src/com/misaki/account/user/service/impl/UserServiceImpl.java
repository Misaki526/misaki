package com.misaki.account.user.service.impl;

import org.springframework.stereotype.Service;
import com.misaki.account.user.entity.User;
import com.misaki.account.user.service.UserService;
import com.misaki.core.service.impl.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
