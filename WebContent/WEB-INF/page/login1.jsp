<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>沂健宝后台管理系统</title>

<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script src="../js/cloud.js" type="text/javascript"></script>
<script src="../js/login1.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
    if (top.location != self.location){    
    	top.location=self.location;    
    	} 
});  
</script>

</head>

<body
	style="background-color: #df7611; background-image: url(../images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">



	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>沂健宝后台管理系统</span>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>


		<form action=' <st:url value="/Manager/login.do"></st:url>'
			method="post">
			<div class="loginbox">
				<ul>
					<li><input id="" name="userName" type="text" class="loginuser"
						value="${error}" onclick="JavaScript:this.value=''" /></li>
					<li><input id="" name="password" type="password"
						class="loginpwd" value="" onclick="JavaScript:this.value=''" /></li>
					<li><input name="" type="submit" class="loginbtn" value="登录" />
						<label><a href="#">忘记密码？</a></label></li>
				</ul>
			</div>
		</form>

	</div>

	<div class="loginbm">版权所有 2017</div>


</body>

</html>
