package com.res.pc.code.customer.bean;

public class QueryExamineVo {
	private String queryFLevel;
	private String queryFStatus="0";
	private String begin;
	private String end;
private  String	currentPage="1";
	public String getQueryFLevel() {
		return queryFLevel;
	}

	public void setQueryFLevel(String queryFLevel) {
		this.queryFLevel = queryFLevel;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getQueryFStatus() {
		return queryFStatus;
	}

	public void setQueryFStatus(String queryFStatus) {
		this.queryFStatus = queryFStatus;
	}

	

}
