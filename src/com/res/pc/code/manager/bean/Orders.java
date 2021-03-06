﻿package com.res.pc.code.manager.bean;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	private String FBillID;
	private String FOrder_Id;
	private String FSource_Id;	
	private String  FSupplier_Id; 
	private String  User_id;	
	private String  FW_Id;
	private String  FS_J_Id	;
	private String  FS_Date;
	private String FYf;	
	private String FSl;	
	private String FZl;
	private String Fje;
	private String FZdy;
	private String  FJsr;
	private String Fstatus;
	private String FRemark;
	private String FClass;
	private String FAddress;
	private String FSname;
	private String Ftel;
	private String begintime,endtime;
	private String FpayWay;
 private List <OrdersItem> list;
 private List<Product> prodList;
 private String address_id;
 private  String Province,City,County;
 private  String Fdiscount;
 private   DeliveryBean deliveryBean;
	private String name;
	private String username;
	private String mark;
	
 public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//是否升级 1是是，2是否（复销）
 private String shengji;
 private String FP_Id;
 
 
 
	public String getFP_Id() {
	return FP_Id;
}
public void setFP_Id(String fP_Id) {
	FP_Id = fP_Id;
}
	public DeliveryBean getDeliveryBean() {
	return deliveryBean;
}
public void setDeliveryBean(DeliveryBean deliveryBean) {
	this.deliveryBean = deliveryBean;
}
	public String getShengji() {
	return shengji;
}
public void setShengji(String shengji) {
	this.shengji = shengji;
}
	public String getFdiscount() {
	return Fdiscount;
}
public void setFdiscount(String fdiscount) {
	Fdiscount = fdiscount;
}
	public String getProvince() {
	return Province;
}
public void setProvince(String province) {
	Province = province;
}
public String getCity() {
	return City;
}
public void setCity(String city) {
	City = city;
}
public String getCounty() {
	return County;
}
public void setCounty(String county) {
	County = county;
}
	public String getBegintime() {
	return begintime;
}
public void setBegintime(String begintime) {
	this.begintime = begintime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}
	public String getFBillID() {
		return FBillID;
	}
	public void setFBillID(String fBillID) {
		FBillID = fBillID;
	}
	public String getFOrder_Id() {
		return FOrder_Id;
	}
	public void setFOrder_Id(String fOrder_Id) {
		FOrder_Id = fOrder_Id;
	}
	public String getFSource_Id() {
		return FSource_Id;
	}
	public void setFSource_Id(String fSource_Id) {
		FSource_Id = fSource_Id;
	}
	public String getFSupplier_Id() {
		return FSupplier_Id;
	}
	public void setFSupplier_Id(String fSupplier_Id) {
		FSupplier_Id = fSupplier_Id;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getFW_Id() {
		return FW_Id;
	}
	public void setFW_Id(String fW_Id) {
		FW_Id = fW_Id;
	}
	public String getFS_J_Id() {
		return FS_J_Id;
	}
	public void setFS_J_Id(String fS_J_Id) {
		FS_J_Id = fS_J_Id;
	}
	public String getFS_Date() {
		return FS_Date;
	}
	public void setFS_Date(String fS_Date) {
		FS_Date = fS_Date;
	}
	public String getFYf() {
		return FYf;
	}
	public void setFYf(String fYf) {
		FYf = fYf;
	}
	public String getFSl() {
		return FSl;
	}
	public void setFSl(String fSl) {
		FSl = fSl;
	}
	public String getFZl() {
		return FZl;
	}
	public void setFZl(String fZl) {
		FZl = fZl;
	}
	public String getFje() {
		return Fje;
	}
	public void setFje(String fje) {
		Fje = fje;
	}
	public String getFZdy() {
		return FZdy;
	}
	public void setFZdy(String fZdy) {
		FZdy = fZdy;
	}
	public String getFJsr() {
		return FJsr;
	}
	public void setFJsr(String fJsr) {
		FJsr = fJsr;
	}
	public String getFstatus() {
		return Fstatus;
	}
	public void setFstatus(String fstatus) {
		Fstatus = fstatus;
	}
	public String getFRemark() {
		return FRemark;
	}
	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}
	public String getFClass() {
		return FClass;
	}
	public void setFClass(String fClass) {
		FClass = fClass;
	}
	public List<OrdersItem> getList() {
		return list;
	}
	public void setList(List<OrdersItem> list) {
		this.list = list;
	}

	public String getFAddress() {
		return FAddress;
	}
	public void setFAddress(String fAddress) {
		FAddress = fAddress;
	}
	public String getFSname() {
		return FSname;
	}
	public void setFSname(String fSname) {
		FSname = fSname;
	}
	public String getFtel() {
		return Ftel;
	}
	public void setFtel(String ftel) {
		Ftel = ftel;
	}
	public List<Product> getProdList() {
		return prodList;
	}
	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getFpayWay() {
		return FpayWay;
	}
	public void setFpayWay(String fpayWay) {
		FpayWay = fpayWay;
	}
	


}
