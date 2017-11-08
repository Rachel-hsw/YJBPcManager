/**
 * 
 */
package com.res.pc.code.manager.bean;

/**
 * @author rlw
 *	菜单表
 */
public class InitialNavigationsInfo
{
	/**
	 * NavigationId
	 */
	private String NavigationId = "";
	
	/**
	 * ParentNavigationId
	 */
	private String ParentNavigationId = "";
	
	/**
	 * 名称
	 */
	private String NavigationText = "";
	
	/**
	 * 图片路径
	 */
	private String ImageUrl = "";
	/**
	 * 图片路径
	 */
	private String NavigationUrl = "";
	/**
	 * 图片路径
	 */
	private Integer IsEnabled = 0;
	
	
	
	private String ParentNavigationName = "";
	
	/**
	 * 图片路径
	 */
	private Integer IsMoveby = 0;
	
	
	public String getParentNavigationName()
	{
		return ParentNavigationName;
	}

	public void setParentNavigationName(String ParentNavigationName)
	{
		this.ParentNavigationName = ParentNavigationName;
	}
	
	
	public Integer getIsMoveby()
	{
		return IsMoveby;
	}

	public void setIsMoveby(Integer IsMoveby)
	{
		this.IsMoveby = IsMoveby;
	}
	
	
	
	
	public String getNavigationId()
	{
		return NavigationId;
	}

	public void setNavigationId(String NavigationId)
	{
		this.NavigationId = NavigationId;
	}

	public String getParentNavigationId()
	{
		return ParentNavigationId;
	}

	public void setParentNavigationId(String ParentNavigationId)
	{
		this.ParentNavigationId = ParentNavigationId;
	}

	public String getNavigationText()
	{
		return NavigationText;
	}

	public void setNavigationText(String NavigationText)
	{
		this.NavigationText = NavigationText;
	}

	public String getImageUrl()
	{
		return ImageUrl;
	}

	public void setImageUrl(String ImageUrl)
	{
		this.ImageUrl = ImageUrl;
	}
	
	public String getNavigationUrl()
	{
		return NavigationUrl;
	}

	public void setNavigationUrl(String NavigationUrl)
	{
		this.NavigationUrl = NavigationUrl;
	}
	
	public Integer getIsEnabled()
	{
		return IsEnabled;
	}

	public void setIsEnabled(Integer IsEnabled)
	{
		this.IsEnabled = IsEnabled;
	}
	
}
