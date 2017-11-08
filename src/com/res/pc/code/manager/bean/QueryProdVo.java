package com.res.pc.code.manager.bean;

public class QueryProdVo {
private String  queryname;
private String  querystatus;
public String getQueryname() {
	return queryname;
}
public void setQueryname(String queryname) {
	this.queryname = queryname == null ? null : queryname.trim();
}
public String getQuerystatus() {
	return querystatus;
}
public void setQuerystatus(String querystatus) {
	this.querystatus = querystatus  == null ? null : querystatus.trim();
}

}
