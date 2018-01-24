package com.res.pc.code.manager;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLClientInfoException;
import java.text.Bidi;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.res.pc.code.customer.bean.Rebate;
import com.res.pc.code.manager.bean.DeliveryBean;
import com.res.pc.code.manager.bean.DriverBean;
import com.res.pc.code.manager.bean.EvaluateBean;
import com.res.pc.code.manager.bean.ExpenseBean;
import com.res.pc.code.manager.bean.InitialNavigationsInfo;
import com.res.pc.code.manager.bean.MyCondition;
import com.res.pc.code.manager.bean.OperationLog;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.PDBean;
import com.res.pc.code.manager.bean.PDItemBean;
import com.res.pc.code.manager.bean.PackageBean;
import com.res.pc.code.manager.bean.PackageItem;
import com.res.pc.code.manager.bean.PayMainBean;
import com.res.pc.code.manager.bean.RoleInfo;
import com.res.pc.code.manager.bean.SpdBean;
import com.res.pc.code.manager.bean.SpdCondition;
import com.res.pc.code.manager.bean.SupplierBean;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.manager.bean.WarehouseBean;
import com.res.pc.code.manager.bean.WithdrawBean;
import com.res.pc.code.manager.bean.PriceBean;
import com.res.pc.code.manager.bean.Product;
import com.res.pc.code.manager.bean.PurItemBean;
import com.res.pc.code.manager.bean.PurchaseBean;
import com.res.pc.code.manager.bean.PurchasingBean;
import com.res.pc.code.manager.bean.QueryProdVo;
import com.res.pc.code.manager.bean.QuerySupplierVo;
import com.res.pc.code.manager.bean.configInfo;
import com.res.pc.code.manager.bean.view_orderdetail;
import com.res.pc.code.manager.service.ManagerServiceImpl;
import com.res.pc.code.vo.BeginingVo;
import com.res.pc.code.vo.BeginningCondition;
import com.res.pc.code.vo.PageBean;
import com.res.pc.util.Constants;
import com.res.pc.util.OperationLogUtils;
import com.res.pc.util.PageInfo;
import com.res.pc.util.PicUtils;
import com.res.pc.util.StringTools;





/**
 * @author liuqiang
 * 2015年11月24日21:16:43
 */ 
@Controller
@RequestMapping(value = "/Manager")
public class ManagerConntroller
{
	/**
	 * 错误日志
	 */
	private static final Logger logger = Logger.getLogger(ManagerConntroller.class);
	
	/**
	 * 引入service
	 */
	@Resource(name = "managerService")
	private ManagerServiceImpl managerService;
	@Resource(name = "OperationLogUtils")
	private OperationLogUtils operationLogUtils;
	/**
	 * 显示登录页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月4日下午12:13:46
	 */
	@RequestMapping(value = "/showLogin" , method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("login1");
		return view;
	}
	
	/**
	 * 显示首页
	 * @param @param request
	 * @param @param response
	 * @param @return    
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月11日下午12:46:49
	 */
	@RequestMapping(value = "/showMain" , method =RequestMethod.GET)
	public ModelAndView showMain(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("main");
		return view;
	}
    @RequestMapping(value = "/showMain_main" , method =RequestMethod.GET)
    public ModelAndView showMain_main(HttpServletRequest request , HttpServletResponse response)
    {
        ModelAndView view = new ModelAndView("main_main");
        return view;
    }	
    
    @RequestMapping(value = "/system/{id}/{name}/{state}/{page}" , method =RequestMethod.GET)
    public ModelAndView showsystem(HttpServletRequest request , HttpServletResponse response,
            @PathVariable("id") String id,
            @PathVariable("name") String name,
            @PathVariable("state") String state,
            @PathVariable("page") int page) throws UnsupportedEncodingException
    {
        ModelAndView view = new ModelAndView("system");
        PageInfo info = new PageInfo();
        info.setPage(page);//页数
        info.setOrder("id asc");
        //调用service
        try
        {
            //设置搜索参数
            if(!StringTools.isNullOrEmpty(name))
            {
                name= URLDecoder.decode(name,"UTF-8");
                info.setquery("name like '%"+name+"%'");
            }
            //*//**
             //* 查询用户列表 和 用户总条数
             //*//*
            List<configInfo> infoList = managerService.queryconfigList(info);
            Integer count = managerService.queryconfigListCount();

            //*//**
             //* 计算分页
             //*//*
            request.setAttribute("count", count);//用户总数
            request.setAttribute("page", page);//当前页
            Integer endPage = (count / Constants.Page_Num) + 1;
            request.setAttribute("EndPage", endPage);//最终页
            request.setAttribute("prePage",page == 1 ?1:page-1);//上一页
            request.setAttribute("nextPage", page == endPage ?endPage:page+1);//下一页
            
           // *//**
            // * 存放值进入request
             //*//*
            request.setAttribute("configList", infoList);
        } 
        catch (SQLClientInfoException e)
        {
            logger.error("数据库异常",e);
        }
        return view;
    }
    
    @RequestMapping(value = "/systemAdd/{id}/{name}/{state}/{page}" , method =RequestMethod.GET)
    public ModelAndView systemAdd(HttpServletRequest request , HttpServletResponse response,
            @PathVariable("id") String id,
            @PathVariable("name") String name,
            @PathVariable("state") String state,
            @PathVariable("page") int page) throws UnsupportedEncodingException
    {
        ModelAndView view = new ModelAndView("systemAdd");
        name=URLDecoder.decode(name,"UTF-8");
        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("state", state);
        request.setAttribute("page", page);
        return view;
    }
    
