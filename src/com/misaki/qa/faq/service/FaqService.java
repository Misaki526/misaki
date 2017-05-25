package com.misaki.qa.faq.service;

import com.misaki.core.service.BaseService;
import com.misaki.qa.faq.entity.Faq;

public interface FaqService extends BaseService<Faq> {
	
	/**
	 * 先将问题分词，从Solr中找相关索引
	 * @param question 前端传入的问题
	 * @return 返回准确的答案
	 */
	public Faq findAnswerInFaq(String question) throws Exception;
	
	// 同步到Solr
	public int synchroSolr() throws Exception;
}
