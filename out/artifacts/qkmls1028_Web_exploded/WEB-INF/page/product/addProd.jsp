<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<li><a href="#">产品管理</a></li>
			<li><a href="#">修改产品</a></li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/Manager/OperationProdInfo.do" method="POST" enctype="multipart/form-data" >
<input name="method" type="hidden"  value="${method}" />
<input name="id" type="hidden"  value="${item.id}" />
<input name="queryname" type="hidden"  value="${vo.queryname}" />
<input name="querystatus" type="hidden"  value="${vo.querystatus}" />
<div class="formbody">
			<div class="formtitle">
				<span>产品信息</span>
			</div>
			<ul class="forminfo" style="overflow:hidden;">
			
				<li><label>商品名称：</label><input name="name" type="text"
					class="dfinput" value="${item.name}" /><i></i></li>
					<li><label>商品种类：</label><input name="category" type="text"
					class="dfinput" value="${item.category}" /><i></i></li>
					<li><label>价格：</label><input name="price" type="text"
					class="dfinput" value="${item.price}" /><i></i></li>
					<li><label>单位：</label><input name="F_Unit" type="text"
					class="dfinput" value="${item.f_Unit}" /><i></i></li>
						<li><label>重量：</label><input name="F_Zl" type="text"
					class="dfinput" value="${item.f_Zl}" /><i></i></li>
				<li><label>商品图片：</label>
				<input name="imgurl" type="hidden"  value="${item.imgurl}" />
				<c:if test="${item.imgurl!=null}">
						<img src="../${item.imgurl}" width=100 height=100/>
						<br/>
					</c:if>
					<input type="file"  name="pictureFile"/> <i></i></li>
						<li><label>描述信息：</label>
						<textarea name="description" style="width:350px;" rows="8" cols="50"  >${item.description }</textarea><i></i></li>
					<li></li>
					<li style="text-align:right; margin-top:30px;">
							<label>&nbsp;</label>  
							<input style="margin-right:100px;" name=""type="submit" class="btn" value="确认保存" />
						</li>
			</ul>
						
		</div></form>
</div>
</body>
</html>