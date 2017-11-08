package com.res.pc.code.manager.bean;

import java.util.List;



public class MyCondition {
	//入库日期、入库单号、进货单位、进货仓库、制单人、原始单号、采购员、驾驶员、
	private String FS_Date,FOrder_Id,FSupplier_Id,FW_Id,FZdy,FSource_Id,FJsr,FS_J_Id,FRemark,FSl,FZl,FJe;
	private List<PurItemBean> itemsList;
	
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
	
	public String getFJe() {
		return FJe;
	}
	public void setFJe(String fJe) {
		FJe = fJe;
	}
	public String getFS_Date() {
		return FS_Date;
	}
	public void setFS_Date(String fS_Date) {
		FS_Date = fS_Date;
	}
	public String getFOrder_Id() {
		return FOrder_Id;
	}
	public void setFOrder_Id(String fOrder_Id) {
		FOrder_Id = fOrder_Id;
	}
	public String getFSupplier_Id() {
		return FSupplier_Id;
	}
	public void setFSupplier_Id(String fSupplier_Id) {
		FSupplier_Id = fSupplier_Id;
	}
	public String getFW_Id() {
		return FW_Id;
	}
	public void setFW_Id(String fW_Id) {
		FW_Id = fW_Id;
	}
	public String getFZdy() {
		return FZdy;
	}
	public void setFZdy(String fZdy) {
		FZdy = fZdy;
	}
	public String getFSource_Id() {
		return FSource_Id;
	}
	public void setFSource_Id(String fSource_Id) {
		FSource_Id = fSource_Id;
	}
	public String getFJsr() {
		return FJsr;
	}
	public void setFJsr(String fJsr) {
		FJsr = fJsr;
	}
	public String getFS_J_Id() {
		return FS_J_Id;
	}
	public void setFS_J_Id(String fS_J_Id) {
		FS_J_Id = fS_J_Id;
	}
	public String getFRemark() {
		return FRemark;
	}
	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}
	public List<PurItemBean> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<PurItemBean> itemsList) {
		this.itemsList = itemsList;
	}
	
}
