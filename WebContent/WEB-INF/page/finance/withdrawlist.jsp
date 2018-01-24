<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <title>提现申请列表</title>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">财务管理</a></li>
			<li><a href="#">提现申请 审核列表</a></li>
		</ul>
	</div>
   <div class="container">
   	<h3 style="text-align: center; padding: 10px 0;">提现申请 审核列表</h3>
   	<table class="table table-bordered table-hover table-striped">
   		<tr>
   			<th>会员名称</th>
   			<th>会员ID</th>
   			<th>转入银行账户</th>
   			<th>提现金额(￥)</th>
   			<th>申请时间</th>
   			<th>状态</th>
   			<th>操作</th>
   		</tr>
   		 <c:forEach items="${WithdrawList}" var="item"  varStatus="s">
   		<tr>
   			<td>${item.name}</td>
   			<td>${item.FUser_Id}</td>
   			<td>${item.FBankdeposit}</td>
   			<td>${item.FJe}</td>
   			<td>${item.FCreatetime}</td>
   			<td>  <c:choose>
    <c:when test="${item.FStatus==0}"> 待审核</c:when>
    <c:when test="${item.FStatus==1}">待转账</c:when>
    <c:when test="${item.FStatus==2}">已完成</c:when>
      <c:when test="${item.FStatus==3}">已驳回</c:when>
   <c:otherwise>看不懂了，请找程序员小姐姐  </c:otherwise>
   </c:choose></td>
   			<td>
   				<a href="${pageContext.request.contextPath}/Manager/queryWithdrawList.do?FId=${item.FId}&FStatus=${item.FStatus}">详情</a>
   			</td>
   		</tr>
   	</c:forEach>
   	</table>
   </div>
   <script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script>
 	layui.use(["laydate","table","layer"],function(){
 		var layer=layui.layer;
 		var ifsucesss="${ifsucesss}";
		if (ifsucesss!=null&&ifsucesss=="true") {
			
			layer.msg("成功");
			}else if(ifsucesss!=null&&ifsucesss=="false"){
				layer.msg("${result}");
			}
 	});
 	window.parent.document.getElementById("index").rows="*,0";
 	</script>
</body>
</html>
