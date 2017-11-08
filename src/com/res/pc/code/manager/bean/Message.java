package com.res.pc.code.manager.bean;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
private String 	FId	,FTel,	FMsg,	FDatetime,	FType,	Fstatus;

public String getFId() {
	return FId;
}

public void setFId(String fId) {
	FId = fId;
}


public String getFTel() {
	return FTel;
}

public void setFTel(String fTel) {
	FTel = fTel;
}

public String getFMsg() {
	return FMsg;
}

public void setFMsg(String fMsg) {
	FMsg = fMsg;
}

public String getFDatetime() {
	return FDatetime;
}

public void setFDatetime(String fDatetime) {
	FDatetime = fDatetime;
}

public String getFType() {
	return FType;
}

public void setFType(String fType) {
	FType = fType;
}

public String getFstatus() {
	return Fstatus;
}

public void setFstatus(String fstatus) {
	Fstatus = fstatus;
}

}
