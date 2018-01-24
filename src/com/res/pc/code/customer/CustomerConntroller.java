/**
 * 
 */
package com.res.pc.code.customer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.res.pc.code.customer.bean.Examine;
import com.res.pc.code.customer.bean.QueryCustomerReate;
import com.res.pc.code.customer.bean.QueryCustomerVo;
import com.res.pc.code.customer.bean.QueryExamineVo;
import com.res.pc.code.customer.bean.Rebate;
import com.res.pc.code.customer.service.CustomerServiceImpl;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.SupplierBean;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.vo.BeginingVo;
import com.res.pc.code.vo.PageBean;
import com.res.pc.util.OperationLogUtils;
import com.res.pc.util.Page;


/**
 * @author Administrator 标签conntroller
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerConntroller {
    /**
     * 错误日志
     */
    private static final Logger logger = Logger.getLogger(CustomerConntroller.class);

    /**
     * 引入service
     */
    @Resource(name = "customerService")
    private CustomerServiceImpl customerService;

	@Resource(name = "OperationLogUtils")
	private OperationLogUtils operationLogUtils;
	
	
	@RequestMapping(value = "/customerList" )
	public String customerList(QueryCustomerVo vo,HttpServletRequest request , HttpServletResponse response)
	{
		PageBean<UserInfo> pageBean = null;
		int count = 0;
		vo.setBegin(vo.getCurrentPage());
		vo.setEnd("20");

		try {
			pageBean = customerService.queryCustomerList(vo);
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("pageBean", pageBean);
		return "customer/customerList";
	}
	
	
	/**
	 * 查询用户详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryUserInfo/{userId}" ,method = RequestMethod.GET)
	public ModelAndView queryUserInfo(QueryCustomerVo vo,HttpServletRequest request , HttpServletResponse response,
			@PathVariable("userId") String userId)
	{
		ModelAndView view = new ModelAndView("customer/upuser");
		try{
			UserInfo reqInfo = new UserInfo();
			reqInfo.setUserid(userId);
			UserInfo info = customerService.queryUserInfo(reqInfo);
			
			request.setAttribute("userInfo", info);
			request.setAttribute("vo", vo);
			
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		
		return view;
	}
	/**
	 * 删除用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delUser" )
	public String deleteUserInfo(QueryCustomerVo vo,HttpServletRequest request , HttpServletResponse response,
	String deluserid,String status)
	{
		try
		{
			UserInfo userInfo = new UserInfo();
			userInfo.setUserid(deluserid);
			if("0".equals(status)){
				userInfo.setFStatus(1);
			}else if("1".equals(status)){
				userInfo.setFStatus(0);
			}
			customerService.deleteUserInfo(userInfo);
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		
		
		return customerList(vo,request, response);
	}
	
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateUserInfo")
	public String updateUserInfo1(HttpServletRequest request,HttpServletResponse response){
		UserInfo reqInfo=new UserInfo();
		reqInfo.setUserid(request.getParameter("userid"));
		reqInfo.setName(request.getParameter("name"));
		reqInfo.setEmail(request.getParameter("email"));
		reqInfo.setTel(request.getParameter("tel"));
		reqInfo.setIccard(request.getParameter("iccard"));
		reqInfo.setUserlb(Integer.valueOf(request.getParameter("userlb")));
		QueryCustomerVo vo=new QueryCustomerVo();
		vo.setCurrentPage(request.getParameter("currentPage"));
		vo.setUsername(request.getParameter("vousername"));
		try{
			customerService.updateUserInfo(reqInfo);
			
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		return customerList(vo,request, response);
	}
	
	/**
	 * 显示修改密码页面
	 * @param request
	 * @param response
	 * @param userKey
	 * @return
	 */
	@RequestMapping(value = "/showUpdatePW/{userId}" , method = RequestMethod.GET)
	public ModelAndView showUpdatePW( QueryCustomerVo vo,HttpServletRequest request,HttpServletResponse response,
			@PathVariable("userId") String userId){
		ModelAndView view = new ModelAndView("customer/updatepw");
		request.setAttribute("pwUser", userId);
		request.setAttribute("vo", vo);
		return view;
	}
	
	

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/updatePW/{currentPage}" , method = RequestMethod.POST)
	public String updatePW(@PathVariable("currentPage") String currentPage,
			HttpServletRequest request,HttpServletResponse response){
		UserInfo info = new UserInfo();
		info.setUserid(request.getParameter("userID"));
		info.setPassword(request.getParameter("password"));
		QueryCustomerVo vo=new QueryCustomerVo();
		vo.setCurrentPage(currentPage);
		vo.setUsername(request.getParameter("username"));
		try {
			customerService.updatePW(info);
		} catch (SQLClientInfoException e) {
			logger.error("数据库异常",e);
		}
		return customerList(vo,request, response);
	}
	
	@RequestMapping(value = "/examineList" )
	public String examineList(QueryExamineVo vo,HttpServletRequest request , HttpServletResponse response)
	{
		PageBean<Examine> pageBean = null;
		vo.setBegin(vo.getCurrentPage());
		vo.setEnd("20");

		try {
			pageBean = customerService.queryExamineList(vo);
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("pageBean", pageBean);
		return "customer/ExamineList";
	}
	
	@RequestMapping(value = "/queryExamineInfo/{id}" ,method = RequestMethod.GET)
	public String queryExamineInfo(QueryExamineVo vo,HttpServletRequest request , HttpServletResponse response,
			@PathVariable("id") String FExamine_id)
	{
		
		try{
			
			Examine info = customerService.queryExamineInfo(FExamine_id);
			
			request.setAttribute("info", info);
			request.setAttribute("vo", vo);
			
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		
		return "customer/ExamineInfo";
	}
	
	
	@RequestMapping(value = "/updateExamineInfo")
	public String updateExamineInfo(QueryExamineVo vo,Examine examine,HttpServletRequest request,HttpServletResponse response){
		
		try{
			customerService.updateExamine(examine);
			
			
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		return examineList(vo,request, response);
	}
	
	@RequestMapping(value = "/RebateList" )
	public String RebateList(QueryCustomerReate vo,HttpServletRequest request , HttpServletResponse response)
	{
		List<Orders> findReateOrder=null;
		try {
			//查询返利列表   Fstatus= 2-待发货；   3-带评价；   2余额 3微信 4现金  FpayWay
		findReateOrder = customerService.findReateOrder(vo);//
		} catch (SQLClientInfoException e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("findReateOrder", findReateOrder);
		return "search/selectOrderRebate";
	}
	@RequestMapping(value = "/findReateOrderInfo/{id}")
	public String findReateOrderInfo(HttpServletRequest request , HttpServletResponse response,
			@PathVariable("id") String Forder_id)
	{
		 List<Rebate> findReateOrderInfo=null;
		try{
			
	  findReateOrderInfo = customerService.findReateOrderInfo(Forder_id);
		
			
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		request.setAttribute("findReateOrderInfo", findReateOrderInfo);
		return "search/RebateInfo";
	}
	
}
