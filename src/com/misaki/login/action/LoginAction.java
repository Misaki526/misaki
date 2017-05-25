package com.misaki.login.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.misaki.account.user.entity.User;
import com.misaki.account.user.service.UserService;

@Controller
@RequestMapping("/admin")
public class LoginAction {
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, HttpSession session){
		System.out.println(user);
		User u = userService.login(user);
		if (u != null) {
			session.setAttribute("user", u);
			return "redirect:/home/index.action";
		} else {
			request.setAttribute("msg", "用户名或密码有误~");
		}
		return "forward:/WEB-INF/jsp/loginUI.jsp";
	}
	
	@RequestMapping("/loginUI")
	public String login(){
		return "forward:/WEB-INF/jsp/loginUI.jsp";
	}
}