/**
 * 
 */
package com.res.pc.code.customer.dao;

import java.sql.SQLClientInfoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.res.pc.code.customer.bean.Amount;
import com.res.pc.code.customer.bean.Examine;
import com.res.pc.code.customer.bean.QueryCustomerReate;
import com.res.pc.code.customer.bean.QueryCustomerVo;
import com.res.pc.code.customer.bean.QueryExamineVo;
import com.res.pc.code.customer.bean.Rebate;
import com.res.pc.code.customer.bean.RebateSetting;
import com.res.pc.code.manager.bean.Area;
import com.res.pc.code.manager.bean.Orders;
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
	
	
	@SuppressWarnings("unchecked")
	public List<Examine>queryExamineList(QueryExamineVo vo) throws SQLClientInfoException
	{
		return (List<Examine>) sqlMapClient.queryForList("customer.selectExamineAll", vo);
	}


	public Integer queryExamineListCount(QueryExamineVo vo) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClient.queryForObject("customer.selectExamineAllCount", vo);
	}
	
	public Examine queryExamineInfo(String  FExamine_id) throws SQLClientInfoException
	{
		return (Examine) sqlMapClient.queryForObject("customer.queryExamineInfo", FExamine_id);
	}
	public void updateExamine(Examine info)  throws SQLClientInfoException{
		try{
		 sqlMapClient.update("customer.updateExamine", info);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<RebateSetting>	queryRebateSetting (String  type) throws SQLClientInfoException
	{
		return (List<RebateSetting>) sqlMapClient.queryForList("customer.queryRebateSetting", type);
	}
	
	@SuppressWarnings("unchecked")
	public RebateSetting queryRebateSettingInfo (String  type,String lv) throws SQLClientInfoException
	
	{
		Map map = new HashMap();
        map.put("Ftype", type);
        map.put("Lv", lv);
		return (RebateSetting) sqlMapClient.queryForList("customer.queryRebateSettingInfo", map);
	}
	
	
	
	public Orders findOrderById(String  order_id) throws SQLClientInfoException
	{
		return (Orders) sqlMapClient.queryForObject("customer.findOrderById", order_id);
	}


	public Area findProxy(String area_id) {
		
		return (Area) sqlMapClient.queryForObject("customer.findProxy", area_id);
		
	}


	public void addRebate(Rebate userRebate) throws SQLClientInfoException {
		 sqlMapClient.update("customer.addRebate", userRebate);	
	}


	public void updateAccount(Rebate userRebate) throws SQLClientInfoException {
		sqlMapClient.update("customer.updateAccount", userRebate);	
		
	}


	public void changePayStateandPayway(String fEorder_id, int i, String paywayNumber,String Fdiscount,String Fje) throws SQLClientInfoException {
		Map map = new HashMap();
        map.put("Fstatus", i);
        map.put("payway", paywayNumber);
        map.put("FOrder_Id", fEorder_id);
        map.put("Fdiscount", Fdiscount);
        map.put("Fje", Fje);
		sqlMapClient.update("customer.changePayStateandPayway", map);
		
	}


	public void updatelevel(String Fproxy, String userid)  throws SQLClientInfoException{
		Map map = new HashMap();
        map.put("Fproxy", Fproxy);
        map.put("userid", userid);
		sqlMapClient.update("customer.updatelevel", map);
		
	}


	public void updateAmount(Amount amount) throws SQLClientInfoException {
		sqlMapClient.update("customer.updateAmount", amount);
		
	}


	public void updateUserendbalance(String user_id, String fJe_H) throws SQLClientInfoException {
		Map map = new HashMap();
        map.put("ending_balance", fJe_H);
        map.put("userid", user_id);
		sqlMapClient.update("customer.updateUserendbalance", map);
		
	}


	public void updateAreae(String fUser_Id,String id) throws SQLClientInfoException  {
		Map map = new HashMap();
        map.put("Fuserid", fUser_Id);
        map.put("id", id);
		sqlMapClient.update("customer.updateAreae", map);
		
	}
public Examine queryExamine(String FExamine_id) {
		
		return (Examine) sqlMapClient.queryForObject("customer.queryExamine", FExamine_id);
		
	}


@SuppressWarnings("unchecked")
public List<Orders>	findReateOrder (QueryCustomerReate vo) throws SQLClientInfoException
{
	return (List<Orders>) sqlMapClient.queryForList("customer.findReateOrder", vo);
}

@SuppressWarnings("unchecked")
public List<Rebate> findReateOrderInfo(String Forder_id) {
	
	return (List<Rebate>) sqlMapClient.queryForList("customer.findReateOrderInfo", Forder_id);
	
}

	
}
