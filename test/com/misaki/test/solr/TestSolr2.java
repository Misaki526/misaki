package com.misaki.test.solr;

import java.util.UUID;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class TestSolr2 {
	
	private static final String URL = "http://localhost:8080/solr/collection1";
	
	@Test
	public void testAddUser() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		
		String prefix = "user_";
		User u1 = new User();
		u1.setId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u1.setName("张三");
		u1.setAge("25");
		u1.setSex("男");
		u1.setLike(new String[] {"足球", "篮球 ", "羽毛球"});
		solrServer.addBean(u1);
		
		User u2 = new User();
		u2.setId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u2.setName("张四");
		u2.setAge("18");
		u2.setSex("女");
		u2.setLike(new String[] {"保龄球", "篮乒乓球", "排球"});
		solrServer.addBean(u2);
		
		solrServer.commit();
	}
	
	@Test
	public void testChange() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		SolrDocument doc = new SolrDocument();
		
		doc.addField("id", "123456");
		doc.addField("user_name", "王五");
		doc.addField("user_like", new String[] {"music", "book", "sport"});
		doc.put("user_sex", "女");
		doc.setField("user_age", "18");
		User u = solrServer.getBinder().getBean(User.class, doc);
		
		System.out.println(u);
	}
	
	@Test
	public void testSearchUser() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "user_name:张");
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();
		for (SolrDocument sd : solrList) {
			User u = solrServer.getBinder().getBean(User.class, sd);
			System.out.println(u.getId());
			System.out.println(u.getName());
		}
	}
	
	@Test
	public void testDelUser() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		
		solrServer.deleteByQuery("user_name:张");
		solrServer.commit();
	}
}