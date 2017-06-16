package com.misaki.ls.music.service;

public interface MusicService {
	
	/**
	 * 先将信息分词，从Solr中找相关索引
	 * @param information 查询信息
	 * @return 音乐地址
	 */
	public String findMusic(String information) throws Exception;
}
