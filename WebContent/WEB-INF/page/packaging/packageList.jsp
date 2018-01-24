<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<title>生产组装</title>
<style>
</style>
</head>
<body style="min-width: 1400px;">
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">库存管理</a></li>
			<li><a href="#">产品组装</a></li>
		</ul>
	</div>
	<form action="${pageContext.request.contextPath}/Manager/packageAdd.do" method="POST" style="margin-top: 20px;">
		
				<input type="submit" value="增加+" class="layui-btn">
			
	</form>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>套餐名称</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>操作</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${packageList}" var="item"  varStatus="s">
			<tr>
				<td>${s['index']+1}</td>
				<td>${item.p_Package}</td>
				<td>${item.p_ZNum}</td>
				<td>${item.p_ZMoney}</td>
				<td><a href="${pageContext.request.contextPath}/Manager/queryProductbyId.do?P_Package_id=${item.p_Package_id}" target="_self">修改</a> <a name="mx"
					onclick="window.parent.document.getElementById('index').rows='400,*';"
					href="${pageContext.request.contextPath}/Manager/packageDetail.do?P_Package_id=${item.p_Package_id}"
					target="rightbottomFrame">明细</a></td>
				<td>${item.p_Remark}</td>
			</tr>
				</c:forEach>
		</tbody>
	</table>
	<script src="${pageContext.request.contextPath }/layui/public.js"></script>
	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script>
		window.parent.document.getElementById("index").rows="*,0";
	</script>
</body>
</html>