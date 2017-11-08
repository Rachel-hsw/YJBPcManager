package com.res.pc.code.vo;

import java.io.Serializable;

public class NumberCondition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String tcode;
	private String id ;
	private String name;
	private String kssj;
	private String jssj;
	private String warehouseName;
	private String begin;
	private String end;
	private String noware;
	private String isShow="1";
	private String ordercustomer;
	private String deliveryMan;
	
	private String code;
	private String area;
	
	
	
  
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area== null ? null : area.trim();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrdercustomer() {
		return ordercustomer;
	}
	public void setOrdercustomer(String ordercustomer) {
		this.ordercustomer = ordercustomer == null ? null : ordercustomer.trim();
	}
	public String getDeliveryMan() {
		return deliveryMan;
	}
	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan == null ? null : deliveryMan.trim();;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getNoware() {
		return noware;
	}
	public void setNoware(String noware) {
		this.noware = noware== null ? null : noware.trim();
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName== null ? null : warehouseName.trim();
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
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode== null ? null : tcode.trim();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	

}
