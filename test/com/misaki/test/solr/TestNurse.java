package com.misaki.test.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.misaki.ls.nurse.entity.Nurse;

@SuppressWarnings("deprecation")
public class TestNurse {
	private static final String URL = "http://localhost:8080/solr/collection1";
	
	@Test
	public void testSearchNurse() throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "nurse_info:我要找山东的保姆");
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();
		for (SolrDocument sd : solrList) {
			Nurse nurse = solrServer.getBinder().getBean(Nurse.class, sd);
			System.out.println(nurse.getNurseId());
			System.out.println(nurse.getNurseName());
		}
	}
	
	@Test
	public void testDel() throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		
		// solrServer.deleteById("1");
		solrServer.deleteByQuery("id:12345 id:23456 id:34567 id:45678 id:56789");
		
		solrServer.commit();
	}	
	
}
