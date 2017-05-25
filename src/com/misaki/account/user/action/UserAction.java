package com.misaki.account.user.action;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.misaki.account.user.entity.User;
import com.misaki.account.user.service.UserService;
import com.misaki.core.action.BaseAction;
import com.misaki.core.page.Page;

@Controller
@RequestMapping("/account/user")
public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(User user) {
		int i = 0;
		try {
			i = userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPageUseDyc(Page<User> page) {
		
		Page<User> result = userService.selectPage(page);
		
		return result.getPageMap();
	}
	
	@RequestMapping("/deleteByPks")
	@ResponseBody
	public Object deleteByPks(String [] pks) {
		int i = 0;
		try {
			i = userService.deleteByPks(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/updateByPk")
	@ResponseBody
	public Object updateByPk(User user) {
		int i = 0;
		try {
			i = userService.updateByPk(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
