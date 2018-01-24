<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
     <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
   <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>明细页</title>
    <script src="layui/weui-distpicker.js"></script>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">综合查询</a></li>
				<li><a href="#">返利表</a></li>
			<li><a href="#">返利明细</a></li>
		</ul>
	</div> 
<div class="container">
    <table class="table table-bordered table-hover table-striped">
     
        <thead>
            <tr>
                <th style="width:50px;">序号</th>
                <th>用户ID</th>
                <th>用户名称</th>
                 <th>返利名称</th>
                <th>返利</th>
              <!--   <th>电子币</th> -->
            </tr>
        </thead>
        <tbody>
         
          <c:forEach items="${requestScope.findReateOrderInfo}" var="item" varStatus="s">
				<tr>
			  <td>${s.index + 1}</td>
            <td>${item.FUser_Id }</td>
            <td>${item.name }</td>
            <td>${item.FRebate_name}<c:if test="${s.index==0}">(总支付金额:${item.FIntegral})</c:if></td>
            <td>${item.FRebate}</td>
          <%--   <td>${item.fe_vouche}</td> --%>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
	<button type="button" class="btn btn-success"
					onclick="window.history.go(-1)"
					style="float:right; margin-top: 10px;">返回</button>
</body>
</html>