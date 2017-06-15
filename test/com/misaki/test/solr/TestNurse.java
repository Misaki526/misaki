package com.misaki.test.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import com.misaki.ls.nurse.entity.Nurse;

public class TestNurse {
	private static final String URL = "http://localhost:8080/solr/collection1";
	
	@Test
	public void testSearchNurse() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "nurse_area:山东");
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();
		for (SolrDocument sd : solrList) {
			Nurse nurse = solrServer.getBinder().getBean(Nurse.class, sd);
			System.out.println(nurse.getNurseId());
			System.out.println(nurse.getNurseName());
		}
	}
	
}
