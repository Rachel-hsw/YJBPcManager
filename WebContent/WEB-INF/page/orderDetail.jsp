<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>明细页</title>
</head>
<body>
    <table class="table table-bordered table-hover table-striped">
        <h4 style="padding:10px 0; text-align: center;" class="layui-bg-blue">明细页</h4>
        <thead>
            <tr>
                <th>序号</th>
                <th>商品名称</th>
                <th>规格型号</th>
                <th>单位</th>
                <th>单价</th>
                <th>数量</th>
                <th>重量</th>
                <th>金额</th>
            </tr>
        </thead>
        <tbody>
       
			<c:forEach items="${orderdetail}" var="item" varStatus="s">
				<tr>
					<td>${s['index']+1}</td>
					<td>${item.name}</td>
					<td>${item.category}</td>
					<td>${item.f_Unit}</td>
					<td><fmt:formatNumber value="${item.price}" pattern="#.0"/></td>
					<td>${item.FP_Num}</td>
					<td>${item.FP_Zl}</td>
					<td><fmt:formatNumber value="${item.FP_Money}" pattern="#.00"/></td>
				</tr>
			</c:forEach>

		</tbody>
    </table>
</body>
</html>