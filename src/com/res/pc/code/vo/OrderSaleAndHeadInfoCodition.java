package com.res.pc.code.vo;



public class OrderSaleAndHeadInfoCodition {
private  String ordercustomer;
private String customerJc;
	private String saleoid;
	private String drivername;
	private String date1; 
	private String date2;
	private String deliverystandards;
	private  String orderStateCode;
	private String begin;
	private String end;
	
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
	public String getOrdercustomer() {
		return ordercustomer;
	}
	public void setOrdercustomer(String ordercustomer) {
		this.ordercustomer = ordercustomer == null ? null : ordercustomer.trim();
	}
	public String getCustomerJc() {
		return customerJc;
	}
	public void setCustomerJc(String customerJc) {
		this.customerJc = customerJc== null ? null : customerJc.trim();
	}
	public String getSaleoid() {
		return saleoid;
	}
	public void setSaleoid(String saleoid) {
		this.saleoid = saleoid== null ? null : saleoid.trim();
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername== null ? null : drivername.trim();
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1== null ? null : date1.trim();
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2== null ? null : date2.trim();
	}
	public String getDeliverystandards() {
		return deliverystandards;
	}
	public void setDeliverystandards(String deliverystandards) {
		this.deliverystandards = deliverystandards== null ? null : deliverystandards.trim();
	}
	public String getOrderStateCode() {
		return orderStateCode;
	}
	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode== null ? null : orderStateCode.trim();
	}
	
}
