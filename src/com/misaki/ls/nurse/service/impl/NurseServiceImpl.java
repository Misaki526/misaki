package com.misaki.ls.nurse.service.impl;

import java.net.URL;
import java.net.URLConnection;
import org.springframework.stereotype.Service;
import com.misaki.core.service.impl.BaseServiceImpl;
import com.misaki.ls.nurse.entity.Nurse;
import com.misaki.ls.nurse.service.NurseService;

@Service("nurseService")
public class NurseServiceImpl extends BaseServiceImpl<Nurse> implements NurseService {

	@Override
	public int synchroSolr() throws Exception {
		String url = "http://localhost:8080/solr/collection1/dataimport?command=delta-import&entity=m_nurse";
			
		URLConnection connection = new URL(url).openConnection();
		connection.connect();
		connection.getHeaderFields();
		
		return 0;
	}

}
