package com.misaki.ls.nurse.service;

import com.misaki.core.service.BaseService;
import com.misaki.ls.nurse.entity.Nurse;

public interface NurseService extends BaseService<Nurse> {

	// 同步到Solr
	public int synchroSolr() throws Exception;
}
