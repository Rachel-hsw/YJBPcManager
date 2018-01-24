package com.res.pc.code.customer.bean;

import java.math.BigDecimal;

public class Amount {
	private String FBillID;	
	private String Fje;
	private String FJe_Y="0.0";
	private String FJe_S;
	
	
	private String FJe_Q="0.0";
	private String FJe_H="0.0";
	private String FDate_First;
	private String FDate_End="";
	private String FRemark="";
	public String getFBillID() {
		return FBillID;
	}
	public void setFBillID(String fBillID) {
		FBillID = fBillID;
	}
	public String getFje() {
		return Fje;
	}
	public void setFje(String fje) {
		Fje = fje;
	}
	public String getFJe_Y() {
		return FJe_Y;
	}
	public void setFJe_Y(String fJe_Y) {
		FJe_Y = fJe_Y;
	}
	public String getJsFJe_S() {
		 BigDecimal b1 = new BigDecimal(Fje);  
	       BigDecimal b2 = new BigDecimal(FJe_Y);   
	      return String.valueOf( b1.subtract(b2).doubleValue());
	}
	public void setFJe_S(String fJe_S) {
		FJe_S = fJe_S;
	}
	
	public String getFJe_S() {
		return FJe_S;
	}
	
	
	public String getFJe_Q() {
		return FJe_Q;
	}
	public void setFJe_Q(String fJe_Q) {
		FJe_Q = fJe_Q;
	}
	public String getFJe_H() {
		return FJe_H;
	}
	public void setFJe_H(String fJe_H) {
		FJe_H = fJe_H;
	}
	public String getFDate_First() {
		return FDate_First;
	}
	public void setFDate_First(String fDate_First) {
		FDate_First = fDate_First;
	}
	public String getFDate_End() {
		return FDate_End;
	}
	public void setFDate_End(String fDate_End) {
		FDate_End = fDate_End;
	}
	public String getFRemark() {
		return FRemark;
	}
	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}
	
	
}
