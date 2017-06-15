package com.misaki.qa.faq.service.impl;

import java.net.URL;
import java.net.URLConnection;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;
import com.misaki.core.service.impl.BaseServiceImpl;
import com.misaki.core.util.AllocationUtil;
import com.misaki.qa.faq.entity.Faq;
import com.misaki.qa.faq.service.FaqService;

@SuppressWarnings("deprecation")
@Service("faqService")
public class FaqServiceImpl extends BaseServiceImpl<Faq> implements FaqService {
	
	@Override
	public Faq findAnswerInFaq(String question) throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(AllocationUtil.URL);
		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.set("q", "faq_title:" + question);
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();

		// 只取最好的值
		Faq faq = null;
		if (solrList.size() > 0)
			faq = solrServer.getBinder().getBean(Faq.class, solrList.get(0));
		else {
			faq = new Faq();
			faq.setFaqAnswer("Not Found");
		}

		return faq;
	}
	
	@Override
	public int synchroSolr() throws Exception {
		String url = "http://localhost:8080/solr/collection1/dataimport?command=delta-import&entity=m_faq";
			
		URLConnection connection = new URL(url).openConnection();
		connection.connect();
		connection.getHeaderFields();
		
		return 0;
	}
}