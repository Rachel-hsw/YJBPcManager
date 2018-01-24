/**
 * 
 */
package com.res.pc.code.customer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLClientInfoException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.res.pc.code.customer.bean.Amount;
import com.res.pc.code.customer.bean.Examine;
import com.res.pc.code.customer.bean.QueryCustomerReate;
import com.res.pc.code.customer.bean.QueryCustomerVo;
import com.res.pc.code.customer.bean.QueryExamineVo;
import com.res.pc.code.customer.bean.Rebate;
import com.res.pc.code.customer.bean.RebateSetting;
import com.res.pc.code.customer.dao.CustomerDaoImpl;
import com.res.pc.code.manager.bean.Area;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.UserInfo;
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
	 * 查询用户信息
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
	
	

	@SuppressWarnings("unchecked")
	public PageBean<Examine> queryExamineList(QueryExamineVo vo) throws SQLClientInfoException
	{      //目的：就是封装一个PageBean 并返回
		PageBean<Examine> pageBean=new PageBean();
		pageBean.setCurrentPage(Integer.valueOf(vo.getBegin()));
		pageBean.setCurrentCount(Integer.valueOf(vo.getEnd()));
		Integer totalCount=customerDao.queryExamineListCount(vo);
		pageBean.setTotalCount(totalCount);
		int totalPage=(totalCount / 20) + 1;
		pageBean.setTotalPage(totalPage);
		vo.setEnd(String.valueOf( Integer.valueOf(vo.getBegin())* 20));
		vo.setBegin(String.valueOf((Integer.valueOf(vo.getBegin()) - 1) * 20+ 1));
		   List<Examine> querylist=	 (List<Examine>) customerDao.queryExamineList(vo);
			pageBean.setQueryNumberList(querylist);
		   return pageBean;
		
	}
	
	public Examine queryExamineInfo(String  FExamine_id) throws SQLClientInfoException
	{
		return customerDao.queryExamineInfo(FExamine_id);
	}
	
	//代理审核后的操作
	public void updateExamine(Examine info)  throws SQLClientInfoException{
		if(!"2".equals(info.getFStatus())){
			Examine info1=customerDao.queryExamine(info.getFExamine_id());
			Orders payorder = customerDao.findOrderById(info1.getForderid());
			String paywayNumber="4";
			UserInfo user=new UserInfo();
			user.setUserid(info1.getFUser_Id());
			 user = customerDao.queryUserInfo(user);
			 
			/* BigDecimal zcompanyRebate = new BigDecimal("0.0");*/
			 List<RebateSetting> rs=null;
				BigDecimal rebatemoney=null;
				BigDecimal discount =null;
				rs=customerDao.queryRebateSetting("3");
				RebateSetting userrs = rs.get(0);
				 discount = new BigDecimal(userrs.getRate());
			 	rebatemoney=new BigDecimal(payorder.getFje());
			 	rebatemoney=rebatemoney.multiply(discount);
				Rebate userRebate = new Rebate();
				userRebate.setFUser_Id(user.getUserid());
				userRebate.setFIntegral(rebatemoney+"");
				userRebate.setFRebate("0");
				userRebate.setFGain(rebatemoney+"");
				userRebate.setFOrder_ID(payorder.getFBillID());
				userRebate.setFAmount(rebatemoney+"");
				userRebate.setFe_vouche(rebatemoney+"");
				userRebate.setFRebate_name("代理付款");
				customerDao.addRebate(userRebate);
				userRebate.setFAmount("0");
				customerDao.updateAccount(userRebate);
				// 推荐人
				Rebate recommendUserRebate = new Rebate();
				if (null != user.getRecommendId() && !"".equals(user.getRecommendId())&&!"0".equals(user.getRecommendId())) {
					recommendUserRebate.setFUser_Id(user.getRecommendId());
				}else{
					recommendUserRebate.setFUser_Id("0");
				}
					RebateSetting recommendUsers = rs.get(1);
					BigDecimal rdiscount = new BigDecimal(recommendUsers.getRate());
				
					recommendUserRebate.setFIntegral("0");
					recommendUserRebate.setFRebate( rebatemoney.multiply(rdiscount).doubleValue() + "");
					recommendUserRebate.setFGain(rebatemoney.multiply(rdiscount).doubleValue() + "");
					recommendUserRebate.setFOrder_ID(payorder.getFBillID());
					recommendUserRebate.setFAmount(rebatemoney+"");
					recommendUserRebate.setFRebate_name("一级推荐奖："+rdiscount.doubleValue()*100+"%");
					customerDao.addRebate(recommendUserRebate);
					recommendUserRebate.setFAmount("0");
					customerDao.updateAccount(recommendUserRebate);
				/*	zcompanyRebate=zcompanyRebate.add(rdiscount); */
				
				// 二级推荐人
				UserInfo Recommenduser1=new UserInfo();
				Recommenduser1.setUserid(user.getRecommendId());
				UserInfo Recommenduser  = customerDao.queryUserInfo(Recommenduser1);
				if (null != Recommenduser.getRecommendId() && !"".equals(Recommenduser.getRecommendId())&&!"0".equals(Recommenduser.getRecommendId())) {
					recommendUserRebate.setFUser_Id(Recommenduser.getRecommendId());
				}else{
					recommendUserRebate.setFUser_Id("0");
				}
					RebateSetting secondrecommendUsers = rs.get(2);
					BigDecimal sdiscount = new BigDecimal(secondrecommendUsers.getRate());
		
					recommendUserRebate.setFRebate(rebatemoney.multiply(sdiscount).doubleValue()+ "");
					recommendUserRebate.setFGain(rebatemoney.multiply(sdiscount).doubleValue()+ "");
					recommendUserRebate.setFAmount(rebatemoney+"");
					recommendUserRebate.setFRebate_name("二级推荐奖："+sdiscount.doubleValue()*100+"%");
					recommendUserRebate.setFOrder_ID(payorder.getFBillID());
					customerDao.addRebate(recommendUserRebate);
					recommendUserRebate.setFAmount("0");
					customerDao.updateAccount(recommendUserRebate);
				/*	zcompanyRebate=zcompanyRebate.add(sdiscount); */
				
		        /*---------------------申请县代理-----------------------------**/
		        if("3".equals(info1.getFLevel())){
				Area area = customerDao.findProxy(info1.getFarea_code());
				Area cityArea = customerDao.findProxy(area.getPparent_id());
				Area provinceArea  = customerDao.findProxy(cityArea.getPparent_id());
				
				/*-----------------------------会员成为区／县代理后，
				 * 其业务区域如果有上级市代理，
				 * 则市代理获得代理预付款的20%的区域代理福利奖，
				 * 省代理（若缺失用公司代替）获得5%的区域代理福利奖；------------------------------------------------*/
				if(!"".equals(cityArea.getFuserid())&&null!=cityArea.getFuserid()){
					Rebate cityproxyRebate = new Rebate();
					
					cityproxyRebate.setFIntegral("0");
					cityproxyRebate.setFUser_Id(cityArea.getFuserid());
					RebateSetting  rscity=rs.get(3);
					BigDecimal b = new BigDecimal(rscity.getRate());
					cityproxyRebate.setFRebate(rebatemoney.multiply(b).doubleValue()+"");
						cityproxyRebate.setFGain(rebatemoney.multiply(b).doubleValue()+"");
						/*zcompanyRebate=zcompanyRebate.add(b); */
						cityproxyRebate.setFAmount(rebatemoney+"");
						cityproxyRebate.setFOrder_ID(payorder.getFBillID());
						cityproxyRebate.setFRebate_name("福利奖："+b.doubleValue()*100+"%");
						customerDao.addRebate(cityproxyRebate);
						cityproxyRebate.setFAmount("0");
						customerDao.updateAccount(cityproxyRebate);
						Rebate provinceRebate = new Rebate(); 
					 if(!"".equals(provinceArea.getFuserid())&&null!=provinceArea.getFuserid()){
						provinceRebate.setFUser_Id(provinceArea.getFuserid());
					}else{
						provinceRebate.setFUser_Id("0");
					}
					 
						provinceRebate.setFIntegral("0");
						RebateSetting  rs1=rs.get(4);
						
						BigDecimal b1 = new BigDecimal(rs1.getRate());
						provinceRebate.setFRebate(rebatemoney.multiply(b1).doubleValue()+"");
						provinceRebate.setFGain(rebatemoney.multiply(b1).doubleValue()+"");
						provinceRebate.setFOrder_ID(payorder.getFBillID());
					/*	zcompanyRebate=zcompanyRebate.add(b1);*/
						provinceRebate.setFAmount(rebatemoney+"");
						provinceRebate.setFRebate_name("福利奖："+b1.doubleValue()*100+"%");
						customerDao.addRebate(provinceRebate);
						provinceRebate.setFAmount("0");
						customerDao.updateAccount(provinceRebate);
				}else{
					/*
					 * 其业务区域如果没有市代理，则其上级省代理（若缺失用公司代替）获得20%的区域代理福利奖，公司获得5%的区域代理福利奖。
					 * 
					 * */
					Rebate provinceRebate = new Rebate(); 
					if(!"".equals(provinceArea.getFuserid())&&null!=provinceArea.getFuserid()){
						provinceRebate.setFUser_Id(provinceArea.getFuserid());
					}else{
						provinceRebate.setFUser_Id("0");
					}
					/****  - -----------------------------------------------------------------------                   **/
				
				
					provinceRebate.setFAmount("0");
					provinceRebate.setFIntegral("0");
					RebateSetting  rs1=rs.get(3);
					BigDecimal b1 = new BigDecimal(rs1.getRate());
					provinceRebate.setFRebate(rebatemoney.multiply(b1).doubleValue()+"");
					provinceRebate.setFGain(rebatemoney.multiply(b1).doubleValue()+"");
					provinceRebate.setFOrder_ID(payorder.getFBillID());
					/*zcompanyRebate=zcompanyRebate.add(b1);*/
					provinceRebate.setFAmount(rebatemoney+"");
					provinceRebate.setFRebate_name("福利奖："+b1.doubleValue()*100+"%");
					customerDao.addRebate(provinceRebate);
					provinceRebate.setFAmount("0");
					customerDao.updateAccount(provinceRebate);
					/********-----------公司获得5%的区域代理福利奖。---------*********/
					Rebate 		companyRebate = new Rebate();
					companyRebate.setFUser_Id("0");
					companyRebate.setFIntegral("0");
					RebateSetting  rs2=rs.get(4);
					
					BigDecimal b2 = new BigDecimal(rs2.getRate());
					companyRebate.setFRebate(rebatemoney.multiply(b2).doubleValue()+"");
					companyRebate.setFGain(rebatemoney.multiply(b2).doubleValue()+"");
					companyRebate.setFOrder_ID(payorder.getFBillID());
				/*	zcompanyRebate=zcompanyRebate.add(b2);*/
					companyRebate.setFAmount(rebatemoney+"");
					companyRebate.setFRebate_name("福利奖："+b2.doubleValue()*100+"%");
					customerDao.addRebate(companyRebate);
					companyRebate.setFAmount("0");
					customerDao.updateAccount(companyRebate);
					
					
					
					
					
					
				}
				
			} 
		        /*---------------------申请市代理-----------------------------**/
		        else if("2".equals(info1.getFLevel())){
		        	
		        	
		        	/*
		        	 * 
		        	 * 
		        	 * 
		        	 * L.会员成为市代理后，
		        	 * 其业务区域省代理（若缺失用公司代替）获得代理预付款的20%区域代理福利奖，
		        	 * 公司获得5%的区域代理福利奖；
		        	 * 否则公司获得（20+5）%的区域代理福利奖
		        	 * */
				Area area = customerDao.findProxy(info1.getFarea_code());
				Area provinceArea = customerDao.findProxy(area.getPparent_id());
				Rebate provinceRebate = new Rebate(); 
				if(!"".equals(provinceArea.getFuserid())&&null!=provinceArea.getFuserid()){
					provinceRebate.setFUser_Id(provinceArea.getFuserid());
				}else{
					provinceRebate.setFUser_Id("0");
				}
				provinceRebate.setFAmount("0");
				provinceRebate.setFIntegral("0");
				RebateSetting  rs1=rs.get(3);
				BigDecimal b1 = new BigDecimal(rs1.getRate());
				provinceRebate.setFRebate(rebatemoney.multiply(b1).doubleValue()+"");
				provinceRebate.setFGain(rebatemoney.multiply(b1).doubleValue()+"");
				provinceRebate.setFOrder_ID(payorder.getFBillID());
			/*	zcompanyRebate=zcompanyRebate.add(b1);*/
				provinceRebate.setFAmount(rebatemoney+"");
				provinceRebate.setFRebate_name("福利奖："+b1.doubleValue()*100+"%");
				customerDao.addRebate(provinceRebate);
				provinceRebate.setFAmount("0");
				customerDao.updateAccount(provinceRebate);
				
				Rebate 		companyRebate = new Rebate();
				companyRebate.setFUser_Id("0");
				companyRebate.setFIntegral("0");
				RebateSetting  rs2=rs.get(4);
				BigDecimal b2 = new BigDecimal(rs2.getRate());
				companyRebate.setFRebate(rebatemoney.multiply(b2).doubleValue()+"");
				companyRebate.setFGain(rebatemoney.multiply(b2).doubleValue()+"");
				companyRebate.setFOrder_ID(payorder.getFBillID());
				/*zcompanyRebate=zcompanyRebate.add(b2);*/
				companyRebate.setFAmount(rebatemoney+"");
				companyRebate.setFRebate_name("福利奖："+b2.doubleValue()*100+"%");
				customerDao.addRebate(companyRebate);
				companyRebate.setFAmount("0");
				customerDao.updateAccount(companyRebate);
				
				
			}
		        /*---------------------申请省代理-----------------------------**/
		        else if("1".equals(info1.getFLevel())){
		        	Rebate 		companyRebate = new Rebate();
					
		        	companyRebate.setFIntegral("0");
		        	companyRebate.setFUser_Id("0");
					RebateSetting  rscity=rs.get(3);
					
					BigDecimal b = new BigDecimal(rscity.getRate());
					companyRebate.setFRebate(rebatemoney.multiply(b).doubleValue()+"");
					companyRebate.setFGain(rebatemoney.multiply(b).doubleValue()+"");
					/*	zcompanyRebate=zcompanyRebate.add(b); */
						companyRebate.setFAmount(rebatemoney+"");
						companyRebate.setFOrder_ID(payorder.getFBillID());
						companyRebate.setFRebate_name("福利奖："+b.doubleValue()*100+"%");
						customerDao.addRebate(companyRebate);
						companyRebate.setFAmount("0");
						customerDao.updateAccount(companyRebate);
						companyRebate.setFUser_Id("0");
						companyRebate.setFIntegral("0");
						RebateSetting  rs1=rs.get(4);
						
						BigDecimal b1 = new BigDecimal(rs1.getRate());
						companyRebate.setFRebate(rebatemoney.multiply(b1).doubleValue()+"");
						companyRebate.setFGain(rebatemoney.multiply(b1).doubleValue()+"");
						companyRebate.setFOrder_ID(payorder.getFBillID());
						/*zcompanyRebate=zcompanyRebate.add(b1);*/
						companyRebate.setFAmount(rebatemoney+"");
						companyRebate.setFRebate_name("福利奖："+b1.doubleValue()*100+"%");
						customerDao.addRebate(companyRebate);
						companyRebate.setFAmount("0");
						customerDao.updateAccount(companyRebate);
				}
		   /* 	// 公司
				Rebate companyRebate = new Rebate();
				BigDecimal z = new BigDecimal(1+"");  
							companyRebate.setFUser_Id("0");
				companyRebate.setFIntegral(payorder.getFje());
				companyRebate.setFRebate(rebatemoney.multiply(zcompanyRebate.add(z)).doubleValue()+ "");
				companyRebate.setFAmount(rebatemoney + "");
				companyRebate.setFGain("0");
				companyRebate.setFOrder_ID(payorder.getFBillID());
				companyRebate.setFRemark("0");
				companyRebate.setFRebate_name("公司返利");
				customerDao.addRebate(companyRebate);
				customerDao.updateAccount(companyRebate); */
			Amount amount = new Amount();
			amount.setFje(payorder.getFje());
			amount.setFJe_Y(payorder.getFje());
			BigDecimal b2 = new BigDecimal(user.getEnding_balance());
			double fje_q = b2.doubleValue();
			amount.setFJe_Q(String.valueOf(fje_q));
			BigDecimal b1 = new BigDecimal(amount.getFJe_Y());
			String FJe_H = String.valueOf(b1.add(b2).doubleValue());
			amount.setFJe_H(FJe_H);
			amount.setFBillID(payorder.getFBillID());
			customerDao.updateAmount(amount);
			customerDao.updateUserendbalance(payorder.getUser_id(), FJe_H);
			//如果一个人是省代理申请了市代理，是可以申请的，更新它的区域代码
			customerDao.updateAreae(info1.getFUser_Id(),info1.getFarea_code());
			customerDao.changePayStateandPayway(info1.getForderid(),5,paywayNumber,String.valueOf(discount),String.valueOf(rebatemoney));
			//省是1，市是2，区是3。比较要申请的代理等级，如果要申请的代理等级小于自己本身的等级，进行更新。比如说他本身是省代理申请了市代理，Fproxy永远显示的是它的最低等级1，即省代理
			if((Integer.valueOf(info.getFLevel())<Integer.valueOf(user.getFproxy()))||Integer.valueOf(user.getFproxy())==0){
			 customerDao.updatelevel(info.getFLevel(),user.getUserid());
			}
			
		}
		customerDao.updateExamine(info);
		
		
	}
	
	public List<Orders>	findReateOrder (QueryCustomerReate vo) throws SQLClientInfoException
	{
		return customerDao.findReateOrder(vo);
	}

	public List<Rebate> findReateOrderInfo(String Forder_id) {
		
		return customerDao.findReateOrderInfo(Forder_id);
		
	}
}
