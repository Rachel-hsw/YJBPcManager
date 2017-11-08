<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>80</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
//去掉字符串空格
function jtrimstr(str) {
	var i = 0;
	var j;
	var len = str.length;
	trimstr = "";
	while (i < len) {
		if (str.charAt(i) != " ") {
			trimstr = trimstr + str.charAt(i);
		}
		i++;
	}
	return (trimstr);
}

//form提交前，验证：营销活动最少选择两个
function check(){
	var newPW = jtrimstr($("#password").val());
	
	//校验密码
	if(newPW == ""){
		alert("密码必填");
		$("#password").focus();
		return false;
	}
	
	//校验密码长度
	if(newPW.length < 6){
		alert("密码小于6位");
		$("#password").focus();
		return false;
	}
	
	
	return true;
}
</script>
</head>

<body>

	<form action=' <st:url value="/Manager/updatePW.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<input type="hidden" name="userID" value="${pwUser }" /> <span>新密码</span>
			</div>
			<ul class="forminfo">
				<li><label>密码：</label><input id="password" name="password"
					type="text" class="dfinput" value="" /><i></i></li>

				<li><label>&nbsp;</label> <input name=""
					onclick="return check()" type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</div>
	</form>

</body>

</html>
