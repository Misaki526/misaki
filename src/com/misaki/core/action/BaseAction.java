package com.misaki.core.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base")
public class BaseAction {
	
	@Resource
	public ServletContext application;
	
	@RequestMapping("/goURL/{folder1}/{folder2}/{file}")
	public String goURL(@PathVariable String folder1, @PathVariable String folder2, @PathVariable String file) {
		return "forward:/WEB-INF/jsp/" + folder1 + "/" + folder2 + "/" + file + ".jsp";
	}

}

