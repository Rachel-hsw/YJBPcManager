<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>77</title>
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
function dawo(){
	var index = layer.open({
	    type: 2,
	    content: '',
	    area: ['300px', '195px'],
	    maxmin: true
	});
	layer.full(index);
}
</script>

</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">菜单列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<ul class="toolbar">
				<li class="click"><span><img
						onclick="window.location.href ='${pageContext.request.contextPath}/Manager/addIN/${page}.do'"
						src="${pageContext.request.contextPath}/images/t01.png" /></span>添加</li>
			</ul>

		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th width="5%">序号<i class="sort"><img
							src="${pageContext.request.contextPath}/images/px.gif" /></i></th>
					<th width="15%">菜单名称</th>
					<th width="15%">parent</th>
					<th>链接地址</th>
					<th width="10%">手机端</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${infoList}" var="info" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${info.navigationText}</td>
						<td>${info.parentNavigationName}</td>
						<td class="left">${info.navigationUrl}</td>
						<td>${info.isMoveby==0?"否":"是"}</td>
						<td><a
							href='<st:url value="/Manager/delIN/${info.navigationId}/${page}.do"></st:url>'
							class="tablelink">${info.isEnabled == "0"?"删除":""}</a> <a
							href='<st:url value="/Manager/queryINInfo/${info.navigationId}/${page}.do"></st:url>'
							class="tablelink">修改</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a
					href='<st:url value="/Manager/INList/${prePage}.do"></st:url>'><span
						class="pagepre"></span></a></li>
				<li class="paginItem"><a href="javascript:;">${page}</a></li>
				<li class="paginItem"><a
					href='<st:url value="/Manager/INList/${nextPage}.do"></st:url>'><span
						class="pagenxt"></span></a></li>
			</ul>
		</div>

	</div>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>