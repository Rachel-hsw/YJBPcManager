/**
 * 
 */
package com.res.pc.code.manager.bean;

import java.util.Date;

/**
 * @author liuqiang
 *	用户登录请求info
 */
public class UserInfo
{ private String userid;

private String username;

private Integer userlb;

private String name;

private String tel;

private String password;

private String openid;

private String iccard;

private String email;

private Date createtime;

private Date datetime;

private String userpic;
private Integer FStatus;


public Integer getFStatus() {
	return FStatus;
}

public void setFStatus(Integer fStatus) {
	FStatus = fStatus;
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
    this.username = username == null ? null : username.trim();
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
    this.name = name == null ? null : name.trim();
}

public String getTel() {
    return tel;
}

public void setTel(String tel) {
    this.tel = tel == null ? null : tel.trim();
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
}

public String getOpenid() {
    return openid;
}

public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
}

public String getIccard() {
    return iccard;
}

public void setIccard(String iccard) {
    this.iccard = iccard == null ? null : iccard.trim();
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
}

public Date getCreatetime() {
    return createtime;
}

public void setCreatetime(Date createtime) {
    this.createtime = createtime;
}

public Date getDatetime() {
    return datetime;
}

public void setDatetime(Date datetime) {
    this.datetime = datetime;
}

public String getUserpic() {
    return userpic;
}

public void setUserpic(String userpic) {
    this.userpic = userpic == null ? null : userpic.trim();
}

}
