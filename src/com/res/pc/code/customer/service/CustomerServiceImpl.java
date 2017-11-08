/**
 * 
 */
package com.res.pc.code.customer.service;

import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.res.pc.code.customer.bean.QueryCustomerVo;
import com.res.pc.code.customer.dao.CustomerDaoImpl;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.vo.BeginingVo;
import com.res.pc.code.vo.PageBean;
import com.res.pc.util.MD5Util;

/**
 * @author rlw
 * 
 */
@Service("customerService")
public class CustomerServiceImpl
{
	
	/**
	 * 引入Dao
	 */
	@Resource(name = "customerDao")
	private CustomerDaoImpl customerDao;
	
	
	/**
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public PageBean<UserInfo> queryCustomerList(QueryCustomerVo vo) throws SQLClientInfoException
	{      //目的：就是封装一个PageBean 并返回
		PageBean<UserInfo> pageBean=new PageBean();
		pageBean.setCurrentPage(Integer.valueOf(vo.getBegin()));
		pageBean.setCurrentCount(Integer.valueOf(vo.getEnd()));
		Integer totalCount=customerDao.queryCustomerListCount(vo);
		pageBean.setTotalCount(totalCount);
		int totalPage=(totalCount / 20) + 1;
		pageBean.setTotalPage(totalPage);
		vo.setEnd(String.valueOf( Integer.valueOf(vo.getBegin())* 20));
		vo.setBegin(String.valueOf((Integer.valueOf(vo.getBegin()) - 1) * 20+ 1));
		   List<UserInfo> querylist=	 (List<UserInfo>) customerDao.queryCustomerList(vo);
			pageBean.setQueryNumberList(querylist);
		   return pageBean;
		
	}


	public void deleteUserInfo(UserInfo userInfo) {
		customerDao.deleteUserInfo(userInfo);
		
	}


		/**
	 * 查询用户详情
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo queryUserInfo(UserInfo info) throws SQLClientInfoException
	{
		return customerDao.queryUserInfo(info);
	}

	/**
	 * 修改用户信息
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateUserInfo(UserInfo info) throws SQLClientInfoException
	{
		try{
			
		
		customerDao.updateUserInfo(info);
		info.setUserid(info.getUserid()+",");
		customerDao.updateUserRole(info);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 修改密码
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePW(UserInfo info) throws SQLClientInfoException
	{
		info.setPassword(MD5Util.getMD5String(info.getPassword()));
		customerDao.updatePW(info);
	}
	
}
