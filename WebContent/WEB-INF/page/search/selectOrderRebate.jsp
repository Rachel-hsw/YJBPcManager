<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
     <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<title>返利查询</title>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">综合查询</a></li>
			<li><a href="#">返利表</a></li>
		</ul>
	</div> 
	
	<div style="padding:20px 30px;">
		<form   action="${pageContext.request.contextPath}/customer/RebateList.do" method="post"  class="form-inline">
		  <div class="form-group">
		    <label for="exampleInputEmail2">订单号:</label>
		    <input type="text" class="form-control" id="exampleInputEmail2" name="queryOrderId" placeholder="订单编号">
		  </div>
		  <button type="submit" class="btn btn-default">查询</button>
		</form>
	</div>
<div class="container">
	<table class="table table-bordered">
		<tr>
			<th style="width:50px">序号</th>
		
			<th>订单编号</th>
			<th>下单时间</th>
			<th>用户名称</th>
			<th>用户ID</th>
			<th>实付金额</th>
			<th>操作</th>
		</tr>
		 <c:forEach items="${findReateOrder}" var="item"  varStatus="s">
		<tr>
			  <td>${s.index + 1}</td>
			  <td>${item.FOrder_Id}</td>
			    <td>${item.FS_Date}</td>
			    	   <td>${item.name}</td>
			   <td>${item.user_id}</td>
				<td>${item.fje}</td>
			<td><a href="${pageContext.request.contextPath}/customer/findReateOrderInfo/${item.FBillID}.do">明细</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript">
window.parent.document.getElementById("index").rows="*,0";
</script>
</body>
</html>