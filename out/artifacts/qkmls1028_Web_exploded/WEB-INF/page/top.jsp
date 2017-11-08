<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>79无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	


</script>


</head>

<body style="background: url(../images/topbg.gif) repeat-x;">

	<div class="topleft">
		<a href="javascript:void(0);" target="_parent"><img src="../images/logo.png"
			title="系统首页" /></a>
	</div>

	<ul class="nav">
	</ul>

	<div class="topright">
		<ul>
			<li><a href="${pageContext.request.contextPath }/Manager/showUpdatePW/${userId}.do"
				target="rightFrame">修改密码</a></li>
			<li><a href="${pageContext.request.contextPath }/default.html" onclick="closeWindow"
				target="_parent">退出</a></li>
		</ul>

		<div class="user">
			<span>${managerUser.name}</span>
		</div>

	</div>

</body>
</html>
