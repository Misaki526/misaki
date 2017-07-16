package com.misaki.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.TokenInfo;

/**
 * 封装一些常见的自然语言处理方法
 */
@SuppressWarnings("deprecation")
public class NLPUtil {
	
	public static final String URL = "http://localhost:8080/solr/collection1";

	/**
	 * @param sentence
	 * @return 分词结果
	 */
	public static List<String> getSplitWords(String sentence) {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		solrServer.setConnectionTimeout(5000);
		
		// 将sentence进行分词
		FieldAnalysisRequest request = new FieldAnalysisRequest(
				"/analysis/field");
		request.addFieldName("faq_title"); // 字段名，随便指定一个支持中文分词的字段
		request.setFieldValue("");         // 字段值，可以为空字符串，但是需要显式指定此参数
		request.setQuery(sentence);
		
		FieldAnalysisResponse response = null;
		try {
			response = request.process(solrServer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> results = new ArrayList<String>();
		Iterator<AnalysisPhase> it = response.getFieldNameAnalysis("faq_title")
				.getQueryPhases().iterator();
		while (it.hasNext()) {
			AnalysisPhase pharse = (AnalysisPhase) it.next();
			List<TokenInfo> list = pharse.getTokens();
			for (TokenInfo info : list) {
				results.add(info.getText());
			}

		}
		
		return results;
	}
}
