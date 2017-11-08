<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>85</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
</head>

<body>
	<div class="formbody">
		<div class="formtitle">
			<span>用户信息</span>
		</div>
		<ul class="forminfo">
			<li><label>用户名：</label><input name="username" type="text"
				class="dfinput" value="${userInfo.username}" /><i></i></li>
			<li><label>昵称：</label><input name="name" type="text"
				class="dfinput" value="${userInfo.name}" /><i></i></li>
		
			<li><label>&nbsp;</label> <input
				onclick="javascript:history.go(-1);" type="button" class="btn"
				value="返回" />
		</ul>
</body>

</html>
