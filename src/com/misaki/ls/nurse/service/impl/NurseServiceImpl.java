package com.misaki.ls.nurse.service.impl;

import java.net.URL;
import java.net.URLConnection;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;
import com.misaki.core.service.impl.BaseServiceImpl;
import com.misaki.core.util.AllocationUtil;
import com.misaki.ls.nurse.entity.Nurse;
import com.misaki.ls.nurse.service.NurseService;

@SuppressWarnings("deprecation")
@Service("nurseService")
public class NurseServiceImpl extends BaseServiceImpl<Nurse> implements NurseService {

	@Override
	public Nurse findNurse(String information) throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(AllocationUtil.URL);
		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.set("q", "nurse_area:" + information);
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();
		
		// 只取最好的值
		Nurse nurse = null;
		if (solrList.size() > 0)
			nurse = solrServer.getBinder().getBean(Nurse.class, solrList.get(0));
		else {
			nurse = new Nurse();
			nurse.setNurseName("Not Found");
		}
		
		return nurse;
	}
	
	@Override
	public int synchroSolr() throws Exception {
		String url = "http://localhost:8080/solr/collection1/dataimport?command=delta-import&entity=m_nurse";
			
		URLConnection connection = new URL(url).openConnection();
		connection.connect();
		connection.getHeaderFields();
		
		return 0;
	}

}
