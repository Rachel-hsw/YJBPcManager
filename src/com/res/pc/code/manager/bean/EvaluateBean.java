package com.res.pc.code.manager.bean;

import java.util.Date;

/**
 *  Evaluate 评价
 */
public class EvaluateBean {

    private int FID;
    private String FDelivery_Time;
    private String FDateTime;
    private String FRemark;
    private String evaluate1;
    private String evaluate2;
    private String evaluate3;
    private String evaluate4;
    private String FBillID;
    private String Fuser_id;
    private String Fprod_id;

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public String getFDelivery_Time() {
        return FDelivery_Time;
    }

    public void setFDelivery_Time(String FDelivery_Time) {
        this.FDelivery_Time = FDelivery_Time;
    }

    public String getFDateTime() {
        return FDateTime;
    }

    public void setFDateTime(String FDateTime) {
        this.FDateTime = FDateTime;
    }

    public String getFRemark() {
        return FRemark;
    }

    public void setFRemark(String FRemark) {
        this.FRemark = FRemark;
    }

    public String getEvaluate1() {
        return evaluate1;
    }

    public void setEvaluate1(String evaluate1) {
        this.evaluate1 = evaluate1;
    }

    public String getEvaluate2() {
        return evaluate2;
    }

    public void setEvaluate2(String evaluate2) {
        this.evaluate2 = evaluate2;
    }

    public String getEvaluate3() {
        return evaluate3;
    }

    public void setEvaluate3(String evaluate3) {
        this.evaluate3 = evaluate3;
    }

    public String getEvaluate4() {
        return evaluate4;
    }

    public void setEvaluate4(String evaluate4) {
        this.evaluate4 = evaluate4;
    }

    public String getFBillID() {
        return FBillID;
    }

    public void setFBillID(String FBillID) {
        this.FBillID = FBillID;
    }

    public String getFuser_id() {
        return Fuser_id;
    }

    public void setFuser_id(String fuser_id) {
        Fuser_id = fuser_id;
    }

    public String getFprod_id() {
        return Fprod_id;
    }

    public void setFprod_id(String fprod_id) {
        Fprod_id = fprod_id;
    }

    @Override
    public String toString() {
        return "EvaluateBean{" +
                "FID=" + FID +
                ", FDelivery_Time='" + FDelivery_Time + '\'' +
                ", FDateTime='" + FDateTime + '\'' +
                ", FRemark='" + FRemark + '\'' +
                ", evaluate1='" + evaluate1 + '\'' +
                ", evaluate2='" + evaluate2 + '\'' +
                ", evaluate3='" + evaluate3 + '\'' +
                ", evaluate4='" + evaluate4 + '\'' +
                ", FBillID='" + FBillID + '\'' +
                ", Fuser_id='" + Fuser_id + '\'' +
                ", Fprod_id='" + Fprod_id + '\'' +
                '}';
    }
}
