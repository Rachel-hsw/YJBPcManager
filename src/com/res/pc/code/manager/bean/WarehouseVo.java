package com.res.pc.code.manager.bean;

public class WarehouseVo {
    private String FId;
    private String FName;
    private String FAddress;
    private String FTel;
    private String FStatus;
    private String FRemark;
    private String begin;
    private String end;
    private  String	currentPage="1";

    public String getFId() {
        return FId;
    }

    public void setFId(String FId) {
        this.FId = FId;
    }

    public String getFStatus() {
        return FStatus;
    }

    public void setFStatus(String FStatus) {
        this.FStatus = FStatus;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFAddress() {
        return FAddress;
    }

    public void setFAddress(String FAddress) {
        this.FAddress = FAddress;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }


    public String getFRemark() {
        return FRemark;
    }

    public void setFRemark(String FRemark) {
        this.FRemark = FRemark;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
