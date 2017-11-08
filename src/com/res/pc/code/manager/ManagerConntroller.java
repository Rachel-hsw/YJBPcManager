package com.res.pc.code.manager;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLClientInfoException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.res.pc.code.manager.bean.DeliveryBean;
import com.res.pc.code.manager.bean.DriverBean;
import com.res.pc.code.manager.bean.InitialNavigationsInfo;
import com.res.pc.code.manager.bean.MyCondition;
import com.res.pc.code.manager.bean.OperationLog;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.PayCondition;
import com.res.pc.code.manager.bean.PayMainBean;
import com.res.pc.code.manager.bean.RewardBean;
import com.res.pc.code.manager.bean.RoleInfo;
import com.res.pc.code.manager.bean.SupplierBean;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.manager.bean.WarehouseBean;
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
	 * 查询奖励 列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setReward")
	public ModelAndView setReward(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("reward");
		
		//调用service
		try
		{
			
			List<RewardBean> infoList = managerService.queryAllReward();
			
			
			request.setAttribute("infoList", infoList);
			
			
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
	@RequestMapping(value = "/Reward" , method = RequestMethod.POST)
	public ModelAndView Reward(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView("reward");
		RewardBean rewardbean=new RewardBean();
		/**
		 *  获取用户名和密码
		 */
		rewardbean.setId(request.getParameter("id"));
		rewardbean.setRate(request.getParameter("Rate"));
	
		try
		{
			//调用service
			    String result = managerService.updateReward(rewardbean);
			
				List<RewardBean> infoList = managerService.queryAllReward();			
				request.setAttribute("infoList", infoList);		
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
	
	
	
	/**
	 * 采购列表
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
			List<UserInfo> userlist = managerService.queryAllUserB();//采购员
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
			ifsucesss=managerService.updatepurchasingM(condition);//到业务层去处理逻辑
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		request.setAttribute("ifsucesss", ifsucesss);
		return purchase1(request, response);
	}
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
			List<UserInfo> userlist = managerService.queryAllUserB();
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
		try{
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
			
			}
			
		} catch (SQLClientInfoException e) {
	            logger.error("数据库异常",e);
	           
	        }  
		
		return returnpurchase(request, response);
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
		System.out.println("123");
		ModelAndView view = new ModelAndView("delivery");
		try
		{   
			String begintime=request.getParameter("begintime") ;
		    String endtime=request.getParameter("endtime") ;
		 /*   Map<String, String> map=new HashMap<String,String>();
		    map.put("begintime","begintime");
		    map.put("endtime","endtime");	*/
		    String query = "(FS_Date >= '"+begintime+"' and FS_Date <= '"+endtime+"')";
		    List<Orders> orderlist=null;
		    if(begintime!=null&&!"".equals(begintime)){
			orderlist = managerService.queryAllOrdersList(query);	
		    }else{
		    orderlist = managerService.queryAllOrdersList();	
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
		    int FBillID=Integer.valueOf(request.getParameter("FBillID")) ;
		   
			List<view_orderdetail> orderdetail = managerService.queryAllOrderDetail(FBillID);
			List<Orders>  orderlist = managerService.queryAllOrdersList(FBillID);	
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
			int FBillID=Integer.valueOf(deliveryBean.getFBillID()) ;
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
	 *采购应付
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
		    int FBillID=Integer.valueOf(request.getParameter("FBillID")) ;
		   
			List<view_orderdetail> orderdetail = managerService.queryAllOrderDetail(FBillID);
			
			request.setAttribute("orderdetail", orderdetail);
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 *采购应付
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
	 *采购应付
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
	 *会员付款
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customerpay")
	public ModelAndView customerpay(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView view = new ModelAndView("customerpay");
		try
		{ 
			String  FName=request.getParameter("FName") ;
			List<SupplierBean> supplierlist = managerService.queryAllSupplier();
			request.setAttribute("supplierlist", supplierlist);
			//订单号
			if(null!=request.getParameter("FId")&&!"".equals(request.getParameter("FId"))){
				 String  FId=request.getParameter("FId") ;
				 List<PurchasingBean> purchasinglist = managerService.queryPurchasing(FId);
				 request.setAttribute("purchasinglist", purchasinglist);
			}
		   
			request.setAttribute("FName", FName);
			
			
			

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	/**
	 *采购应付
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
			 for(int i=0;i<infoArray.length;i++){
				 managerService.updatePurchase_Status(Integer.valueOf(infoArray[i])); //更新采购状态表	
				 managerService.updateAmountPay(Integer.valueOf(infoArray[i])); //更新采购金额表
				
				// 供应商表里的Fending_balance字段为正表示公司欠供应商，为负供应商欠公司
				}
			 managerService.updateT_Qk_FK(payMainBean); //公司资金账户变动表（供应商付款表）	
			 managerService.updateSupplier1(payMainBean); //更新供应商表 

		} 
		catch (SQLClientInfoException e)
		{
			logger.error("数据库异常",e);
		}
		return view;
	}
	
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
	
	
}
