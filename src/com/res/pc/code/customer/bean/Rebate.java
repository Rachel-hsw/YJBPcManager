package com.res.pc.code.customer.bean;

import java.io.Serializable;

public class Rebate implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Fid;	
	private String FUser_Id	;
	private String FOrder_ID;
	private String FIntegral;
	private String FRebate	;
	private String FAmount;	
	private String FGain;
	private String  voucher;
	private String Fbeging_balance;
	private String Fe_vouche="0";
	private String Fbalance;
	private String FRemark;//如果是公司总账，给0
	private String FRebate_name;//一级推荐奖  省区域福利奖
	private String name;//用户名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFRebate_name() {
		return FRebate_name;
	}
	public void setFRebate_name(String fRebate_name) {
		FRebate_name = fRebate_name;
	}
	public String getFRemark() {
		return FRemark;
	}
	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}
	public String getFbalance() {
		return Fbalance;
	}
	public void setFbalance(String fbalance) {
		Fbalance = fbalance;
	}
	public String getFe_vouche() {
		return Fe_vouche;
	}
	public void setFe_vouche(String fe_vouche) {
		Fe_vouche = fe_vouche;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public String getFid() {
		return Fid;
	}
	public void setFid(String fid) {
		Fid = fid;
	}
	public String getFUser_Id() {
		return FUser_Id;
	}
	public void setFUser_Id(String fUser_Id) {
		FUser_Id = fUser_Id;
	}
	public String getFOrder_ID() {
		return FOrder_ID;
	}
	public void setFOrder_ID(String fOrder_ID) {
		FOrder_ID = fOrder_ID;
	}
	public String getFIntegral() {
		return FIntegral;
	}
	public void setFIntegral(String fIntegral) {
		FIntegral = fIntegral;
	}
	public String getFRebate() {
		return FRebate;
	}
	public void setFRebate(String fRebate) {
		FRebate = fRebate;
	}
	public String getFAmount() {
		return FAmount;
	}
	public void setFAmount(String fAmount) {
		FAmount = fAmount;
	}
	public String getFGain() {
		return FGain;
	}
	public void setFGain(String fGain) {
		FGain = fGain;
	}
	public String getFbeging_balance() {
		return Fbeging_balance;
	}
	public void setFbeging_balance(String fbeging_balance) {
		Fbeging_balance = fbeging_balance;
	}
	


}
