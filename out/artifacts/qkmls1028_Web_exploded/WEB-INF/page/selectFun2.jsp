<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>75</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/css/token-input.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>


<script type="text/javascript">
function delRow(obj){ //参数为A标签对象
    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标
    tb.deleteRow(rowIndex); //删除当前行
}
function tanc(id,name){
	var index = window.parent.getlay();
	window.parent.document.getElementById("ParentNavigationId").value=id;
	window.parent.document.getElementById("ParentNavigationName").value=name;
	parent.layer.close(index);
}

</script>
</head>
<body>
	<table class="tablelist">
		<thead>
			<tr>

				<th width="15%">菜单名称</th>
				<th width="15%">parent</th>
				<th>链接地址</th>
				<th width="10%">手机端</th>
				<th width="10%">选择</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${infoList}" var="info" varStatus="status">
				<tr>
					<td>${info.navigationText}</td>
					<td>${info.parentNavigationName}</td>
					<td style="text-align:left;">${info.navigationUrl}</td>
					<td>${info.isMoveby==0?"否":"是"}</td>
					<td><img
						onclick="tanc('${info.navigationId}','${info.navigationText}')"
						src="${pageContext.request.contextPath}/images/t01.png" /></td>
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
				href='<st:url value="/Manager/selectFun2/null/${prePage}.do"></st:url>'><span
					class="pagepre"></span></a></li>
			<li class="paginItem"><a href="javascript:;">${page}</a></li>
			<li class="paginItem"><a
				href='<st:url value="/Manager/selectFun2/null/${nextPage}.do"></st:url>'><span
					class="pagenxt"></span></a></li>
		</ul>
	</div>
</body>

</html>
