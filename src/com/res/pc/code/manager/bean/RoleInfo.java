/**
 * 
 */
package com.res.pc.code.manager.bean;

/**
 * @author rlw
 *	用户登录请求info
 */
public class RoleInfo
{
	/**
	 * ID
	 */
	private String ID = "";
	
	/**
	 * 角色
	 */
	private String RoleName = "";
	
	/**
	 * 备注
	 */
	private String Remark = "";
	
	/**
	 * 权限
	 */
	private String Power = "";
	/**
	 * 权限
	 */
	private Integer IsMoveby = 0;
	
	/**
	 * 权限
	 */
	private String users = "";
		
	/**
	 *
	 */
	private String userNames = "";
	
	/**
	 *
	 */
	private String PowerNames = "";
	
	
	public String getuserNames()
	{
		return userNames;
	}

	public void setuserNames(String userNames)
	{
		this.userNames = userNames;
	}
	
	public String getPowerNames()
	{
		return PowerNames;
	}

	public void setPowerNames(String PowerNames)
	{
		this.PowerNames = PowerNames;
	}
	
	public String getusers()
	{
		return users;
	}

	public void setusers(String users)
	{
		this.users = users;
	}
	

	public String getRoleName()
	{
		return RoleName;
	}

	public void setRoleName(String RoleName)
	{
		this.RoleName = RoleName;
	}

	public String getRemark()
	{
		return Remark;
	}

	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

	public String getID()
	{
		return ID;
	}

	public void setID(String ID)
	{
		this.ID = ID;
	}

	public String getPower()
	{
		return Power;
	}

	public void setPower(String Power)
	{
		this.Power = Power;
	}
	
	public Integer getIsMoveby()
	{
		return IsMoveby;
	}

	public void setIsMoveby(Integer IsMoveby)
	{
		this.IsMoveby = IsMoveby;
	}
	
}
