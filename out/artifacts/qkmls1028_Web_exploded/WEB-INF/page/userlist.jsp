<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>86</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myJs/sording.js"></script><!--列表排序-->
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

function dawo(){
	var index = layer.open({
	    type: 2,
	    content: '',
	    area: ['300px', '195px'],
	    maxmin: true
	});
	layer.full(index);
}
function isDel(key,page){
	//询问框
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	},function(){
		window.location.href = "${pageContext.request.contextPath}/Manager/delUser/"+key+"/"+page+".do";
	});
}
</script>

<style>tr th{cursor:pointer;}</style>
</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户管理</a></li>
			<li><a href="#">用户列表</a></li>
		</ul>
	</div>

	<br />
	<form
		action=' <st:url value="/Manager/UserList/${prePage}.do"></st:url>'
		method="post">
		<div class="tools">
			<ul class="seachform1">
				<li><label>名称：</label><input id="uName" name="uName" value="${uName }"
					class="scinput1" type="text" /></li>
				<li><input id="searchs" name="" class="scbtn" value="查询"
					type="submit" /></li>
			</ul>
		</div>
	</form>
	<table class="tablelist">
		<thead>
			<tr>
				<th width="3%">序号<i class="sort"><img
						src="${pageContext.request.contextPath}/images/px.gif" /></i></th>
				<th dataType="num">工号</th>
				<th dataType="roomType">姓名</th>
				<th dataType="roomType">用户类型</th>
				<th>电话</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td><a
						href='<st:url value="/Manager/queryUserInfo/${user.userId}/0.do"></st:url>'
						class="tablelink">${user.userName}</a></td>
					<td>${user.nickName}</td>
					<td>${user.userType == "1"?"司机":(user.userType == "0"?"管理员":(user.userType == "2"?"统计员":"业务员"))}</td>
					<td class="telsplit">${user.phone}</td>
					<td><a
						href='<st:url value="/Manager/queryUserImelList/${user.userId}/${user.userName}.do"></st:url>'
						class="tablelink">查看绑定</a> <a href='#'
						onclick="isDel('${user.userKey}','${userPage}')" class="tablelink">${user.status == "0"?"删除":""}</a>
						<a
						href='<st:url value="/Manager/queryUserInfo/${user.userId}/1.do"></st:url>'
						class="tablelink">修改</a> <a
						href='<st:url value="/Manager/showUpdatePW/${user.userId}.do"></st:url>'
						class="tablelink">重置密码</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<div class="pagin">
		<div class="message">
			共<i class="blue">${userCount}</i>条记录，当前显示第&nbsp;<i class="blue">${userPage}&nbsp;</i>页
		</div>
		<ul class="paginList">
			<li class="paginItem"><a
				href='<st:url value="/Manager/UserList/${prePage}.do?uName=${uName }"></st:url>'><span
					class="pagepre"></span></a></li>
			<li class="paginItem"><a href="javascript:;">${userPage}</a></li>
			<li class="paginItem"><a
				href='<st:url value="/Manager/UserList/${nextPage}.do?uName=${uName }"></st:url>'><span
					class="pagenxt"></span></a></li>
		</ul>
	</div>



	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	$(".telsplit").each(function(){
		if($(this).html() != ""){
			var text=$(this).html().trim();
			var txt=text.substring(0,3)+"-"+text.substring(3,7)+"-"+text.substring(7,11);
			$(this).html(txt);
		};
	})
	</script>

</body>

</html>