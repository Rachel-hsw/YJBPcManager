package com.res.pc.code.customer.bean;

import java.io.Serializable;
//申请代理的表
public class Examine  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String FExamine_id;
	private String FUser_Id;
	private String FLevel;
	private String Farea_code;
	private String FCompnay_id="";
	private String FBatch;
	private String FPic;
	private String FMoney;
	private String FCreatedate;
	private String FDatetime;
	private String FRemark="";
	private String username;
	private String tel;
	private String Faccount_Name,
	
	FOpen_Account,
	FCart_Number,Forderid;
	private String FStatus;
	private  String PCityname;
	private String FEorder_id;
	public String getFStatus() {
		return FStatus;
	}
	public void setFStatus(String fStatus) {
		FStatus = fStatus;
	}
	public String getFExamine_id() {
		return FExamine_id;
	}
	public void setFExamine_id(String fExamine_id) {
		FExamine_id = fExamine_id;
	}
	public String getFUser_Id() {
		return FUser_Id;
	}
	public void setFUser_Id(String fUser_Id) {
		FUser_Id = fUser_Id;
	}
	public String getFLevel() {
		return FLevel;
	}
	public void setFLevel(String fLevel) {
		FLevel = fLevel;
	}
	public String getFarea_code() {
		return Farea_code;
	}
	public void setFarea_code(String farea_code) {
		Farea_code = farea_code;
	}
	public String getFCompnay_id() {
		return FCompnay_id;
	}
	public void setFCompnay_id(String fCompnay_id) {
		FCompnay_id = fCompnay_id;
	}
	public String getFBatch() {
		return FBatch;
	}
	public void setFBatch(String fBatch) {
		FBatch = fBatch;
	}
	public String getFPic() {
		return "http://www.szyjb.net"+FPic;
	}
	public void setFPic(String fPic) {
		FPic = fPic;
	}
	
	public String getFMoney() {
		return FMoney;
	}
	public void setFMoney(String fMoney) {
		FMoney = fMoney;
	}
	public String getFCreatedate() {
		return FCreatedate;
	}
	public void setFCreatedate(String fCreatedate) {
		FCreatedate = fCreatedate;
	}
	public String getFDatetime() {
		return FDatetime;
	}
	public void setFDatetime(String fDatetime) {
		FDatetime = fDatetime;
	}
	public String getFRemark() {
		return FRemark;
	}
	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFaccount_Name() {
		return Faccount_Name;
	}
	public void setFaccount_Name(String faccount_Name) {
		Faccount_Name = faccount_Name;
	}
	public String getFOpen_Account() {
		return FOpen_Account;
	}
	public void setFOpen_Account(String fOpen_Account) {
		FOpen_Account = fOpen_Account;
	}
	public String getFCart_Number() {
		return FCart_Number;
	}
	public void setFCart_Number(String fCart_Number) {
		FCart_Number = fCart_Number;
	}
	public String getPCityname() {
		return PCityname;
	}
	public void setPCityname(String pCityname) {
		PCityname = pCityname;
	}
	public String getForderid() {
		return Forderid;
	}
	public void setForderid(String forderid) {
		Forderid = forderid;
	}

	
}
