<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>82</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户管理</a></li>
			<li><a href="#">修改用户</a></li>
		</ul>
	</div>

	<form action=' <st:url value="/Manager/updateUserInfo.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>用户信息</span>
			</div>
			<ul class="forminfo">
				<li><label>昵称：</label><input name="nickName" type="text"
					class="dfinput" value="${userInfo.nickName}" /><i></i></li>
				<li><label>手机：</label><input name="Phone" type="text"
					class="dfinput" value="${userInfo.phone}" /><i></i></li>
				<li><label>车牌：</label><input name="CID" type="text"
					class="dfinput" value="${userInfo.CID}" /><i></i></li>
				<li><label>载重：</label><input name="shippington" type="text"
					class="dfinput" value="${userInfo.shippington}" /><i></i></li>
				<li><label>类别</label> <cite> <select id="oneType"
						name="UserType" style="width: 100px;">
							<option ${userInfo.userType == "0"?"selected":""} value="0">管理员</option>
							<option ${userInfo.userType == "1"?"selected":""} value="1">驾驶员</option>
							<option ${userInfo.userType == "2"?"selected":""} value="2">统计员</option>
					</select> <i>请下拉选择类别</i>
				</cite></li>
				<li ${userInfo.userType != "1"?"style='display:none;'":""} ><label>是否显示位置</label> <cite> <select id="oneType"
						name="IsShowPosition" style="width: 100px;">
							<option ${userInfo.isShowPosition == "1"?"selected":""} value="1">是</option>
							<option ${userInfo.isShowPosition == "0"?"selected":""} value="0">否</option>
							
					</select> <i>请下拉选择类别</i>
				</cite></li>
				<li><label>&nbsp;</label> <input name="userId" type="hidden"
					class="dfinput" value="${userInfo.userId}" /> <input name=""
					type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</div>

	</form>

</body>

</html>
