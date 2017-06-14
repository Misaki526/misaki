package com.misaki.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.TokenInfo;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class AllocationUtil {

	private static final String URL = "http://localhost:8080/solr/collection1";
	public static final String TYPE_MUSIC = "music";
	public static final String TYPE_FOOD = "food";
	public static final String TYPE_NURSE = "nurse";
	public static final String TYPE_QA = "qa";

	/**
	 * 将传来的指令分词，根据规则归为某种服务类型
	 * @param order 前端传来的指令
	 * @return 返回属于的服务类型
	 */
	public static String getType(String order) {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		solrServer.setConnectionTimeout(5000);
		
		// 将order进行分词
		FieldAnalysisRequest request = new FieldAnalysisRequest(
				"/analysis/field");
		request.addFieldName("faq_title"); // 字段名，随便指定一个支持中文分词的字段
		request.setFieldValue("");         // 字段值，可以为空字符串，但是需要显式指定此参数
		request.setQuery(order);
		
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
		
		/*
		 * 听歌：听...，放...，
		 * 点餐：吃...，点餐...
		 * 找保姆：找...保姆
		 * 问答：other
		 */
		String resultType = AllocationUtil.TYPE_QA;
		for (String word : results) {
			if (word.contains("听") || word.contains("放")) {
				resultType = AllocationUtil.TYPE_MUSIC;
				break;
			} else if (word.contains("吃") || word.contains("点餐")) {
				resultType = AllocationUtil.TYPE_FOOD;
				break;
			} else if (word.contains("找") || word.contains("保姆")) {
				resultType = AllocationUtil.TYPE_NURSE;
				break;
			}
		}

		return resultType;
	}

	@Test
	public void getAnalysis() {
		System.out.println(AllocationUtil.getType("我想听星星点灯"));
	}
}
