package com.misaki.home.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeAction {

	@RequestMapping(value="/index")
	public String index() {
		
		return "forward:/WEB-INF/jsp/home/home.jsp";
	}
}
