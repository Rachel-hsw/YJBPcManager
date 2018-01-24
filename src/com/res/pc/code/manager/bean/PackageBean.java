package com.res.pc.code.manager.bean;

import java.util.List;

public class PackageBean {
	private String P_Package_id,	P_Package,	P_ZNum,	P_ZMoney,	P_Remark;
	private List<PackageItem> itemsList;

	
	public List<PackageItem> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<PackageItem> itemsList) {
		this.itemsList = itemsList;
	}

	public String getP_Package_id() {
		return P_Package_id;
	}

	public void setP_Package_id(String p_Package_id) {
		P_Package_id = p_Package_id;
	}

	public String getP_Package() {
		return P_Package;
	}

	public void setP_Package(String p_Package) {
		P_Package = p_Package;
	}

	public String getP_ZNum() {
		return P_ZNum;
	}

	public void setP_ZNum(String p_ZNum) {
		P_ZNum = p_ZNum;
	}

	public String getP_ZMoney() {
		return P_ZMoney;
	}

	public void setP_ZMoney(String p_ZMoney) {
		P_ZMoney = p_ZMoney;
	}

	public String getP_Remark() {
		return P_Remark;
	}

	public void setP_Remark(String p_Remark) {
		P_Remark = p_Remark;
	}


}
