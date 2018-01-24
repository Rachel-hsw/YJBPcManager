package com.res.pc.code.customer.bean;

import java.util.Date;

public class QueryCustomerVo {
     
	 private String username;

	 private String name;

	 private String tel;


	 private String openid;

	 private String iccard;

	 private String email;

	 private String createtime;
     private String FStatus;
	 public String getFStatus() {
		return FStatus;
	}
	public void setFStatus(String fStatus) {
		FStatus = fStatus;
	}
	private Date datetime;
		private String begin;
		private String end;
	private  String	currentPage="1";
	
	
		public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username== null ? null : username.trim();
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name== null ? null : name.trim();
		}
		
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel== null ? null : tel.trim();
		}
	
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid== null ? null : openid.trim();
		}
		public String getIccard() {
			return iccard;
		}
		public void setIccard(String iccard) {
			this.iccard = iccard== null ? null : iccard.trim();
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email== null ? null : iccard.trim();
		}
	
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public Date getDatetime() {
			return datetime;
		}
		public void setDatetime(Date datetime) {
			this.datetime = datetime;
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
		
		
}
