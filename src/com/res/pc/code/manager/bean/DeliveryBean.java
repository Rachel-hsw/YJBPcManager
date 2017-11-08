package com.res.pc.code.manager.bean;

import java.util.List;

public class DeliveryBean {
	//单据ID	地址ID	快递公司	货运单号	运费	发货人/签收人	发货时间	签收时间	运单图片	备注	发货仓库

	private String FBillID,	FW_ID,	FExpress,	FCourier_Number,	FFreight,	FConsignor,	FDelivery_Time,	FSign_In_Time,	FImg,	FRemark,	FId;
	private List<PurItemBean> itemsList;
	
	public List<PurItemBean> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<PurItemBean> itemsList) {
		this.itemsList = itemsList;
	}

	public String getFBillID() {
		return FBillID;
	}

	public void setFBillID(String fBillID) {
		FBillID = fBillID;
	}

	public String getFW_ID() {
		return FW_ID;
	}

	public void setFW_ID(String fW_ID) {
		FW_ID = fW_ID;
	}

	public String getFExpress() {
		return FExpress;
	}

	public void setFExpress(String fExpress) {
		FExpress = fExpress;
	}

	public String getFCourier_Number() {
		return FCourier_Number;
	}

	public void setFCourier_Number(String fCourier_Number) {
		FCourier_Number = fCourier_Number;
	}

	public String getFFreight() {
		return FFreight;
	}

	public void setFFreight(String fFreight) {
		FFreight = fFreight;
	}

	public String getFConsignor() {
		return FConsignor;
	}

	public void setFConsignor(String fConsignor) {
		FConsignor = fConsignor;
	}

	public String getFDelivery_Time() {
		return FDelivery_Time;
	}

	public void setFDelivery_Time(String fDelivery_Time) {
		FDelivery_Time = fDelivery_Time;
	}

	public String getFSign_In_Time() {
		return FSign_In_Time;
	}

	public void setFSign_In_Time(String fSign_In_Time) {
		FSign_In_Time = fSign_In_Time;
	}

	public String getFImg() {
		return FImg;
	}

	public void setFImg(String fImg) {
		FImg = fImg;
	}

	public String getFRemark() {
		return FRemark;
	}

	public void setFRemark(String fRemark) {
		FRemark = fRemark;
	}

	public String getFId() {
		return FId;
	}

	public void setFId(String fId) {
		FId = fId;
	}

}
