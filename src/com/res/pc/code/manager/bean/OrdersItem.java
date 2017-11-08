package com.res.pc.code.manager.bean;

import java.io.Serializable;

public class OrdersItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String FBillID;
	private String  FEntryID; 
	private String 	FP_Id ;
	private String 	FP_Price_Id	;
	private String FP_Price;
	private String FP_Num	;
	private String FP_Money;
	public String getFBillID() {
		return FBillID;
	}
	public void setFBillID(String fBillID) {
		FBillID = fBillID;
	}
	public String getFEntryID() {
		return FEntryID;
	}
	public void setFEntryID(String fEntryID) {
		FEntryID = fEntryID;
	}
	public String getFP_Id() {
		return FP_Id;
	}
	public void setFP_Id(String fP_Id) {
		FP_Id = fP_Id;
	}
	public String getFP_Price_Id() {
		return FP_Price_Id;
	}
	public void setFP_Price_Id(String fP_Price_Id) {
		FP_Price_Id = fP_Price_Id;
	}
	public String getFP_Price() {
		return FP_Price;
	}
	public void setFP_Price(String fP_Price) {
		FP_Price = fP_Price;
	}
	public String getFP_Num() {
		return FP_Num;
	}
	public void setFP_Num(String fP_Num) {
		FP_Num = fP_Num;
	}
	public String getFP_Money() {
		return FP_Money;
	}
	public void setFP_Money(String fP_Money) {
		FP_Money = fP_Money;
	}
	

}
