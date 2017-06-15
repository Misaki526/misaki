package com.misaki.core.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.misaki.core.util.AllocationUtil;

@Controller
@RequestMapping("/base")
public class BaseAction {
	
	@Resource
	public ServletContext application;
	
	@RequestMapping("/goURL/{folder1}/{folder2}/{file}")
	public String goURL(@PathVariable String folder1, @PathVariable String folder2, @PathVariable String file) {
		return "forward:/WEB-INF/jsp/" + folder1 + "/" + folder2 + "/" + file + ".jsp";
	}
	
	@RequestMapping("/allocation")
	public Object allocation(String order) {
		String result = AllocationUtil.getType(order);
		
		// 拼凑转发地址
		String forwardAddr = null;
		switch (result) {
			case AllocationUtil.TYPE_FOOD:
				forwardAddr = "ls/" + AllocationUtil.TYPE_FOOD + "/findFood.action";
				break;
				
			case AllocationUtil.TYPE_MUSIC:
				forwardAddr = "ls/" + AllocationUtil.TYPE_MUSIC + "/findMusic.action";
				break;
				
			case AllocationUtil.TYPE_NURSE:
				forwardAddr = "ls/" + AllocationUtil.TYPE_NURSE + "/findNurse.action";
				break;
				
			default:
				forwardAddr = AllocationUtil.TYPE_QA + "/faq/findAnswerInFaq.action";
				break;
		}
		return "forward:/" + forwardAddr;
	}

}

