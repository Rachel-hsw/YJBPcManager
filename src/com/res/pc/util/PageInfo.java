/**
 * 
 */
package com.res.pc.util;

/**
 * @author liuqiang
 *	分页info
 */
public class PageInfo
{
	/**
	 * 分页条数
	 */
	private Integer pageNum = 10;
	
	/**
	 * 页数
	 */
	private Integer page = 1;
	
	/**
	 * 开始条数
	 */
	private Integer start = 0;
	
	/**
	 * 结束条数
	 */
	private Integer end = 10;
	
	/**
	 * 排序
	 */
	private String order = "";
	
	
	/**
	 * 条件
	 */
	private String query = "";
	
	public String getquery()
	{
		return query;
	}

	public void setquery(String query)
	{
		this.query = query;
	}

	
	
	public Integer getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
