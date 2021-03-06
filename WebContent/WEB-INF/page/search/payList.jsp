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

<title>采购订单列表</title>
<style>
</style>
</head>
<body style="min-width: 1400px;">
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">销售管理</a></li>
			<li><a href="#">销售发货</a></li>
		</ul>
	</div>
	<form action="${pageContext.request.contextPath}/Manager/purchasePay.do" method="POST" style="margin-top: 20px;">
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
				<th>供应商</th>
				<th>付款日期</th>
				<th>付款ID</th>
				<th>付款方式</th>
				<th>付款总金额</th>
				<th>付款手续费</th>
				<th>到账金额</th>
				<th>操作</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${mianList}" var="item"  varStatus="s">
			<tr>
				<td>${s['index']+1}</td>
				<td>${item.FSupplier_Id}</td>
				<td>${item.FS_Date}</td>
				<td>${item.FPay_Id}</td>
				<td>${item.FIsDefault}</td>
				<td class="telSplit">${item.FFk_Zje}</td>
				<td>${item.FSxf}</td>
				<td>${item.FFk_Je}</td>
				<td><a name="mx"
					href="${pageContext.request.contextPath}/Manager/payListDetail.do?FId=${item.FId}&CGID=${item.CGID}"
					>明细</a></td>
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