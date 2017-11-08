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
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<title>订单明细表</title>
<style>
</style>
</head>
<body style="min-width: 1400px;">

	<form action="${pageContext.request.contextPath}/Manager/delivery.do" method="POST" style="margin-top: 20px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">日期</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="test1" name="begintime" value="${begintime}"
							placeholder="迄今为止">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">至</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="test1-1" name="endtime" value="${endtime}"
							placeholder="">
					</div>
				</div>
				<input type="submit" value="查询" class="layui-btn">
			</div>
		</div>
	</form>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>订单号</th>
				<th>订单日期</th>
				<th>会员ID</th>
				<th>会员姓名</th>
				<th>手机号码</th>
				<th>总数量</th>
				<th>总重量</th>
				<th>操作</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${orderlist}" var="item"  varStatus="s">
			<tr>
				<td>${s['index']+1}</td>
				<td>${item.FOrder_Id}</td>
				<td>${item.FS_Date}</td>
				<td>${item.user_id}</td>
				<td>${item.FSname}</td>
				<td class="telSplit">${item.ftel}</td>
				<td>${item.FSl}</td>
				<td>${item.FZl}</td>
				<td><a href="${pageContext.request.contextPath}/Manager/deliveryprint.do?FBillID=${item.FBillID}" target="_self">发货</a> <a name="mx"
					onclick="window.parent.document.getElementById('index').rows='400,*';"
					href="${pageContext.request.contextPath}/Manager/orderDetail.do?FBillID=${item.FBillID}"
					target="rightbottomFrame">明细</a></td>
				<td>${item.FRemark}</td>
			</tr>
				</c:forEach>
		</tbody>
	</table>
	<script src="${pageContext.request.contextPath }/layui/public.js"></script>
	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script>
		/* =========电话号码用空格分割开始========= */
		telSplit($(".telSplit"));
		/* =========电话号码用空格分割结束========= */
		layui.use([ 'laydate' ], function() {
			var laydate = layui.laydate
			laydate.render({
				elem : '#test1'
			});
			laydate.render({
				elem : '#test1-1'
			});
		})
		$(".goods").click(function() {
			var tdhtml = $(this).parent().siblings("td");
			var arr;
			for (var i = 0; i < tdhtml.length; i++) {
				arr += tdhtml[i].innerHTML;
			}
			layer.open({
				type : 1,
				area : [ "1000px", "600px" ],
				shadeClose : true,
				shade : 0.2,
				content : arr
			})
		})
		window.parent.document.getElementById("index").rows="*,0";
	</script>
</body>
</html>