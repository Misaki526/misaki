package com.misaki.core.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 3504572639257797739L;
	
	private Integer pageNumber;   // 当前页
	private Integer pageSize;
	private Integer totalRecord;
	private List<T> list;
	private T paramEntity;  // 多查询条件时使用
	private String keyword; // 单个关键字查询
	private Integer start;
	private Map<String, Object> pageMap = new HashMap<String, Object>();

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalRecord(Integer totalRecord) {
		pageMap.put("total", totalRecord);
		this.totalRecord = totalRecord;
	}

	public void setList(List<T> list) {
		pageMap.put("rows", list);
		this.list = list;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public T getParamEntity() {
		return paramEntity;
	}

	public void setParamEntity(T paramEntity) {
		this.paramEntity = paramEntity;
	}

	public Integer getStart() {
		this.start = (pageNumber-1) * pageSize;
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalRecord=" + totalRecord + ", list=" + list
				+ ", keyword=" + keyword + ", start=" + start + "]";
	}
	
}