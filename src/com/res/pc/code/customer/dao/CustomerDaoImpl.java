/**
 * 
 */
package com.res.pc.code.customer.dao;

import java.sql.SQLClientInfoException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.res.pc.code.customer.bean.QueryCustomerVo;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.vo.PageBean;
import com.res.pc.util.Page;
import com.res.pc.util.PageInfo;

/**
 * 标签DAO
 * @author rlw
 */
@Repository("customerDao")
public class CustomerDaoImpl extends SqlMapClientDaoSupport
{
	@Resource(name = "sqlMapClient")
	private SqlMapClientTemplate sqlMapClient;

	@PostConstruct
	public void initSqlMapClient()
	{
		super.setSqlMapClientTemplate(sqlMapClient);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserInfo>queryCustomerList(QueryCustomerVo vo) throws SQLClientInfoException
	{
		return (List<UserInfo>) sqlMapClient.queryForList("customer.selectCustomerAll", vo);
	}


	public Integer queryCustomerListCount(QueryCustomerVo vo) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClient.queryForObject("customer.selectCustomerListCount", vo);
	}


	public void deleteUserInfo(UserInfo userInfo) {
		sqlMapClient.update("customer.deleteUserInfo", userInfo);
		
	}


	/**
	 * 查询用户详情
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo queryUserInfo(UserInfo info) throws SQLClientInfoException
	{
		return (UserInfo) sqlMapClient.queryForObject("customer.queryUserInfo", info);
	}

	/**
	 * 修改用户
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateUserInfo(UserInfo info) throws SQLClientInfoException
	{
		sqlMapClient.update("customer.updateUserInfo",info);
	}

	/**
	 * 修改密码
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePW(UserInfo info) throws SQLClientInfoException
	{
		sqlMapClient.update("customer.updatePW", info);
	}


	public void updateUserRole(UserInfo info)  throws SQLClientInfoException{
		try{
		 sqlMapClient.update("customer.updateUserRole", info);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	
	
}
