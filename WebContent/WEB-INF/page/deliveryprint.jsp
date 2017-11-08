<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
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

<title>发货页</title>
<style>
.form-group {
	margin-top: 10px;
}

label {
	width: 80px;
	text-align: justify;
	text-justify: distribute-all-lines;
	text-align-last: justify;
}

#table-title tr th {
	text-align: center;
}

.left {
	margin-left: 170px;
}

@media screen and (max-width: 1200px) {
	.left {
		margin-left: 50px;
	}
}

@media screen and (max-width: 995px) {
	.left {
		margin-left: 0px;
	}
}
</style>
</head>
<body>
	<div class="layui-container" id="content">
		<h2 style="text-align: center;">双鸿公司</h2>

		<p style="text-align: center;" id="address">
			<span>地址：</span><span>苏州吴中双鸿</span>
		</p>
		<h3 style="text-align: center;">发货清单</h3>
		<form class="form-inline"
			action="${pageContext.request.contextPath}/Manager/deliverysumbit.do"
			method="post" id="form">
			<input type="hidden" value="${orderlist[0].FBillID}" name="FBillID">
			<input type="hidden" value="${orderlist[0].FAddress}" name="FW_ID">
			<div class="form-group">
				<label for="inupt3">单号:</label> <input type="text"
					class="form-control input-sm" id="inupt3" name="FOrder_Id"
					value="${orderlist[0].FOrder_Id}" placeholder="" readonly>
			</div>
			<div class="form-group left">
				<label>发货仓库:</label> <select name="FId" value=""
					class="form-control input-sm select1" style="width: 168px;">
					<c:forEach items="${arealist}" var="item">
						<option value="${item.FId}">${item.FName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group left">
				<label for="inupt5">发货日期:</label> <input type="text"
					class="form-control input-sm" id="inupt5" name="FDelivery_Time"
					value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>"
					placeholder="" readonly>
			</div>
			<br>
			<div class="form-group">
				<label for="inupt0">会员ID:</label> <input type="text"
					class="form-control input-sm" id="inupt0" name="" readonly
					value="${orderlist[0].user_id}" placeholder="">
			</div>
			<div class="form-group  left">
				<label for="inupt01">会员昵称:</label> <input type="text"
					class="form-control input-sm" id="inupt01" name="" readonly
					value="${orderlist[0].FSname}" placeholder="">
			</div>
			<div class="form-group left">
				<label for="inupt02">联系电话:</label> <input type="text"
					class="form-control input-sm" id="inupt02" name="" readonly
					value="${orderlist[0].ftel}" placeholder="">
			</div>
			<div class="form-group">
				<label>快递公司:</label> <select name="FExpress" value=""
					class="form-control input-sm select2" style="width: 168px;">
					<option>中通</option>
					<option>圆通</option>
					<option>申通</option>
					<option>韵达</option>
					<option>顺丰</option>
				</select>
			</div>
			<div class="form-group left">
				<label for="inupt9">快递单号:</label> <input type="text"
					class="form-control input-sm" id="inupt9" name="FCourier_Number"
					value="" placeholder="" required>
			</div>
			<div class="form-group left hidden-print">
				<input type="submit" class="btn btn-success"
					style="margin-left: 100px; margin-top: 10px;" value="确认发货">
				<button id="print" type="button" class="btn btn-success"
					style="margin-left: 10px; margin-top: 10px;">打印清单</button>
			</div>

			<table class="table table-bordered table-hover table-condensed"
				id="table-title" style="margin-top: 20px;">
				<thead>
					<tr>
						<th style="width: 50px;">序号</th>
						<th>商品 名称</th>
						<th>规格 型号</th>
						<th>单位</th>
						<th>单价</th>
						<th>数量</th>
						<th>重量</th>
						<th>金额</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="x" begin="0" end="9" step="1">
						<tr>
							<td>${x+1}<%-- --${orderdetail[x].id}--${orderdetail[x].FP_Num} --%></td>
							<input type="hidden" value="${orderdetail[x].id}" name="itemsList[${x}].FP_Id">
							<td>${orderdetail[x].name}</td>
							<td>${orderdetail[x].category}</td>
							<td>${orderdetail[x].f_Unit}</td>
							<td>${orderdetail[x].price}</td>
							<td><input type="hidden" value="${orderdetail[x].FP_Num}" name="itemsList[${x}].FP_Num"></td>
							<td>${orderdetail[x].FP_Zl}</td>
							<td>${orderdetail[x].FP_Money}</td>
							<td>${orderdetail[x].FRemark}</td>
						</tr>
					</c:forEach>
					<tr>
						<td>合计</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${orderlist[0].FSl}</td>
						<td>${orderlist[0].FZl}</td>
						<td>${orderlist[0].fje}</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<div class="layui-row">
				<div class="layui-col-xs4">
					<div class="grid-demo grid-demo-bg1">
						<div class="form-group">
							<label for="inupt55">制单人:</label> <input style="border: none;"
								type="text" id="inupt55" name="" value="${managerUser.name}"
								readonly>
						</div>
					</div>
				</div>
				<div class="layui-col-xs4">
					<div class="grid-demo">
						<div class="form-group">
							<label for="inupt11">发货人:</label> <input style="border: none;"
								type="text" id="inupt11" name="FConsignor"
								value="${managerUser.name}" readonly>
						</div>
					</div>
				</div>
				<div class="layui-col-xs4">
					<div class="grid-demo grid-demo-bg1">
						<div class="form-group">
							<label for="inupt22">签收人:</label> <input style="border: none;"
								type="text" id="inupt22" name="" value="${managerUser.name}"
								readonly>
						</div>
					</div>
				</div>
			</div>
			<div style="text-align: right" class="hidden-print">
				<button type="button" class="btn btn-success"
					onclick="window.history.go(-1)"
					style="margin-left: 10px; margin-top: 10px;">返回</button>
			</div>
		</form>
	</div>
	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script language="javascript"
		src="${pageContext.request.contextPath }/layui/jquery-1.4.4.min.js"></script>
	<script language="javascript"
		src="${pageContext.request.contextPath }/layui/jquery.jqprint-0.3.js"></script>
	<script>
		$("#print")
				.click(
						function() {
							layui
									.use(
											"layer",
											function() {
												var layer = layui.layer
												var tableHtml = $(
														'#table-title').html();
												layer
														.open({
															type : 1,
															btn : [ '打印', '取消' ],
															area : [ "1000px",
																	"600px" ],
															content : "<div id='con' class='layui-container'>"
																	+ "<h2 style='text-align: center;'>"
																	+ $(
																			'#content h2')
																			.html()
																	+ "</h2>"
																	+ "<p style='text-align: center;'>"
																	+ $(
																			"#address")
																			.html()
																	+ "</p>"
																	+ "<h3 style='text-align: center;'>发货清单</h3>"
																	+ "<table style='width:100%;font-size: 12px; margin-bottom: 0px;'>"
																	+ "<tbody>"
																	+ "<tr>"
																	+ "<td><span style='display: inline-block; width: 65px;'>单号：</span><span>"
																	+ $(
																			'#inupt3')
																			.val()
																	+ "</span></td>"
																	+ "<td><span style='display: inline-block; width: 65px;'>发货仓库：</span><span>"
																	+ $(
																			'.select1')
																			.val()
																	+ "</span></td>"
																	+ "<td><span style='display: inline-block; width: 65px;'>发货日期：</span><span>"
																	+ $(
																			'#inupt5')
																			.val()
																	+ "</span></td>"
																	+ "</tr>"
																	+ "<tr>"
																	+ "<td><span style='display: inline-block; width: 65px;'>会员ID：</span><span>"
																	+ $(
																			'#inupt0')
																			.val()
																	+ "</span></td>"
																	+ "<td><span style='display: inline-block; width: 65px;'>会员昵称：</span><span>"
																	+ $(
																			'#inupt01')
																			.val()
																	+ "</span></td>"
																	+ "<td><span style='display: inline-block; width: 65px;'>联系电话：</span><span>"
																	+ $(
																			'#inupt02')
																			.val()
																	+ "</span></td>"
																	+ "</tr>"
																	+ "<tr>"
																	+ "<td><span style='display: inline-block; width: 65px;'>快递公司：</span><span>"
																	+ $(
																			'.select2')
																			.val()
																	+ "</span></td>"
																	+ "<td><span style='display: inline-block; width: 65px;'>快递单号：</span><span>"
																	+ $(
																			'#inupt9')
																			.val()
																	+ "</span></td>"
																	+ "</tr>"
																	+ "</tbody>"
																	+ "</table>"
																	+ "<table class='table table-bordered table-condensed' style='width: 100%;font-size: 12px; margin-bottom: 5px;'>"
																	+ $(
																			'#table-title')
																			.html()
																	+ "</table>"
																	+ "<div style='display: flex;'>"
																	+ "<div style='flex: 1'><span>制单人:</span><span>"
																	+ $(
																			'#inupt55')
																			.val()
																	+ "</span></div>"
																	+ "<div style='flex: 1'><span>发货人:</span><span></span>"
																	+ $(
																			'#inupt11')
																			.val()
																	+ "</div>"
																	+ "<div style='flex: 1'><span>签收人:</span><span></span>"
																	+ $(
																			'#inupt22')
																			.val()
																	+ "</div>"
																	+ "</div>"
																	+ "</div>",
															shadeClose : true,
															success : function(
																	layero) {
																var btn = layero
																		.find('.layui-layer-btn0');
																btn
																		.click(function() {
																			$(
																					"#con")
																					.jqprint();
																		})
															}
														})
											})
						})
		window.parent.document.getElementById("index").rows = "*,0";
	</script>
</body>
</html>