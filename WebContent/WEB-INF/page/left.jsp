﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.res.pc.code.manager.bean.UserInfo,
com.res.pc.code.manager.bean.InitialNavigationsInfo,java.util.List" pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>64无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
	$('.menuson li').click(function(){
		$(this).addClass("nav-bc");
		$(this).children().addClass("nav-color");
		$(this).siblings().removeClass("nav-bc");
		$(this).siblings().children().removeClass("nav-color");
	});
})	
</script>
<style type="text/css">
		.nav-bc{
			background-color: #EB5409;
			
		}
		.nav-color{
			color:#fff !important;
		}
</style>

</head>

<body style="background: #fff3e1;">
	<div class="lefttop">
		<span></span>菜单
	</div>
	<dl class="leftmenu">
<% 
    List<InitialNavigationsInfo> parentMenu = (List<InitialNavigationsInfo>)request.getAttribute("parentMenu");
    
    List<InitialNavigationsInfo> childMenu = (List<InitialNavigationsInfo>)request.getAttribute("childMenu");
    
    for(InitialNavigationsInfo p:parentMenu){ %>
		<dd>
			<div class="title">
				<span><img src="<%=p.getImageUrl() %>" /></span><%=p.getNavigationText() %>
			</div>
			<ul class="menuson">
				<%for(InitialNavigationsInfo c:childMenu){
    	if(c.getParentNavigationId().equals(p.getNavigationId())){%>
				<li><cite></cite><a
					href=' <st:url value="<%=c.getNavigationUrl()%>"></st:url>'
					target="rightFrame"><%=c.getNavigationText() %></a><i></i></li>
				<%} %>

				<%} %>
			</ul>
		</dd>



		<%} %>
		<%UserInfo u = (UserInfo) request.getSession().getAttribute("managerUser"); 
    if("admin".equals(u.getUsername())){%> 
		<dd>
			<div class="title">
				<span><img src="../images/leftico02.png" /></span>系统管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a
					href=' <st:url value="/Manager/INList/1.do"></st:url>'
					target="rightFrame">菜单列表</a><i></i></li>
				<li><cite></cite><a
					href=' <st:url value="/Manager/RoleList/1.do"></st:url>'
					target="rightFrame">角色列表</a><i></i></li>
					<li><cite></cite><a
					href=' <st:url value="/Manager/setReward.do"></st:url>'
					target="rightFrame">返利设置</a><i></i></li>
			</ul>
		</dd>
		<%}%>

	</dl>
</body>
</html>
