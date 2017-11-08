<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>84</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
});

window.parent.document.getElementById("index").rows="*,0";


function isDel(iid,uid,uname){
	
	//询问框
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	},function(){
		window.location.href = "${pageContext.request.contextPath}/Manager/delUserImei/"+iid+"/"+uid+"/"+uname+".do";
	},function(){
	});
}

function bangding(uname,uid){
	window.location.href = "${pageContext.request.contextPath}/Manager/addUserImei/"+uname+"/"+uid+".do";
}
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户管理</a></li>
			<li><a href="#">用户IMEI绑定管理</a></li>
		</ul>
	</div>
	<br />
		
	<div class="tools">
		<ul class="toolbar">
			<li onclick="bangding('${uname}','${uId}')" class="click"><span><img
					src="${pageContext.request.contextPath}/images/t01.png"></span>绑定</li>
		</ul>
	</div>

	<table class="tablelist">
		<thead>
			<tr>
				<th>序号</th>
				<th>工号</th>
				<th>绑定IMEI编码</th>
				<th>手机号</th>
				<th>绑定时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userImeiList}" var="info" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${uname}</td>
					<td>${info.IMEI}</td>
					<td class="telsplit">${info.phone}</td>
					<td>${info.createTime.substring(0,19)}</td>
					<td><a href='#'
						onclick="isDel('${info.ID}','${info.userId}','${uname}')"
						class="tablelink">解绑</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	</div>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	$(".telsplit").each(function(){
		if($(this).html() != ""){
			var text=$(this).html();
			var txt=text.substring(0,3)+"-"+text.substring(3,7)+"-"+text.substring(7,11);
			$(this).html(txt);
		};
	})
	</script>

</body>

</html>