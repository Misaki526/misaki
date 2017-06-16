package com.misaki.test.solr;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 引入相关jar
 */
@SuppressWarnings("deprecation")
public class TestSolr1 {

	private static final String URL = "http://localhost:8080/solr/collection1";
	
	@Test
	public void testAdd() throws Exception {
		// 实例化solr对象
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		// 实例化添加数据类
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.setField("id", "1001");
		doc1.setField("name", "iphone7手机");
		doc1.setField("price", "6000");
		doc1.setField("url", "/images/001.jpg");
		
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.setField("id", "1002");
		doc2.setField("name", "sam sung s8手机");
		doc2.setField("price", "6000");
		doc2.setField("url", "/images/002.jpg");
		
		// 设置服务器保存信息并提交
		solrServer.add(doc1);
		solrServer.add(doc2);
		solrServer.commit();
	}
	
	@Test
	public void testSearch() throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		// 查询类
		SolrQuery solrQuery = new SolrQuery();
		// 查询关键词
		solrQuery.set("q", "name:iphone7");
		// 查询数据
		QueryResponse response = solrServer.query(solrQuery);
		// 获取数据
		SolrDocumentList solrList = response.getResults();
		long num = solrList.getNumFound();
		System.out.println("条数: " + num);
		
		for (SolrDocument sd : solrList) {
			String id = (String) sd.get("id");
			String name = (String) sd.get("name");
			Float price = (Float) sd.get("price");
			String url = (String) sd.get("url");
			
			System.out.println("id: " + id);
			System.out.println("name: " + name);
			System.out.println("price: " + price);
			System.out.println("url: " + url);
		}
	}
	
	@Test
	public void testDel() throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		
		// solrServer.deleteById("1");
		
		solrServer.deleteByQuery("id:1001 id:1002");
		
		solrServer.commit();
	}	
	
}
