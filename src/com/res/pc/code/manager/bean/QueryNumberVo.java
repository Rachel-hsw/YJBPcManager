package com.res.pc.code.manager.bean;

public class QueryNumberVo  {
	@Override
	public String toString() {
		return "QueryNumberVo [CustomerJc=" + CustomerJc + ", CustomerID=" + CustomerID + ", qc=" + qc + ", fhsl="
				+ fhsl + ", yssl=" + yssl + ", xhsl=" + xhsl + ", hssl=" + hssl + ", thsl=" + thsl + ", qm=" + qm + "]";
	}
	private String CustomerJc;//客户简称
	private String CustomerID;//用户id
	private Integer qc;
	//private  int qc;//期初量
	private Integer  fhsl;//发货量
	private Integer  yssl;//运行量
	private Integer  xhsl;//卸货量
	private Integer  hssl;//回收量
	private Integer  thsl;//退回量
	private Integer  qm;//期末量
	public String getCustomerJc() {
		return CustomerJc;
	}
	public void setCustomerJc(String customerJc) {
		CustomerJc = customerJc;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	
	public Integer getQc() {
		return qc;
	}
	public void setQc(Integer qc) {
		this.qc = qc;
	}
	public Integer getFhsl() {
		return fhsl;
	}
	public void setFhsl(Integer fhsl) {
		this.fhsl = fhsl;
	}
	public Integer getYssl() {
		return yssl;
	}
	public void setYssl(Integer yssl) {
		this.yssl = yssl;
	}
	public Integer getXhsl() {
		return xhsl;
	}
	public void setXhsl(Integer xhsl) {
		this.xhsl = xhsl;
	}
	public Integer getHssl() {
		return hssl;
	}
	public void setHssl(Integer hssl) {
		this.hssl = hssl;
	}
	public Integer getThsl() {
		return thsl;
	}
	public void setThsl(Integer thsl) {
		this.thsl = thsl;
	}
	public Integer getQm() {
		return qm;
	}
	public void setQm(Integer qm) {
		this.qm = qm;
	}
	

}
