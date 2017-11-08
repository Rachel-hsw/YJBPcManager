package com.res.pc.code.manager.bean;

public class view_orderdetail {
	//商品id/商品名称/规格型号/单位/单价/数量/重量/金额/备注 
	private String id,FBillID, name ,category,F_Unit  ,price,FP_Num,FP_Zl,FP_Money,FRemark ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFP_Money() {
		return FP_Money;
	}

	public void setFP_Money(String fP_Money) {
		FP_Money = fP_Money;
	}

	public String getFRemark() {
		return FRemark;
	}

	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFBillID() {
		return FBillID;
	}

	public void setFBillID(String fBillID) {
		FBillID = fBillID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getF_Unit() {
		return F_Unit;
	}

	public void setF_Unit(String f_Unit) {
		F_Unit = f_Unit;
	}

	public String getFP_Num() {
		return FP_Num;
	}

	public void setFP_Num(String fP_Num) {
		FP_Num = fP_Num;
	}

	public String getFP_Zl() {
		return FP_Zl;
	}

	public void setFP_Zl(String fP_Zl) {
		FP_Zl = fP_Zl;
	}
	

}
