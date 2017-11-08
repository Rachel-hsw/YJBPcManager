<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>systemAdd</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript">
$(function(){
	var txt_r=$(".recycle_to").val();
	$("#recycle_t").children().eq(txt_r).attr("selected","selected");
})
var laySum = 0; //定义变量 介绍layer的index
function getlay() {  //利用这个方法向子页面传递layer的index
    return laySum;
}
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

//form提交前，验证
function check(){
	var systemNameInfo = jtrimstr($("#nameInfo").val());

	
	
	//校验用户名
	if(systemNameInfo == ""){
		alert("客户名称必 填");
		$("#nameInfo").focus();
		return false;
	}
	return true;
}
$(document).ready(function(e) {
	$(".select3").uedSelect({
		width : 100
	});
	
	
});
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统设置</a></li>
			<li><a href="#">系统参数</a></li>
		</ul>
	</div>

	<form action=' <st:url value="/Manager/systemPost.do"></st:url>'
		method="post">
		<div class="formbody">
		<!-- 参数尚未修改 -->
			<div class="formtitle">
				<span>参数信息</span> 
				<input id="id"value="${id == 'null'?'':id}" name="id" type="hidden" /> 
				<input id="page" value="${page}" name="page" type="hidden" />
			</div>
			<!-- 参数尚未修改 -->
			<ul class="forminfo">
				<li>
					<label>系统参数名称：</label> 
					<input id="nameInfo" name="nameInfo" type="text" class="dfinput" value="${name == 'null'?'':name}" /> 
				</li>		
				<li>
				<label>桶参数状态</label>
				<div class="vocation">
				<input class="recycle_to" value="${state == 'null'?'0':state}" type="hidden"></input>
			    <select id="recycle_t" name="state" class="select3">
					<option value="0">否</option>
					<option value="1">是</option>
			    </select>
			    </div>
				</li>
				<li>
					<label>&nbsp;</label>
					<input name="" onsubmit="return check()" type="submit" class="btn" value="确认保存" />
				</li>
			</ul>
		</div>
	</form>

</body>

</html>