    @RequestMapping(value = "/systemPost" , method =RequestMethod.POST)
    public ModelAndView systemPost(HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException
    { 
        String id=request.getParameter("id");
        String name=request.getParameter("nameInfo");
        String state=request.getParameter("state");
        configInfo config = new configInfo();
        config.setid(id);
        config.setname(name);
        config.setstate(state);
        try {
            managerService.updateconfig(config);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
        }      
        return showsystem(request,response,id,name,state,1);
    }   
	/**
	 * 显示index页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月7日上午10:48:57
	 */
	@RequestMapping(value = "/showIndex" , method = RequestMethod.GET)
	public ModelAndView showIndex(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
	
	/**
	 * 显示top页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月7日上午10:53:28
	 */
	@RequestMapping(value = "/showTop" , method = RequestMethod.GET)
	public ModelAndView showTop(HttpServletRequest request , HttpServletResponse response)
	{
		
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("managerUser");
		request.setAttribute("userId", userInfo.getUserid());
		ModelAndView view = new ModelAndView("top");
		return view;
	}
	
	
	/**
	 * 显示left页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author rlw
	 * @date 2015年10月7日下午6:03:58
	 */
	@RequestMapping(value = "/showLeft" , method = RequestMethod.GET)
	public ModelAndView showLeft(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("left");
		UserInfo u = (UserInfo) request.getSession().getAttribute("managerUser");
		try {
			List<InitialNavigationsInfo> inInfos = managerService.queryINByUserId(String.valueOf(u.getUserid()));
			//System.out.println(inInfos.size());
			List<InitialNavigationsInfo> parentMenu = new ArrayList<InitialNavigationsInfo>();
			List<InitialNavigationsInfo> childMenu = new ArrayList<InitialNavigationsInfo>();
			for(int i=0;i<inInfos.size();i++){
				if("0".equals(inInfos.get(i).getParentNavigationId())){
					parentMenu.add(inInfos.get(i));
				}else{
					childMenu.add(inInfos.get(i));
				}
				//System.out.println(inInfos.get(i).getParentNavigationId());
			}
			
			
			request.setAttribute("inInfos", inInfos);//
			request.setAttribute("parentMenu", parentMenu);
			request.setAttribute("childMenu", childMenu);
			
		} catch (SQLClientInfoException e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	
	/**
	 * 显示right页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月7日下午7:04:02
	 */
	@RequestMapping(value = "/showRight" , method = RequestMethod.GET)
	public ModelAndView showRight(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("right");
		return view;
	}
	
	
	/**
	 * 显示footer页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 刘强
	 * @date 2015年10月7日下午7:39:21
	 */
	@RequestMapping(value = "/showFooter" , method = RequestMethod.GET)
	public ModelAndView showFooter(HttpServletRequest request , HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("footer");
		return view;
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("main");
		UserInfo userInfo = new UserInfo();
		
		/**
		 *  获取用户名和密码
		 */
		userInfo.setUsername(request.getParameter("userName"));
		userInfo.setPassword(request.getParameter("password"));
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()));
		//if("2016-02-20 09:00:00".equals(df.format(new Date()))) return null;
		try
		{
			//调用service
			userInfo = managerService.login(userInfo);
			//存登录信息进入request
			request.setAttribute("user", userInfo);
			
			if(StringTools.isNullOrEmpty(userInfo))
			{
				//用户信息查询不到
				view = new ModelAndView("login1");
				request.setAttribute("error", "用户名或密码错误");
			}
			else
			{
				//登录成功，存信息进入session
				request.getSession().setAttribute("managerUser", userInfo);
				
				operationLogUtils.log(request,"登录成功");
			}
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	

	
	/**
	 * 查询菜单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/INList/{page}" , method = RequestMethod.GET)
	public ModelAndView queryINList(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("page") int page)
	{
		ModelAndView view = new ModelAndView("sysfunction");
		
		PageInfo info = new PageInfo();
		info.setPage(page);//页数
		info.setOrder("NavigationId asc");
		//调用service
		try
		{
			/**
			 * 查询用户列表 和 用户总条数
			 */
			List<InitialNavigationsInfo> infoList = managerService.queryINList(info);
			for(int i=0;i<infoList.size();i++){
				if(infoList.get(i).getParentNavigationId()!=null&&!"".equals(infoList.get(i).getParentNavigationId())){
					InitialNavigationsInfo reginfo = new InitialNavigationsInfo();
					reginfo.setNavigationId(infoList.get(i).getParentNavigationId());
					InitialNavigationsInfo pinfo = managerService.queryINInfo(reginfo);
					if(pinfo !=null) infoList.get(i).setParentNavigationName(pinfo.getNavigationText());
				}
			}
			Integer count = managerService.queryINCount();

			/**
			 * 计算分页
			 */
			request.setAttribute("count", count);//用户总数
			request.setAttribute("page", page);//当前页
			Integer endPage = (count / Constants.Page_Num) + 1;
			request.setAttribute("userEndPage", endPage);//最终页
			request.setAttribute("prePage",page == 1 ?1:page-1);//上一页
			request.setAttribute("nextPage", page == endPage ?endPage:page+1);//下一页
			
			/**
			 * 存放值进入request
			 */
			request.setAttribute("infoList", infoList);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	
	/**
	 * 查询菜单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectFun/{relIds}/{page}" , method = RequestMethod.GET)
	public ModelAndView selectFun(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("relIds") String relIds,@PathVariable("page") int page)
	{
		ModelAndView view = new ModelAndView("selectFun");
		
		
		//调用service
		try
		{
			/**
			 * 查询用户列表 和 用户总条数
			 */
			List<InitialNavigationsInfo> infoList = managerService.queryAllIN();
			
			for(int i=0;i<infoList.size();i++){
				if(infoList.get(i).getParentNavigationId()!=null&&!"".equals(infoList.get(i).getParentNavigationId())){
					InitialNavigationsInfo reginfo = new InitialNavigationsInfo();
					reginfo.setNavigationId(infoList.get(i).getParentNavigationId());
					InitialNavigationsInfo pinfo = managerService.queryINInfo(reginfo);
					if(pinfo !=null) infoList.get(i).setParentNavigationName(pinfo.getNavigationText());
				}
			}
			
		
			
			
			/**
			 * 存放值进入request
			 */
			request.setAttribute("infoList", infoList);
			request.setAttribute("relIds", relIds);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	
	
	/**
	 * 查询菜单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectFun2/{relIds}/{page}" , method = RequestMethod.GET)
	public ModelAndView selectFun2(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("relIds") String relIds,@PathVariable("page") int page)
	{
		ModelAndView view = new ModelAndView("selectFun2");
		
		PageInfo info = new PageInfo();
		info.setPage(page);//页数
		info.setOrder("NavigationId asc");
		//调用service
		try
		{
			/**
			 * 查询用户列表 和 用户总条数
			 */
			List<InitialNavigationsInfo> infoList = managerService.queryINList(info);
			for(int i=0;i<infoList.size();i++){
				if(infoList.get(i).getParentNavigationId()!=null&&!"".equals(infoList.get(i).getParentNavigationId())){
					InitialNavigationsInfo reginfo = new InitialNavigationsInfo();
					reginfo.setNavigationId(infoList.get(i).getParentNavigationId());
					InitialNavigationsInfo pinfo = managerService.queryINInfo(reginfo);
					if(pinfo !=null) infoList.get(i).setParentNavigationName(pinfo.getNavigationText());
				}
			}
			Integer count = managerService.queryINCount();

			/**
			 * 计算分页
			 */
			request.setAttribute("count", count);//用户总数
			request.setAttribute("page", page);//当前页
			Integer endPage = (count / Constants.Page_Num) + 1;
			request.setAttribute("userEndPage", endPage);//最终页
			request.setAttribute("prePage",page == 1 ?1:page-1);//上一页
			request.setAttribute("nextPage", page == endPage ?endPage:page+1);//下一页
			
			/**
			 * 存放值进入request
			 */
			request.setAttribute("infoList", infoList);
			request.setAttribute("relIds", relIds);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	
	
	/**
	 * 删除菜单信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delIN/{navigationId}/{page}" , method = RequestMethod.GET)
	public ModelAndView deleteInfo(HttpServletRequest request , HttpServletResponse response,
			@PathVariable("navigationId") String NavigationId,@PathVariable("page") Integer page)
	{
		try
		{
			InitialNavigationsInfo info = new InitialNavigationsInfo();
			info.setNavigationId(NavigationId);
			managerService.deleteINInfo(info);
			operationLogUtils.log(request, "删除菜单信息");
		}
		catch(Exception e)
		{
			
			logger.error("数据库异常",e);
			operationLogUtils.log(request, ""+e);
		}
		
	
	
		
		return queryINList(request, response, page);
	}
	
	/**
	 * 查询菜单详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryINInfo/{navigationId}/{page}" ,method = RequestMethod.GET)
	public ModelAndView queryINInfo(HttpServletRequest request , HttpServletResponse response,
			@PathVariable("navigationId") String NavigationId,@PathVariable("page") Integer page)
	{
		ModelAndView view = new ModelAndView("upfunction");
		try{
			InitialNavigationsInfo reginfo = new InitialNavigationsInfo();
			reginfo.setNavigationId(NavigationId);
			InitialNavigationsInfo info = managerService.queryINInfo(reginfo);
			if(info.getParentNavigationId()!=null&&!"".equals(info.getParentNavigationId())){
				reginfo.setNavigationId(info.getParentNavigationId());
				InitialNavigationsInfo pinfo = managerService.queryINInfo(reginfo);
				if(pinfo !=null) info.setParentNavigationName(pinfo.getNavigationText());
			}
			
			request.setAttribute("page", page);
			request.setAttribute("info", info);
			
			
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		
		return view;
	}
	
	
	/**
	 * 修改菜单信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateINInfo",method = RequestMethod.POST)
	public ModelAndView updateINInfo(HttpServletRequest request,HttpServletResponse response){
		Integer page = Integer.parseInt(request.getParameter("page"));
		try{
			InitialNavigationsInfo reqInfo = new InitialNavigationsInfo();	
			
			reqInfo.setNavigationId(request.getParameter("NavigationId"));
			reqInfo.setNavigationText(request.getParameter("NavigationText"));
			reqInfo.setParentNavigationId(request.getParameter("ParentNavigationId"));
			reqInfo.setNavigationUrl(request.getParameter("NavigationUrl"));
			reqInfo.setImageUrl(request.getParameter("ImageUrl"));
			reqInfo.setIsMoveby(Integer.parseInt(request.getParameter("IsMoveby")));
			
			managerService.updateINInfo(reqInfo);
			operationLogUtils.log(request, "修改菜单信息");
		}catch(Exception e){
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "修改菜单信息："+e);
		}
		
		return queryINList(request, response, page);
	}
	
	/**
	 * 添加用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addINInfo" , method = RequestMethod.POST)
	public ModelAndView addINInfo(HttpServletRequest request , HttpServletResponse response){
	
		Integer page = Integer.parseInt(request.getParameter("page"));
		try{
			InitialNavigationsInfo info = new InitialNavigationsInfo();
			info.setNavigationText(request.getParameter("NavigationText"));
			info.setParentNavigationId(request.getParameter("ParentNavigationId"));
			info.setNavigationUrl(request.getParameter("NavigationUrl"));
			info.setImageUrl(request.getParameter("ImageUrl"));
			info.setIsMoveby(Integer.parseInt(request.getParameter("IsMoveby")));
			//调用service
			String result = managerService.addINInfo(info);
			
			if("error".equals(result)){
				request.setAttribute("error", "菜单已存在");
				return addIN(request, response,page);
			}
			operationLogUtils.log(request, "添加菜单信息操作");
		}catch (Exception e){
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "添加菜单信息操作："+e);
		}
	
		return queryINList(request, response, page);
	}
	
	
	
	/**
	 * 添加菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addIN/{page}" ,method = RequestMethod.GET)
	public ModelAndView addIN(HttpServletRequest request , HttpServletResponse response,@PathVariable("page") Integer page){
		ModelAndView view = new ModelAndView("functioninfo");
		try{
			request.setAttribute("page", page);			
		}catch(Exception e){
			logger.error("数据库异常",e);
		}		
		return view;
	}
	
	
	/**
	 * 查询角色列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/RoleList/{page}" , method = RequestMethod.GET)
	public ModelAndView queryRoleList(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("page") int page)
	{
		ModelAndView view = new ModelAndView("manager/sysrole");
		
		PageInfo info = new PageInfo();
		info.setPage(page);//页数
		info.setOrder("CreateTime asc");
		//调用service
		try
		{
			/**
			 * 查询用户列表 和 用户总条数
			 */
			List<RoleInfo> infoList = managerService.queryRoleList(info);
			Integer count = managerService.queryRoleCount();
			
			for(int i=0;i<infoList.size();i++){
				if(infoList.get(i).getusers()!=null&&!"".equals(infoList.get(i).getusers())){
					String ids = infoList.get(i).getusers();
					if(",".equals(ids.substring(0, 1)))ids = ids.substring(1, ids.length());
					if(",".equals(ids.substring(ids.length()-1, ids.length())))ids = ids.substring(0, ids.length()-1);
					
					List<UserInfo> us = managerService.queryUsersByIds(ids);
					
					String userNames = "";
					for(UserInfo u : us){
						userNames += u.getName() + ",";
					}
					if(userNames.length()>0)userNames = userNames.substring(0, userNames.length()-1);
					infoList.get(i).setuserNames(userNames);
				}
				if(infoList.get(i).getPower()!=null&&!"".equals(infoList.get(i).getPower())){
					List<InitialNavigationsInfo> fins = managerService.queryINByIds(infoList.get(i).getPower());
					String PowerNames = "";
					for(InitialNavigationsInfo fin : fins){
						PowerNames += fin.getNavigationText() + ",";
					}
					if(PowerNames.length()>0)PowerNames = PowerNames.substring(0, PowerNames.length()-1);
					infoList.get(i).setPowerNames(PowerNames);
				}
			}

			/**
			 * 计算分页
			 */
			request.setAttribute("count", count);//用户总数
			request.setAttribute("page", page);//当前页
			Integer endPage = (count / Constants.Page_Num) + 1;
			request.setAttribute("userEndPage", endPage);//最终页
			request.setAttribute("prePage",page == 1 ?1:page-1);//上一页
			request.setAttribute("nextPage", page == endPage ?endPage:page+1);//下一页
			
			/**
			 * 存放值进入request
			 */
			request.setAttribute("infoList", infoList);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	
	
	
	
	
	/**
	 * 删除角色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delRole/{id}/{page}" , method = RequestMethod.GET)
	public ModelAndView deleteRoleInfo(HttpServletRequest request , HttpServletResponse response,
			@PathVariable("id") String id,@PathVariable("page") Integer page)
	{
		try
		{
			RoleInfo info = new RoleInfo();
			info.setID(id);
			managerService.deleteRoleInfo(info);
			operationLogUtils.log(request, "删除角色信息");
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "删除角色信息："+e);
		}
		
		
		return queryRoleList(request, response, page);
	}
	
	/**
	 * 查询角色详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryRoleInfo/{id}/{page}" ,method = RequestMethod.GET)
	public ModelAndView queryRoleInfo(HttpServletRequest request , HttpServletResponse response,
			@PathVariable("id") String id,@PathVariable("page") Integer page)
	{
		ModelAndView view = new ModelAndView("manager/uprole");
		try{
			RoleInfo reginfo = new RoleInfo();
			reginfo.setID(id);
			
			RoleInfo info = managerService.queryRoleInfo(reginfo);
			
			
			
			if(info.getusers()!=null&&!"".equals(info.getusers())){
				String ids = info.getusers();
				if(",".equals(ids.substring(0, 1)))ids = ids.substring(1, ids.length());
				if(",".equals(ids.substring(ids.length()-1, ids.length())))ids = ids.substring(0, ids.length()-1);
				List<UserInfo> us = managerService.queryUsersByIds(ids);
				
				String userNames = "";
				for(UserInfo u : us){
					userNames += u.getName() + ",";
				}
				if(userNames.length()>0)userNames = userNames.substring(0, userNames.length()-1);
				info.setuserNames(userNames);
			}
			
			if(info.getPower()!=null&&!"".equals(info.getPower())){
				
				List<InitialNavigationsInfo> fins = managerService.queryINByIds(info.getPower());
				String PowerNames = "";
				for(InitialNavigationsInfo fin : fins){
					PowerNames += fin.getNavigationText() + ",";
				}
				if(PowerNames.length()>0)PowerNames = PowerNames.substring(0, PowerNames.length()-1);
				info.setPowerNames(PowerNames);
			}
			
			
			request.setAttribute("page", page);
			request.setAttribute("info", info);
			operationLogUtils.log(request, "查询角色详情");
			
		}
		catch(Exception e){
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "查询角色详情："+e);
		}
		
		return view;
	}
	
	
	/**
	 * 修改角色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateRoleInfo",method = RequestMethod.POST)
	public ModelAndView updateRoleInfo(HttpServletRequest request,HttpServletResponse response){
		Integer page = Integer.parseInt(request.getParameter("page"));
		try{
			RoleInfo reqInfo = new RoleInfo();
			reqInfo.setID(request.getParameter("ID"));
			reqInfo.setRoleName(request.getParameter("RoleName"));
			reqInfo.setusers(request.getParameter("users"));
			reqInfo.setRemark(request.getParameter("Remark"));
			reqInfo.setPower(request.getParameter("Power"));
		
			
			managerService.updateRoleInfo(reqInfo);
			operationLogUtils.log(request, "修改角色信息");
		}catch(Exception e){
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "修改角色信息："+e);
		}
		
		return queryRoleList(request, response, page);
	}
	
	/**
	 * 添加角色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRoleInfo" , method = RequestMethod.POST)
	public ModelAndView addRoleInfo(HttpServletRequest request , HttpServletResponse response){
	
		Integer page = Integer.parseInt(request.getParameter("page"));
		try{
			RoleInfo info = new RoleInfo();
			info.setRoleName(request.getParameter("RoleName"));
			info.setusers(request.getParameter("users"));
			info.setRemark(request.getParameter("Remark"));
			info.setPower(request.getParameter("Power"));
			info.setIsMoveby(Integer.parseInt(request.getParameter("IsMoveby")));
			//调用service
			String result = managerService.addRoleInfo(info);
			
			if("error".equals(result)){
				request.setAttribute("error", "角色名存在");
				return addRole(request, response,page);
			}
			
			operationLogUtils.log(request, "添加角色信息");
		}catch (Exception e){
			logger.error("数据库异常",e);
			operationLogUtils.log(request, "添加角色信息："+e);
		}
		
		return queryRoleList(request, response, page);
	}
	
	
	
	/**
	 * 添加角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRole/{page}" ,method = RequestMethod.GET)
	public ModelAndView addRole(HttpServletRequest request , HttpServletResponse response,@PathVariable("page") Integer page){
		ModelAndView view = new ModelAndView("manager/roleinfo");
		try{
			request.setAttribute("page", page);			
		}catch(Exception e){
			logger.error("数据库异常",e);
		}		
		return view;
	}
	
	/**
	 * 显示修改密码页面
	 * @param request
	 * @param response
	 * @param userKey
	 * @return
	 */
	@RequestMapping(value = "/showUpdatePW/{userId}" , method = RequestMethod.GET)
	public ModelAndView showUpdatePW(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("userId") String userId){
		ModelAndView view = new ModelAndView("updatepw");
		request.setAttribute("pwUser", userId);
		return view;
	}
	
	@RequestMapping(value = "/updatePW" , method = RequestMethod.POST)
	public String updatePW(HttpServletRequest request,HttpServletResponse response){
		UserInfo info = new UserInfo();
		info.setUserid(request.getParameter("userID"));
		info.setPassword(request.getParameter("password"));
		try {
			managerService.updatePW(info);
		} catch (SQLClientInfoException e) {
			logger.error("数据库异常",e);
		}
		request.getSession().invalidate();  
	    return "login1";
		 
	}
	
	/**
	 * 查询用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectEnt/{relIds}/{page}" , method = RequestMethod.GET)
	public ModelAndView selectEnt(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("relIds") String relIds,@PathVariable("page") int page)
	{
		
		ModelAndView view = new ModelAndView("selectEnt");
		
		
		//调用service
		try
		{
			/**
			 * 查询用户列表 和 用户总条数
			 */
			List<UserInfo> infoList = managerService.queryAllUser();
			
			/**
			 * 存放值进入request
			 */
			request.setAttribute("infoList", infoList);
			request.setAttribute("relIds", relIds);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/zk" , method = RequestMethod.POST)
	public ModelAndView zk(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("zk");
		PriceBean zkbean=new PriceBean();
		request.getParameter("daili");	
		/**
		 *  获取用户名和密码
		 */
		zkbean.setP_Price_id(request.getParameter("daili"));
		zkbean.setP_Price(request.getParameter("P_Price"));
		zkbean.setP_Num(request.getParameter("p_Num"));
		zkbean.setP_Money(request.getParameter("p_Money"));
		try
		{
			//调用service
			    String result = managerService.updateZk(zkbean);
			
		
			
				List<PriceBean> infoList = managerService.queryAllZk();
				
				
				request.setAttribute("infoList", infoList);
			/*	operationLogUtils.log(request,"登录成功");*/
			
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
		
	/**
	 * 查询折扣 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setZk")
	public ModelAndView setZk(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("zk");
		
		//调用service
		try
		{
			
			List<PriceBean> infoList = managerService.queryAllZk();
			
			
			request.setAttribute("infoList", infoList);
			/*for(int i=0;i<infoList.size();i++){
				
				String s=infoList.get(i).getZk();
				String h=infoList.get(i).getDllevel();
				System.out.println(h+":"+s);
			}
			*/
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	//Manager/setReward.do
	
	/**
	 * 返利设置 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setReward")
	public ModelAndView setReward(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("setting");
		
		//调用service
		try
		{
			
			List<SpdBean> spdBeanList = managerService.queryRebateByType();
			for (SpdBean spdBean : spdBeanList) {
				spdBean.setRate(String.valueOf((int)(Double.parseDouble(spdBean.getRate())*100)));
			}
			request.setAttribute("spdBeanList", spdBeanList);		
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 修改返利
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/Reward")
	public ModelAndView Reward(SpdCondition condition,HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("setting");
		boolean ifsucesss=false;
	
		try
		{   List<SpdBean> spdBeanList=condition.getItemsList();   
			int i=1;
			//调用service
			    for (SpdBean spdBean : spdBeanList) {
			    	
			    	spdBean.setId(String.valueOf(i));
			    	spdBean.setRate(String.valueOf((Double.parseDouble(spdBean.getRate())/100)));
			    	managerService.updateRebateByTypeAndLv(spdBean);
			    	i++;
			    	
				}
			
			    
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		ifsucesss=true;
		request.setAttribute("ifsucesss", ifsucesss);
		return setReward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchase")
	public ModelAndView purchase(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("purchase/purchase");
		
		//调用service
		/*try
		{
			
			List<PriceBean> infoList = managerService.queryAllZk();
			
			
			request.setAttribute("infoList", infoList);
			for(int i=0;i<infoList.size();i++){
				
				String s=infoList.get(i).getZk();
				String h=infoList.get(i).getDllevel();
				System.out.println(h+":"+s);
			}
			
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}*/
		return view;
	}
	/**
	 * 查询折扣 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/showOrderEdit")
	public ModelAndView showOrderEdit(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("123");
		ModelAndView view = new ModelAndView("purchase/orderEdit");
		return view;
	}
		
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addPurchase" , method = RequestMethod.POST)
	public ModelAndView addPurchase(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("purchase/purchase");
		PurchaseBean purchaseBean=new PurchaseBean();
		
		purchaseBean.setJE(request.getParameter("JE"));
		purchaseBean.setJSR(request.getParameter("JSR"));
		purchaseBean.setOrder_id(request.getParameter("Order_id"));
		purchaseBean.setS_J_ID(request.getParameter("S_J_ID"));
		purchaseBean.setSL(request.getParameter("JE"));
		purchaseBean.setUser_id(request.getParameter("User_id"));
		purchaseBean.setW_ID(request.getParameter("W_ID"));
		purchaseBean.setZDY(request.getParameter("ZDY"));
		purchaseBean.setZL(request.getParameter("ZL"));
		
		try
		{
			//调用service
			    String result = managerService.addPurchase(purchaseBean);
			
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 查询折扣 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryPurchase")
	public ModelAndView queryPurchase(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("purchase/querypurchase");

		//调用service
		try
		{
			
		
			List<PurchaseBean> infoList = managerService.queryPurchase();
			
			
			request.setAttribute("infoList", infoList);
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	/*--------------------------------------------------------------采购---------------------------------------------*/
	
	/**
	 * 采购界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchase1")
	public ModelAndView purchase1(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("newpurchase");
		//customerquery
		
		try
		{
			
			List<WarehouseBean> arealist = managerService.queryAllWarehouse();	
			List<DriverBean> driverlist = managerService.queryAllDriver();//驾驶员
			List<UserInfo> userlist = managerService.queryAllUserB("2");//采购员
			List<Product> productlist = managerService.queryAllProduct();
			List<SupplierBean> supplierlist = managerService.queryAllSupplier();
			request.setAttribute("arealist", arealist);
			request.setAttribute("driverlist", driverlist);
			request.setAttribute("userlist", userlist);
			request.setAttribute("productlist", productlist);
			request.setAttribute("supplierlist", supplierlist);
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 
	 * 提交采购信息
	 * @param condition
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchasingUpdates")
	public ModelAndView purchasingUpdates(MyCondition condition,HttpServletRequest request,HttpServletResponse response){
		boolean ifsucesss = false;
		String result="";
		try{
			/*if(condition.getItemsList()!=null){
			List<PurItemBean> itemsList=condition.getItemsList();
			for (PurItemBean purItemBean : itemsList) {
				//商品名称,规格            ,单位        ,单价            ,数量        ,重量,金额          ,备注
				//FP_Id ,FP_Price,FP_Num,FP_ZL,FP_Money,Remark
				if(purItemBean.getFP_Id()!=null&&!"".equals(purItemBean.getFP_Id())){
					managerService.updatepurchasing(purItemBean);//更新采购明细表
					purItemBean.setFW_Id(condition.getFW_Id());
					managerService.updateStock(purItemBean); //更新库存表
					
					}
				//更新采购明细表//更新库存表//更新采购主表//更新采购金额表//更新供应商表 //更新供应商资金变动表//更新字号表
			}
			
			managerService.updatepurchasingM(condition);//更新采购主表
			managerService.updateAmount(condition);//更新采购金额表
			managerService.updateSupplier(condition);//更新供应商表
			managerService.updateCfg(condition); //更新供应商资金变动表
			managerService.updateBillnumber(condition);//更新字号表
			}
			*/
			List<MyCondition> myCondition=managerService.findFSourceId(condition);
			if ( myCondition.size()!=0) {
				//如果能查到该原始订单号
				ifsucesss=false;
				result="不允许输入重复的原始单号";
				
			}else{
				result="成功";
				ifsucesss=managerService.updatepurchasingM(condition);//到业务层去处理逻辑	
			}
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		request.setAttribute("result", result);
		request.setAttribute("ifsucesss", ifsucesss);
		return purchase1(request, response);
	}
	/*-------------------------------------------------------------------------采购退货---------------------------------------------*/
	/**
	 * 采购退货列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/returnpurchase")
	public ModelAndView returnpurchase(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("returnpurchase");
		
		try
		{
			
			List<WarehouseBean> arealist = managerService.queryAllWarehouse();	
			List<DriverBean> driverlist = managerService.queryAllDriver();
			List<UserInfo> userlist = managerService.queryAllUserB("2");
			List<Product> productlist = managerService.queryAllProduct();
			List<SupplierBean> supplierlist = managerService.queryAllSupplier();
			request.setAttribute("arealist", arealist);
			request.setAttribute("driverlist", driverlist);
			request.setAttribute("userlist", userlist);
			request.setAttribute("productlist", productlist);
			request.setAttribute("supplierlist", supplierlist);
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 
	 * 提交采购退货信息
	 * @param condition
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/returnUpdates")
	public ModelAndView returnUpdates(MyCondition condition,HttpServletRequest request,HttpServletResponse response){
		boolean ifsucesss = false;
		try{
			List<MyCondition> myCondition=managerService.findFSourceId(condition);
			if ( myCondition.size()!=0) {
				//如果能查到该原始订单号
				ifsucesss=false;
				String result="不允许输入重复的原始单号";
				request.setAttribute("result", result);
			}else{
			if(condition.getItemsList()!=null){
			List<PurItemBean> itemsList=condition.getItemsList();
			for (PurItemBean purItemBean : itemsList) {
				//商品名称,规格            ,单位        ,单价            ,数量        ,重量,金额          ,备注
				//FP_Id ,FP_Price,FP_Num,FP_ZL,FP_Money,Remark
				if(purItemBean.getFP_Id()!=null&&!"".equals(purItemBean.getFP_Id())){
					purItemBean.setFP_Num("-"+purItemBean.getFP_Num());
					purItemBean.setFP_Money("-"+purItemBean.getFP_Money());
					purItemBean.setFW_Id(condition.getFW_Id());
					managerService.updatereturn(purItemBean); //更新采购明细表
					managerService.updateStock(purItemBean); //更新库存表
					}
				
				
			}
			condition.setFJe("-"+condition.getFJe()); 
			managerService.updatereturnM(condition); //更新采购主表
			managerService.updateAmount(condition); //更新采购金额表
			managerService.updateSupplier(condition); //更新供应商表
			managerService.updateCfg(condition); //更新供应商资金变动表
			managerService.updateBillnumber(condition); //更新字号表
			
			}	ifsucesss=true;
			}	
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		request.setAttribute("ifsucesss", ifsucesss);
		return returnpurchase(request, response);
	}
	/*-------------------------------------------------------------------------采购应付---------------------------------------------*/

	/**
	 *指定供应商的采购应付列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/shouldpay")
	public ModelAndView shouldpay(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("shouldpay");
		try
		{ 
			String  FName=request.getParameter("FName") ;
			String  FId=request.getParameter("FId") ;
			List<SupplierBean> supplierlist = managerService.queryAllSupplier();
			request.setAttribute("supplierlist", supplierlist);
			//订单号
			if(null!=request.getParameter("FId")&&!"".equals(request.getParameter("FId"))){
				
				 List<PurchasingBean> purchasinglist = managerService.queryPurchasing(FId);
				 request.setAttribute("purchasinglist", purchasinglist);
			}
		   
			request.setAttribute("FName", FName);
			request.setAttribute("FId", FId);
			
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}

	/**
	 *采购应付提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/paysubmit")
	public ModelAndView paysubmit(PayMainBean payMainBean,HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("shouldpay");
		try
		{ 
			 String[] infoArray=request.getParameterValues("delId");
			  StringBuffer sb = new StringBuffer();
			 for(int i=0;i<infoArray.length;i++){
				 managerService.updatePurchase_Status(Integer.valueOf(infoArray[i])); //更新采购主表订单状态	
				 managerService.updateAmountPay(Integer.valueOf(infoArray[i])); //更新采购金额表
				
				// 供应商表里的Fending_balance字段为正表示公司欠供应商，为负供应商欠公司
				 sb.append(infoArray[i]);
		            if ((i + 1) != infoArray.length) {
		                sb.append(",");
		            }
				}
			 payMainBean.setCGID(sb.toString());
			 managerService.updateT_Qk_FK(payMainBean); //公司资金账户变动表（供应商付款表）	
			 managerService.updateSupplier1(payMainBean); //更新供应商表 

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/*------------------------------------------------------------------------销售订单发货---------------------------------------------*/
	/**
	 *根据采购单ID查询查看指定ID待发货明细信息 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchaseDetail")
	public ModelAndView purchaseDetail(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("orderDetail");
		try
		{
			//订单号
			int FBillID=Integer.valueOf(request.getParameter("FBillID")) ;
			
			List<view_orderdetail> orderdetail = managerService.queryPurchaseDetail(FBillID);
			
			request.setAttribute("orderdetail", orderdetail);
			
			
			
		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 订单发货列表界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delivery")
	public ModelAndView delivery(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("delivery");
		try
		{   
			String begintime=request.getParameter("begintime") ;
		    String endtime=request.getParameter("endtime") ;
		 /*   Map<String, String> map=new HashMap<String,String>();
		    map.put("begintime","begintime");
		    map.put("endtime","endtime");	*/
		   
		    List<Orders> orderlist=null;
		    if(begintime!=null&&!"".equals(begintime)){
		    String query = "(T_Qk_Orders.Fstatus='2' and FS_Date >= '"+begintime+"' and FS_Date <= '"+endtime+"')";
			orderlist = managerService.queryAllOrdersList(query);	
		    }else{
		    String query = "(T_Qk_Orders.Fstatus='2')";
		    orderlist = managerService.queryAllOrdersList(query);	
		    }
			request.setAttribute("orderlist", orderlist);
			request.setAttribute("begintime", begintime);
			request.setAttribute("endtime", endtime);
		
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}

	
	/**
	 * 发货主界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deliveryprint")
	public ModelAndView deliveryprint(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("deliveryprint");

		try
		{
			//订单号
		    String FBillID=request.getParameter("FBillID") ;
		   
			List<view_orderdetail> orderdetail = managerService.queryAllOrderDetail(FBillID);
			List<Orders>  orderlist = managerService.queryAllOrdersListss(FBillID);	
			List<WarehouseBean> arealist = managerService.queryAllWarehouse();	
			request.setAttribute("arealist", arealist);			
			request.setAttribute("orderdetail", orderdetail);
			request.setAttribute("orderlist", orderlist);
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
	/**
	 * 提交发货单
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */

	@RequestMapping(value = "/deliverysumbit")
	public ModelAndView deliverysumbit(DeliveryBean deliveryBean,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		try{
			String  FBillID=deliveryBean.getFBillID() ;
			managerService.updateDeliver_Goods(deliveryBean); //添加发货表信息
			managerService.updateOrders_Status(FBillID);//更新订单主表
			
			String FId=deliveryBean.getFId();
			if(deliveryBean.getItemsList()!=null){
				List<PurItemBean> itemsList=deliveryBean.getItemsList();
				for (PurItemBean purItemBean : itemsList) {
					//商品名称,规格            ,单位        ,单价            ,数量        ,重量,金额          ,备注
					//FP_Id ,FP_Price,FP_Num,FP_ZL,FP_Money,Remark	
					purItemBean.setFW_Id(FId);
					//仓库Id FW_Id	商品Id FP_Id
						
          if (!"".equals(purItemBean.getFP_Id())&&purItemBean.getFP_Id()!=null) {
        	  managerService.updateStock2(purItemBean); //更新库存表
			
		}
					
			}}
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		
		return delivery(request, response);
	}
	/**
	 *根据销售订单ID查询发货明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("orderDetail");
		try
		{
			//订单号
		   String FBillID=request.getParameter("FBillID") ;
		   
			List<view_orderdetail> orderdetail = managerService.queryAllOrderDetail(FBillID);
			
			request.setAttribute("orderdetail", orderdetail);
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/*-------------------------------------------------------------------------会员升级付款---------------------------------------------*/
	/**
	 *会员付款
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customerpay")
	public ModelAndView customerpay(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("finance/custmercost");
		return view;
	}
	/**
	 *会员付款提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/custmercostSubmit")
	public ModelAndView custmercostSubmit(Orders condition,HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("finance/custmercost");
		String result = "";
		boolean ifsucesss=false;
	
		try {
			
		  
			if("".equals(managerService.queryUserById(condition.getUser_id()))||null==(managerService.queryUserById(condition.getUser_id()))){
				result="此用户没有注册过";
			}else{
			result=managerService.updateCustmercost(condition);
			ifsucesss=true;
			}
			request.setAttribute("result", result);	
			request.setAttribute("ifsucesss", ifsucesss);	
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//custmercost
		}
		return view;
		
	}
	/*------------------------------------------------------------------------供应商增删改查---------------------------------------------*/	
	@RequestMapping(value = "/SupplierList" )
	public String SupplierList(QuerySupplierVo vo,HttpServletRequest request , HttpServletResponse response)
	{
		PageBean<SupplierBean> pageBean = null;
		int count = 0;
		vo.setBegin(vo.getCurrentPage());
		vo.setEnd("20");

		try {
			pageBean =managerService.querySupplierList(vo);
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("pageBean", pageBean);
		return "purchasingunit/purchasingunitList";
	}
	
	
	/**
	 * 查询进货单位详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/querySupplierInfo" ,method = RequestMethod.GET)
	public ModelAndView querySupplierInfo(QuerySupplierVo vo,HttpServletRequest request , HttpServletResponse response,
			String Id,String method)
	{
		ModelAndView view = new ModelAndView("purchasingunit/uppurchasingunit");
		SupplierBean info=null;
		try{
			if(null!=Id&&!"".equals(Id)){
			SupplierBean reqInfo = new SupplierBean();
			reqInfo.setFId(Id);
			 info = managerService.querySupplierInfo(reqInfo);
			
			}
			request.setAttribute("supplierInfo", info);
			request.setAttribute("vo", vo);
			request.setAttribute("method", method);
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		
		return view;
	}
	/**
	 * 删除进货单位信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delSupplier" )
	public String delSupplier(QuerySupplierVo vo,HttpServletRequest request , HttpServletResponse response,
	String  delid,String status)
	{
		try
		{
			SupplierBean supplierBean= new SupplierBean();
			supplierBean.setFId(delid);
			if("0".equals(status)){
				supplierBean.setFStatus("1");
			}else if("1".equals(status)){
				supplierBean.setFStatus("0");
			}
			
			managerService.deleteSupplierInfo(supplierBean);
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		
		return SupplierList(vo,request, response);
	}
	
	
	/**
	 * 修改进货单位信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/OperationSupplierInfo")
	public String OperationSupplierInfo(SupplierBean  supplierInfo,HttpServletRequest request,HttpServletResponse response){
	       QuerySupplierVo vo=new QuerySupplierVo();
	       vo.setCurrentPage(request.getParameter("currentPage"));
	       vo.setFname(request.getParameter("name"));
	       vo.setFaddress(request.getParameter("address"));
	       vo.setFtel(request.getParameter("tel"));
	       vo.setFstatus(request.getParameter("status"));
	       vo.setFcontacts(request.getParameter("contacts"));
	       String method=request.getParameter("method");
		try{
			if("update".equals(method)){
			managerService.updateSupplierInfo(supplierInfo);
			}else{
				managerService.addSupplierInfo(supplierInfo);
			}
			
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		return SupplierList(vo,request, response);
	}
	/*-------------------------------------------------------------------------商品增删改查---------------------------------------------*/	
	@RequestMapping(value = "/prodList" )
	public String prodList(QueryProdVo vo,HttpServletRequest request , HttpServletResponse response)
	{
		//1，调用service查询所有商品
		List<Product> list=null;
		try {
			list = managerService.findAllProd(vo);
			//2,将所有商品信息存入request域后带到页面展示
			request.setAttribute("list", list);
			request.setAttribute("vo", vo);
			
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "product/proList";
		
	}
	/**
	 * 查询产品详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryProdInfo" ,method = RequestMethod.GET)
	public ModelAndView queryProdInfo(QueryProdVo vo,HttpServletRequest request , HttpServletResponse response,
			String Id,String method)
	{
		ModelAndView view = new ModelAndView("product/addProd");
		Product prod=null;
		try{
			if(null!=Id&&!"".equals(Id)){;
			 prod = managerService.queryProductInfo(Id);
			
			}
			request.setAttribute("item", prod);
			request.setAttribute("vo", vo);
			request.setAttribute("method", method);
		}
		catch(Exception e){
			logger.error("数据库异常",e);
		}
		
		return view;
	}
	
	/**
	 * 删除产品信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delProd" )
	public String delProd(QueryProdVo vo,Product prod ,HttpServletRequest request , HttpServletResponse response)
	{
		try
		{
			if("0".equals(prod.getFStatus())){
				prod.setFStatus("1");
			}else if("1".equals(prod.getFStatus())){
				prod.setFStatus("0");
			}
			
			managerService.deleteProductInfo(prod);
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		
		return prodList(vo,request, response);
	}
	
	/**
	 * 修改产品信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/OperationProdInfo")
	public String OperationSupplierInfo(QueryProdVo  vo,Product prod ,  @RequestParam("pictureFile")  MultipartFile pictureFile,HttpServletRequest request,HttpServletResponse response){
	     	       String method=request.getParameter("method");
	     	  
		try{
			  //保存图片到 
 			String name = UUID.randomUUID().toString().replaceAll("-", "");
 			//jpg
 			String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
 			if(!"".equals(ext)&&null!=ext){
 			String upload=request.getSession().getServletContext().getRealPath("upload\\" + name + "." + ext);
 			pictureFile.transferTo(new File(upload));
 			
 			prod.setImgurl("upload/" + name + "." + ext);
 			//--生成缩略图
 			PicUtils picu = new PicUtils(upload);
 			picu.resizeByHeight(140);
 			}
 			if (prod.getDivName().equals("护肤型")) {
				prod.setDivID("H");
			}else 	if (prod.getDivName().equals("技能型")) {
				prod.setDivID("J");
			}
			if("update".equals(method)){
			managerService.updateProductInfo(prod);
			}else{
				managerService.addProductInfo(prod);
			}
			
		}
		catch(Exception e)
		{
			logger.error("数据库异常",e);
		}
		
		return prodList(vo,request, response);
	}
	/*------------------------------------------------------------------------库存管理，商品盘库---------------------------------------------*/
	/**
	 * 商品盘库
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/InventoryWS")
	public ModelAndView InventoryWS(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("InventoryWS");
		try
		{   String  FName=request.getParameter("FName") ;//仓库名称
			String  FW_Id=request.getParameter("FW_Id") ;//仓库ID
			List<WarehouseBean> arealist = managerService.queryAllWarehouse();	//查询仓库列表
			request.setAttribute("arealist", arealist);
			// 获取指定仓库下的商品列表
			if(null!=request.getParameter("FW_Id")&&!"".equals(request.getParameter("FW_Id"))){
				
				
				 List<Product> Inventory = managerService.queryInventory(FW_Id);	
					
					request.setAttribute("Inventory", Inventory);
			}
			
			  Date currentTime = new Date();
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
			  String dateString = formatter.format(currentTime);
			  //得到PD20171109
			  String w="PD"+dateString.substring(0, 8);
			  //获取目前数据库中的最大派单号，从数据库获取回来的单号格式为PD20171109001
			  List<PDBean> PDBean = managerService.queryPDBean(w);	
			  if (!PDBean.get(0).getFPDDH().equals("0")) {
				  //截取后三位001
			  String dh=PDBean.get(0).getFPDDH().substring(10,13);
			  //转化为int类型，1。然后加1，得2
			  int num=Integer.valueOf(dh)+1;
			  //再转化为002，然后加上PD20171109---%03d，一种左边补0 的等宽格式,比如数字12,%03d出来就是: 012
			  String PDDH =w+String.format("%03d", num);
              
			  request.setAttribute("PDDH", PDDH);// 
			  }else{
				  request.setAttribute("PDDH", w+"001");	  
			  }
			request.setAttribute("FName", FName);//回显
			request.setAttribute("FW_Id", FW_Id);
			List<UserInfo> userlist = managerService.queryAllUserB("1,2");//会员
			request.setAttribute("userlist", userlist);
			
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 * 
	 * 提交盘点仓库信息
	 * @param condition
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/InventoryWSUpdates")
	public ModelAndView InventoryWSUpdates(PDBean condition,HttpServletRequest request,HttpServletResponse response){
		boolean ifsucesss = false;
		try{
			
			if(condition.getItemsList()!=null){
				List<PDItemBean> itemsList=condition.getItemsList();
				for (PDItemBean PDItemBean : itemsList) {
					
					if(PDItemBean.getFP_Id()!=null&&!"".equals(PDItemBean.getFP_Id())){
						//更新盘点明细表
						PDItemBean.setFPDDH(condition.getFPDDH());
						managerService.updatePDItem(PDItemBean); 
						
						}
					
				}
				//更新盘点主表
				managerService.updatePD(condition);
				}
			ifsucesss=true;//到业务层去处理逻辑
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		request.setAttribute("ifsucesss", ifsucesss);
		return InventoryWS(request, response);
	}	
	/*------------------------------------------------------------------------辅助功能，评价---------------------------------------------*/
	 /**
     * 根据条件查询评价列表并返回到evaluate页面
     */
    @RequestMapping(value = "/queryEvaluateListByCond/{reqStr}/{page}", method = RequestMethod.GET)
    public ModelAndView queryEvaluateListByCond(HttpServletRequest request, HttpServletResponse response, @PathVariable("reqStr") String reqStr, @PathVariable("page") int page) {
        ModelAndView view = new ModelAndView("evaluate");
        PageInfo info = new PageInfo();
        info.setPage(page);//页数
        info.setOrder("FID desc");
     
        if("null".equals(reqStr)) {
            reqStr = "";
        }
        info.setquery(reqStr);
        try {
            //	query list
            List<EvaluateBean> evaluateBeanList = managerService.queryEvaluateListByCond(info);
            //	query count
            int count = managerService.queryCountInEvaluateByCond(info);
            request.setAttribute("count", count);        //count总条数
            request.setAttribute("page", page);    //当前页
            Integer endPage = (count / Constants.Page_Num) + 1;
            request.setAttribute("userEndPage", endPage);        //最终页
            request.setAttribute("prePage", page == 1 ? 1 : page - 1);        //上一页
            request.setAttribute("nextPage", page == endPage ? endPage : page + 1);        //下一页
            request.setAttribute("evaluateList", evaluateBeanList);
            if ("".equals(reqStr)) {
                reqStr = "null";
            }
            request.setAttribute("reqStr", reqStr);
        } catch (Exception e) {
            logger.error("数据库异常", e);
        }

        return view;
    }
    /*-------------------------------------------------------------------------产品组装---------------------------------------------*/
    /**
	 *查询套餐列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/productpackaging")
	public ModelAndView productpackaging(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageList");
		
		
			List<PackageBean> packageList = managerService.queryPackageList();
			request.setAttribute("packageList", packageList);

		return view;
	}
	/**
	 *根据套餐id查询套餐明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/packageDetail")
	public ModelAndView packageDetail(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageDetail");
		
		String  P_Package_id=request.getParameter("P_Package_id") ;//仓库名称
		List<PackageItem> packageDetail = managerService.queryPackageDetail(P_Package_id);
		request.setAttribute("packageDetail", packageDetail);
		
		return view;
	}
	
	/**
	 *增加套餐界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/packageAdd")
	public ModelAndView packageAdd(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageAdd");
		try {
		
		List<Product> productList = managerService.queryAllProduct();
		
		request.setAttribute("productList", productList);
    
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 *增加套餐界面提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/packageAddSubmit")
	public ModelAndView packageAddSubmit(PackageBean condition,HttpServletRequest request,HttpServletResponse response)
	{
		boolean ifsucesss = false;
	
		try {
		   
			ifsucesss=managerService.updatePackage(condition);
				
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productpackaging(request, response);
	}
	/**
	 *修改套餐界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryProductbyId")
	public ModelAndView queryProductbyId(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageAdd2");
		try {
		String  P_Package_id=request.getParameter("P_Package_id") ;//套餐名称
		List<PackageItem> productList = managerService.queryProductbyId(P_Package_id);
		
		request.setAttribute("productList", productList);
		
		List<PackageBean> packageList = managerService.queryPackagebyId(P_Package_id);
		request.setAttribute("packageList", packageList);
		
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	/**
	 *修改套餐界面提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/packageChangeSubmit")
	public ModelAndView packageChangeSubmit(PackageBean condition,HttpServletRequest request,HttpServletResponse response)
	{
		boolean ifsucesss = false;
	
		try {
		   
			ifsucesss=managerService.updatePackage2(condition);
				
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productpackaging(request, response);
	}
	
	  /*-------------------------------------------------------------------------财务管理---------------------------------------------*/

	/**
	 *销售退货
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/salesreturn")
	public ModelAndView salesreturn(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageAdd2");
		
		return view;
	}
	/**
	 *资产负债表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/balancesheet")
	public ModelAndView balancesheet(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageAdd2");
		
		return view;
	}
	/**
	 *综合查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/allquery")
	public ModelAndView allquery(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("packaging/packageAdd2");
		
		return view;
	}
	
	
	/**
	 *一般费用
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/generalexpense")
	public ModelAndView generalexpense(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("finance/generalexpense");
		
		return view;
	}
	/**
	 *其他收入
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/otherearning")
	public ModelAndView otherearning(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("finance/otherearning");
		
		return view;
	}
	/**
	 *一般费用提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/generalSubmit")
	public ModelAndView generalSubmit(ExpenseBean condition,HttpServletRequest request,HttpServletResponse response)
	{
		boolean ifsucesss = false;
	
		try {
			condition.setFDefault("0");
			ifsucesss=managerService.updateExpenseOrEarn(condition);
				
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ifsucesss", ifsucesss);
		return generalexpense(request, response);
	}
	/**
	 *其他收入提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/otherearningSubmit")
	public ModelAndView otherearningSubmit(ExpenseBean condition,HttpServletRequest request,HttpServletResponse response)
	{
		boolean ifsucesss = false;
		
		try {
			condition.setFDefault("1");
			ifsucesss=managerService.updateExpenseOrEarn(condition);
			
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ifsucesss", ifsucesss);
		return otherearning(request, response);
	}
	  /*------------------------------------------------------------------------综合查询---------------------------------------------*/
	/**
	 *采购列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchaseList")
	public ModelAndView purchaseList(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("search/purchaseList");
		try{
			String begintime=request.getParameter("begintime") ;
		    String endtime=request.getParameter("endtime") ;
		    List<PurchasingBean> mianList=null;
		    if(begintime!=null&&!"".equals(begintime)){
		    String query = "(1=1 and FS_Date >= '"+begintime+"' and FS_Date <= '"+endtime+"')";
		    mianList = managerService.queryPurchaseList(query);
		    }else{
		    String query = "(1=1)";
		    mianList = managerService.queryPurchaseList(query);	
		    }
		
	for (PurchasingBean purchasingBean : mianList) {
		if (purchasingBean.getFstatus()==1) {
			purchasingBean.setFstatusText("采购成功，待付款");
			
		}else 	if (purchasingBean.getFstatus()==2) {
			purchasingBean.setFstatusText("采购付款成功");
			
		}else 	if (purchasingBean.getFstatus()==4) {
			purchasingBean.setFstatusText("采购退货，待付款");
			
		}else 	if (purchasingBean.getFstatus()==5) {
			purchasingBean.setFstatusText("退货付款成功");
			
		}
		
		
	}		
		
		request.setAttribute("mianList", mianList);
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return view;
	}
	/**
	 *采购明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchaseListDetail")
	public ModelAndView purchaseListDetail(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("search/purchaseDetail");
		try{
		String FBillID=request.getParameter("FBillID");
		int Fstatus=Integer.valueOf(request.getParameter("Fstatus"));
		List<PurchasingBean> mianList = managerService.queryPurchaseListByID(FBillID);//查询指定ID的采购主表信息
	    request.setAttribute("mianList", mianList);
		List<PurItemBean> itemsList = managerService.purchaseItemDetail(FBillID);//查询指定ID的采购明细表信息
	    request.setAttribute("itemsList", itemsList);
	    request.setAttribute("Fstatus", Fstatus);
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return view;
	}
	/**
	 *采购付款单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/purchasePay")
	public ModelAndView purchaseListReturn(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("search/payList");
		try{
		//查询采购付款单列表
			String begintime=request.getParameter("begintime") ;
		    String endtime=request.getParameter("endtime") ;
		    List<PayMainBean> mianList=null;
		    if(begintime!=null&&!"".equals(begintime)){
		    String query = "( 1=1 and FS_Date >= '"+begintime+"' and FS_Date <= '"+endtime+"')";
		    mianList = managerService.queryPayList(query);
		    }else{
		    String query = "( 1=1)";
		    mianList = managerService.queryPayList(query);
		    }
		
		request.setAttribute("mianList", mianList);
		} catch (SQLClientInfoException e) {
	        logger.error("数据库异常",e);
	       
	    }  
		return view;
	}
	/**
	 *采购付款单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/payListDetail")
	public ModelAndView payListDetail(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("search/payListDetail");
		try{
		String FId=request.getParameter("FId");
		String CGID=request.getParameter("CGID");
		List<PurchasingBean> mianList = managerService.queryPurchaseListByID(CGID);//查询指定一系列ID的采购主表信息
	    request.setAttribute("mianList", mianList);
		 List<PayMainBean> payList = managerService.queryPayListByID(FId);//查询指定ID的付款表信息
		 request.setAttribute("payList", payList);
		

	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return view;
	}
	
	/**
	 *销售单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deliveryList")
	public ModelAndView deliveryList(HttpServletRequest request,HttpServletResponse response)
	{

		ModelAndView view = new ModelAndView("search/delivery");
		try
		{   

			String begintime=request.getParameter("begintime") ;
		    String endtime=request.getParameter("endtime") ;
		    String username=request.getParameter("username") ;
		    if (username==null) {
		    	username="";
			}
		    List<Orders> orderlist=null;
		    if(begintime!=null&&!"".equals(begintime)){
		    	String query="";
		    	 query = "(1=1 and FS_Date >= '"+begintime+"' and FS_Date <"+"(select dateadd(dd,1,'"+endtime+"')))";
		    	  if (!"".equals(username)){
		    		  if (query.length() > 2)
							query += " and "; 
		    		  query += " FSname like '%" + username + "%'";
		    	  }
		    	// 根据日期查询待发货订单列表
			orderlist = managerService.queryAllOrdersList(query);	
			
		    }else{
		    	String query="";
		    	  if (!"".equals(username)){
		    		   query = "(1=1 and FSname  like '%"+username+"%')";
		    	  }
		    	   query="(1=1)";
		  
		 // 查询所有待发货列表
		    orderlist = managerService.queryAllOrdersList(query);	
		    }
		    for (Orders orders : orderlist) {
		    	//2018-01-11 19:29:54.107 截取为2018-01-11 19:29:54
		    	if (!"".equals(orders.getFS_Date())) {
		    		String FS_Date=orders.getFS_Date();
		    		FS_Date=FS_Date.substring(0,19);
		    		  System.out.println("...");
		       		orders.setFS_Date(FS_Date);  			        
				}
		    	if (null==orders.getFstatus()) {
		    		orders.setFstatus("6");
				}
		    	    if (orders.getFstatus().equals("0")) {
					orders.setFstatus("订单已取消");
					
				    }else  if (orders.getFstatus().equals("1")) {
						orders.setFstatus("下单成功，待付款");
						
					}else 	if (orders.getFstatus().equals("2")) {
						orders.setFstatus("付款成功，待发货");
						
					}else 	if (orders.getFstatus().equals("3")) {
						orders.setFstatus("发货成功，待签收");
						
					}else 	if (orders.getFstatus().equals("4")) {
						orders.setFstatus("签收成功，待评价");
						
					}else 	if (orders.getFstatus().equals("5")) {
						orders.setFstatus("已完成");
						
					}else 	if (orders.getFstatus().equals("6")) {
						orders.setFstatus("此订单没有状态");
						
					}
			}
			request.setAttribute("orderlist", orderlist);
			
			request.setAttribute("begintime", begintime);
			request.setAttribute("endtime", endtime);
			request.setAttribute("username", username);
		
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 *销售退货列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deliveryReturnList")
	public ModelAndView deliveryReturnList(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("search/deliveryReturnList");
		
		return view;
	}

	@ResponseBody 
	@RequestMapping(value = "/queryUpdate")
	public List<String> queryUpdate(HttpServletRequest request,HttpServletResponse response)
	{
		List<String> list=new ArrayList<String>(); 
	try{ 
		String User_id=request.getParameter("User_id");
	    String Fje=request.getParameter("Fje");
		UserInfo userInfo =managerService.findLevelByUserid(User_id);//0,0 U_Llevel,Fproxy 
		Orders orders=new Orders();
		orders.setUser_id(User_id);
		orders.setFje(Fje);
		String hh=managerService.queryshengjiRebate(orders,"1");
		hh=hh.replace("null", "没有");
		 SpdBean spdBean=new SpdBean();
		    //第一次购买本人折扣
		    spdBean.setFtype("1");
		    spdBean.setLv("1");
		    SpdBean zk =managerService.findRateByLv(spdBean);
		    //复销本人折扣
		    spdBean.setFtype("2");
		    spdBean.setLv("1");
		    SpdBean overzk =managerService.findRateByLv(spdBean);
		    String result="";
		    String panDuan="";
		    if (Fje.equals("9800")&&userInfo.getFproxy().equals("0")) {
		    	if (userInfo.getU_Llevel().equals("0")) {
		    		//普通会员
		    		    panDuan="2";//弹出确认框
	    			    result="您目前为普通会员，确定升级为高级会员吗？<br>"+hh;
						list.add(panDuan);
						list.add(result);
				}else if (userInfo.getU_Llevel().equals("1")) {//高级会员
					    panDuan="1";//弹出提醒框
	    			    result="您目前已经为高级会员，你可以选择付款19800，升级为钻石会员。";
						list.add(panDuan);
						list.add(result);
			
				}else if(userInfo.getU_Llevel().equals("2")){//钻石会员
					    panDuan="1";//弹出提醒框
	    			    result="您目前已经钻石会员。";
						list.add(panDuan);
						list.add(result);
				}else{
					result="有事情发生了";
					panDuan="1";
					list.add(panDuan);
					list.add(result);
				}
			}else if (Fje.equals("19800")&&userInfo.getFproxy().equals("0")) {
				if (userInfo.getU_Llevel().equals("0")) {
		    		//普通会员
					   panDuan="2";//弹出确认框
	    			    result="您目前为普通会员，确定升级为钻石会员吗？<br>"+hh;
						list.add(panDuan);
						list.add(result);
				}else if (userInfo.getU_Llevel().equals("1")) {//高级会员
					   panDuan="2";//弹出确认框
	    			    result="您目前为高级会员，确定升级为钻石会员吗?<br>"+hh;
						list.add(panDuan);
						list.add(result);
				
				}else if (userInfo.getU_Llevel().equals("2")) {//加盟店
					panDuan="1";//弹出提醒框
    			    result="您目前已经钻石会员。";
					list.add(panDuan);
					list.add(result);
				}else{
					result="有事情发生了";
					panDuan="1";
					list.add(panDuan);
					list.add(result);
				}
			}else if (!userInfo.getFproxy().equals("0")) {
				//是代理
				panDuan="1";//弹出提醒框
			    result="您已经是代理，不需要升级";
				list.add(panDuan);
				list.add(result);
				
			}
		   
		 
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return list;
		
		
	}
	
	/**
	 * 查询指定用户的电子币余量是否足以支付
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/queryIfEnough")
	public List<String> queryIfEnough(HttpServletRequest request,HttpServletResponse response)
	{
		List<String> list=new ArrayList<String>(); 
	try{ 
	    String result="";
		String panDuan="";
		String User_id=request.getParameter("User_id");
	    String Fje=request.getParameter("Fje");
		 Rebate rebate=managerService.queryIfEnough(User_id);
		 if (rebate==null) {
				panDuan="1";//弹出提醒框
			    result="该用户不存在";
				list.add(panDuan);
				list.add(result);
		}else{
		 BigDecimal Fe_voucheS=new BigDecimal(rebate.getFe_vouche());
		 BigDecimal Fe_voucheF=new BigDecimal(Fje);
		 if (Fe_voucheS.compareTo(Fe_voucheF)<0) {
				panDuan="1";//弹出提醒框
			    result="该用户的电子币余量不足以支付此笔订单";
				list.add(panDuan);
				list.add(result);
		}else{
			UserInfo userInfo=managerService.queryUserById(User_id);
			panDuan="2";//弹出确认框
		    result="您确认扣除用户"+userInfo.getName()+Fje+"电子币吗？";
			list.add(panDuan);
			list.add(result);
		}
		}
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return list;
		
		
	}
	/**
	 *人工补下单并发货
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deliveryByman")
	public ModelAndView deliveryByman(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("sale/deliveryByman");
		try{
		List<WarehouseBean> arealist = managerService.queryAllWarehouse();	
		request.setAttribute("arealist", arealist);	
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return view;
	}
	
	/**
	 *补发货提交
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/deliveryBymanSubmit")
	public ModelAndView deliveryBymanSubmit(Orders condition,@RequestParam("pictureFile")  MultipartFile pictureFile,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException
	{
		ModelAndView view = new ModelAndView("sale/deliveryByman");
		String result = "";
		boolean ifsucesss=false;
		try {	  
			if("".equals(managerService.queryUserById(condition.getUser_id()))||null==(managerService.queryUserById(condition.getUser_id()))){
				result="此用户没有注册过";
			}else{
			  //保存图片到 
 			String name = UUID.randomUUID().toString().replaceAll("-", "");
 			//jpg
 			String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
 			if(!"".equals(ext)&&null!=ext){
 			String upload=request.getSession().getServletContext().getRealPath("upload\\" + name + "." + ext);
 			pictureFile.transferTo(new File(upload));
 			
 			condition.getDeliveryBean().setFImg("upload/" + name + "." + ext);
 			//--生成缩略图
 			PicUtils picu = new PicUtils(upload);
 			picu.resizeByHeight(140);
 			result=managerService.deliveryBymanSubmit(condition);
			ifsucesss=true;
 			}				
			}
			List<WarehouseBean> arealist = managerService.queryAllWarehouse();	
			request.setAttribute("arealist", arealist);	
			request.setAttribute("result", result);	
			request.setAttribute("ifsucesss", ifsucesss);	
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//custmercost
		}
		return view;
		
	}
	/**
	 *提现列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryWithdrawList")
	public ModelAndView queryWithdrawList(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = null ;
		try{
			 String FId=request.getParameter("FId");
			 String FStatus=request.getParameter("FStatus"); 
			 String query="";
			if(!"".equals(FId)&&null!=FId){
			query="FId="+FId;
			 view = new ModelAndView("finance/withdrawDetail");
			}else{
				query="1=1";
				view = new ModelAndView("finance/withdrawlist");
			}
		 List<WithdrawBean>  WithdrawList =managerService.queryWithdrawList(query);
		 request.setAttribute("WithdrawList", WithdrawList);	
		request.setAttribute("FStatus", FStatus);	
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return view;
	}
	
	/**
	 *更新提现单状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateWithdrawStatus")
	public ModelAndView updateWithdrawStatus(WithdrawBean condition,HttpServletRequest request,HttpServletResponse response)
	{
		boolean ifsucesss = false;
		String result="";
		ModelAndView view =  new ModelAndView("finance/withdrawlist");
		 String query="1=1";
		try {
			Rebate rebate=managerService.queryBalance(condition.getFUser_Id());
			 BigDecimal b1 = new BigDecimal(rebate.getFbalance());
			 BigDecimal b2 = new BigDecimal(condition.getFJe());
			if (b1.compareTo(b2)<0) {
				ifsucesss=false;
				result="你账户中的余额为"+b1+",不足以支付此次提现";
				
			}else{
				ifsucesss=managerService.updateWithdrawStatus(condition);
				result="成功";
			}		
			 List<WithdrawBean>  WithdrawList =managerService.queryWithdrawList(query);
			 request.setAttribute("WithdrawList", WithdrawList);	
		} catch (SQLClientInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("ifsucesss", ifsucesss);
		request.setAttribute("result", result);
		return view;
	}
	/**
	 *驳回提现审核
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ajaxUpdateRefuseReason")
	@ResponseBody
	public String ajaxUpdateRefuseReason(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view =  new ModelAndView("finance/withdrawlist");
		 String query="1=1";
		try{
			boolean ifsucesss = false;
			String result="";
		    String RefuseReson=request.getParameter("RefuseReson");
		    String FId=request.getParameter("FId");
		    WithdrawBean condition=new WithdrawBean();
		    condition.setFId(FId);
		    condition.setFStatus("3");
		    condition.setFRefuseReason(RefuseReson);
			ifsucesss=managerService.ajaxUpdateRefuseReason(condition);
			result="驳回成功";
			/*response.getWriter().print(arg0);*/
			 /*List<WithdrawBean>  WithdrawList =managerService.queryWithdrawList(query);
			 request.setAttribute("WithdrawList", WithdrawList);	
			 request.setAttribute("ifsucesss", ifsucesss);
				request.setAttribute("result", result);*/
	} catch (SQLClientInfoException e) {
        logger.error("数据库异常",e);
       
    }  
		return "[\"0\"]";
	}
	
}