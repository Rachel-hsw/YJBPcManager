package com.res.pc.code.manager.bean;

/**
 * 仓库管理实体类
 * @author mz
 * @date: 2017:10:24 15:21
 */
public class WarehouseEntity {
    private Long FId;
    private String FName;
    private String FAddress;
    private String FTel;
    private int FStatus;
    private String FRemark;

    public long getFId() {
        return FId;
    }

    public void setFId(long FId) {
        this.FId = FId;
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

    public int getFStatus() {
        return FStatus;
    }

    public void setFStatus(int FStatus) {
        this.FStatus = FStatus;
    }

    public String getFRemark() {
        return FRemark;
    }

    public void setFRemark(String FRemark) {
        this.FRemark = FRemark;
    }
}
