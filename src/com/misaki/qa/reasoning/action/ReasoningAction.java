package com.misaki.qa.reasoning.action;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.misaki.qa.reasoning.service.ReasoningService;

@Controller
@RequestMapping(value = "/qa/reasoning")
public class ReasoningAction {
	
	@Resource
	private ReasoningService reasoningService;
	
	@RequestMapping("/findAnswer")
	@ResponseBody
	public Object findAnswer(String order) {
		
		String result = null;
		try {
			result = reasoningService.findAnswer(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
