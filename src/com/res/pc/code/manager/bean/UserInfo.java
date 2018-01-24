/**
 * 
 */
package com.res.pc.code.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuqiang
 *	用户登录请求info
 */
public class UserInfo implements Serializable
{    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String userid;
private String username;//帐号
private Integer userlb;
private String name; //姓名
private String tel; //电话
private String password;
private String openid="";
private String iccard;//身份证号
private String email;//邮箱
private String userpic;
private String datetime;
private String error;
private String U_Llevel;
private String Fproxy;
private String recommendId;
private String ending_balance;
private String area_id;
private Integer FStatus;
private String PCityname;

public String getPCityname() {
	return PCityname;
}
public void setPCityname(String pCityname) {
	PCityname = pCityname;
}
public String getFproxy() {
	return Fproxy;
}
public void setFproxy(String fproxy) {
	Fproxy = fproxy;
}
public String getError() {
    return error;
}
public void setError(String error) {
    this.error = error;
}
public String getDatetime() {
    return datetime;
}
public void setDatetime(String datetime) {
    this.datetime = datetime;
}
public String getUserid() {
    return userid;
}
public void setUserid(String userid) {
    this.userid = userid;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}

public Integer getUserlb() {
	return userlb;
}
public void setUserlb(Integer userlb) {
	this.userlb = userlb;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getTel() {
    return tel;
}
public void setTel(String tel) {
    this.tel = tel;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getOpenid() {
    return openid;
}
public void setOpenid(String openid) {
    this.openid = openid;
}
public String getIccard() {
    return iccard;
}
public void setIccard(String iccard) {
    this.iccard = iccard;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getUserpic() {
    return userpic;
}
public void setUserpic(String userpic) {
    this.userpic = userpic;
}
public String getRecommendId() {
    return recommendId;
}
public void setRecommendId(String recommendId) {
    this.recommendId = recommendId;
}
public String getEnding_balance() {
	return ending_balance;
}
public void setEnding_balance(String ending_balance) {
	this.ending_balance = ending_balance;
}
public String getU_Llevel() {
	return U_Llevel;
}
public void setU_Llevel(String u_Llevel) {
	U_Llevel = u_Llevel;
}
public String getArea_id() {
	return area_id;
}
public void setArea_id(String area_id) {
	this.area_id = area_id;
}
public Integer getFStatus() {
	return FStatus;
}
public void setFStatus(Integer fStatus) {
	FStatus = fStatus;
}



}
