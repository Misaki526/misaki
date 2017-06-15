package com.misaki.ls.nurse.service;

import com.misaki.core.service.BaseService;
import com.misaki.ls.nurse.entity.Nurse;

public interface NurseService extends BaseService<Nurse> {

	/**
	 * 先将信息分词，从Solr中找相关索引
	 * @param information 查询信息
	 * @return 保姆资料
	 */
	public Nurse findNurse(String information) throws Exception;
	
	/**
	 * 同步到Solr
	 */
	public int synchroSolr() throws Exception;
}
