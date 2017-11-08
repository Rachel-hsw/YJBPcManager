/**  
 * @Title: SessionFilter.java
 * @Package com.wxsh.interfaces.code.manger.service
 * @Description: TODO
 * @author 刘强
 * @date 2015年10月11日下午12:56:40
 */
package com.res.pc.code.manager.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.util.StringTools;

/**
 * 过滤器
 * ClassName: SessionFilter 
 * @author 刘强
 * @date 2015年10月11日下午12:56:40
 */
public class SessionFilter extends OncePerRequestFilter
{

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		//不拦截的URL
		String [] notFilter = new String[]{"showLogin.do","default.html","login.do","showMain.do","showIndex.do","showTop.do","showLeft.do","showRight.do","showFooter.do"};
		
		//请求的url
		String url = request.getRequestURI();
		
		boolean doFilter = true;
		for (String s : notFilter)
		{
			if(url.indexOf(s) != -1)
			{
				doFilter = false;
			}
		}
		
		if(doFilter)
		{
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("managerUser");
			if(StringTools.isNullOrEmpty(userInfo))
			{
				response.sendRedirect(request.getContextPath()+"/default.html");
			}
			else
			{
				filterChain.doFilter(request, response);
			}
		}
		else
		{
			filterChain.doFilter(request, response);
		}
	}

}
