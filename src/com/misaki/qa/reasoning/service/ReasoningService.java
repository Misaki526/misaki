package com.misaki.qa.reasoning.service;


public interface ReasoningService {
	
	/**
	 * 根据规则，推理答案
	 * @param question 前端传入的问题
	 * @return 返回准确的答案
	 */
	public String findAnswer(String question) throws Exception;
}
