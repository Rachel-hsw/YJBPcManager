package com.res.pc.code.manager.bean;

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
	
 private List <OrdersItem> list;
 private List<Product> prodList;
 
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
	


}
