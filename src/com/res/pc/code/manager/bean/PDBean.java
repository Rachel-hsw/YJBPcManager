package com.res.pc.code.manager.bean;

import java.util.List;

public class PDBean {
private String FId,	FPDDH	,FW_Id,	FSl,	F_Money	,FZdy,	FPdy,	FShy,FS_Date;
private List<PDItemBean> itemsList;


public List<PDItemBean> getItemsList() {
	return itemsList;
}

public void setItemsList(List<PDItemBean> itemsList) {
	this.itemsList = itemsList;
}

public String getFS_Date() {
	return FS_Date;
}

public void setFS_Date(String fS_Date) {
	FS_Date = fS_Date;
}

public String getFId() {
	return FId;
}

public void setFId(String fId) {
	FId = fId;
}

public String getFPDDH() {
	return FPDDH;
}

public void setFPDDH(String fPDDH) {
	FPDDH = fPDDH;
}

public String getFW_Id() {
	return FW_Id;
}

public void setFW_Id(String fW_Id) {
	FW_Id = fW_Id;
}

public String getFSl() {
	return FSl;
}

public void setFSl(String fSl) {
	FSl = fSl;
}

public String getF_Money() {
	return F_Money;
}

public void setF_Money(String f_Money) {
	F_Money = f_Money;
}

public String getFZdy() {
	return FZdy;
}

public void setFZdy(String fZdy) {
	FZdy = fZdy;
}

public String getFPdy() {
	return FPdy;
}

public void setFPdy(String fPdy) {
	FPdy = fPdy;
}

public String getFShy() {
	return FShy;
}

public void setFShy(String fShy) {
	FShy = fShy;
}




}
