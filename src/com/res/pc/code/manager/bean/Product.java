package com.res.pc.code.manager.bean;

import java.io.Serializable;

public class Product implements Serializable{
	private String id,FNum_E;
	private String name;
	private String price;
	private String category;
	private int pnum;
	private String imgurl;
	private String description;
	private String F_Unit;
	private String isCollection="0";
	private String  FStatus;
	private String F_Num;
	private String F_Zl;
	private String divID;
	private String  divName;
	
	public String getDivID() {
		return divID;
	}
	public void setDivID(String divID) {
		this.divID = divID;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFNum_E() {
		return FNum_E;
	}
	public void setFNum_E(String fNum_E) {
		FNum_E = fNum_E;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getF_Unit() {
		return F_Unit;
	}
	public void setF_Unit(String f_Unit) {
		F_Unit = f_Unit;
	}
	public String getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(String isCollection) {
		this.isCollection = isCollection;
	}
	public String getFStatus() {
		return FStatus;
	}
	public void setFStatus(String fStatus) {
		FStatus = fStatus;
	}
	public String getF_Num() {
		return F_Num;
	}
	public void setF_Num(String f_Num) {
		F_Num = f_Num;
	}
	public String getF_Zl() {
		return F_Zl;
	}
	public void setF_Zl(String f_Zl) {
		F_Zl = f_Zl;
	}
	public String getImgurls() {
		return imgurl.substring(0,imgurl.lastIndexOf("."))
						+"_s"
							+imgurl.substring(imgurl.lastIndexOf("."));
	}

}
