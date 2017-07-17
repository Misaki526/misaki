package com.misaki.qa.reasoning.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.misaki.core.util.NLPUtil;
import com.misaki.qa.reasoning.service.ReasoningService;
import com.misaki.qa.reasoning.util.ReasoningUtil;

@Service("reasoningService")
public class ReasoningServiceImpl implements ReasoningService {

	@Override
	public String findAnswer(String question) throws Exception {
		
		// 获取分词结果
		List<String> splitWords = NLPUtil.getSplitWords(question);
		
		String result = ReasoningUtil.getAnswerFromGraph(splitWords);
		
		return result;
	}
	
}
