package com.misaki.ls.food.action;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misaki.ls.food.service.FoodService;

@Controller
@RequestMapping("/ls/food")
public class FoodAction {
	@Resource
	private FoodService foodService;

	@RequestMapping("/findFood")
	@ResponseBody
	public Object findFood(String order) {
		
		return null;
	}
}
