<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />

   

<title>
<c:if test="${Fstatus==1||Fstatus==2 }">
商品采购入库
</c:if>
<c:if test="${Fstatus==4||Fstatus==5 }">
商品采购出库
</c:if>
</title>
<style>
body {
	min-width: 1000px;
}

.top {
	padding: 2px 0;
	background: #FFF7EA;
	
}
.main {
	padding: 50px 0;
	
	padding-top: 50px;
	
}

tr th {
	background: #FFDCA9;
}

tbody tr:nth-child(even) {
	background: #FFF7EA;
}

.footer {
	background: #FFF7EA;
}

.on {
	background-color: #4EC2FF !important;
}

.inp {
	height: 20px;
	border: none;
	outline: none;
}
.layui-input, .layui-select, .layui-textarea {
    height: 20px;
}
.layui-form-label {
    padding: 0px;
}
.layui-table td, .layui-table th {
    padding: 0px 15px;
}
</style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">综合查询</a></li>
			<li><a href="#">采购列表</a></li>
			<li><a href="#">采购明细</a></li>
		</ul>
	</div>
	<div class="main">
		<!-- ------------------头部第一行----------------------- -->
		<div class="top">
			<div class="layui-inline">
				<label class="layui-form-label">入库日期：</label>
				<div class="layui-input-inline">
					<!-- placeholder="yyyy-MM-dd" -->
					<input type="text" name="FS_Date" class="layui-input"
						readonly="readonly"
						value="${mianList[0].FS_Date}">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 117px;">
				<p style="font-size: 22px;">
					<c:if test="${Fstatus==1||Fstatus==2 }">
商品采购入库
</c:if>
					<c:if test="${Fstatus==4||Fstatus==5 }">
商品采购出库
</c:if>
				</p>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">入库编号：</label>
				<div class="layui-input-inline">
					<input type="text" name="FOrder_Id" class="layui-input"
						readonly="readonly"
						value="${mianList[0].FOrder_Id}">
				</div>
			</div>
			<!-- -----------------头部第二行------------------------ -->
			<div style="margin-top: 4px;">
				<div class="layui-row">
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">进货单位：</label>
							<div class="layui-input-inline purchasingunit">
								<input type="text" class="layui-input " placeholder="" disabled value="${mianList[0].FSupplier_Id}">

							</div>
						</div>
					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">进货仓库：</label>
							<div class="layui-input-inline purchasingunit">
								<input type="text" class="layui-input " placeholder="" disabled value="${mianList[0].FW_Id}">

							</div>
						</div>

					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">制单人：</label>
							<div class="layui-input-inline">
								<input name="FZdy" type="text" class="layui-input"
									readonly="readonly" value="${mianList[0].FZdy}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- ------------------头部第三行---------------------- -->
			<div style="margin: 4px 0;">
				<div class="layui-row">
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">原始单号：</label>
							<div class="layui-input-inline">
								<input name="FSource_Id" type="text" class="layui-input" required
									placeholder="" value="${mianList[0].FSource_Id}">
							</div>
						</div>
					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">采购员：</label>
							<div class="layui-input-inline purchasingunit">
								<input type="text" class="layui-input " placeholder="" disabled value="${mianList[0].FJsr}">
							</div>
						</div>

					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">驾驶员：</label>
							<div class="layui-input-inline purchasingunit">
								<input type="text" class="layui-input " placeholder="" disabled value="${mianList[0].FS_J_Id}">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- --------------------表格------------------------- -->
		<div style="margin: 0px; box-shadow: 0 0 5px #aaa;">
			<table class="layui-table" style="overflow: auto; height: 400px;">
				<thead>
					<tr>
						<th style="width: 30px;">序号</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>单价</th>
						<th>数量</th>
						<th>重量</th>
						<th>金额</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="www">
					<c:forEach var="x" begin="0" end="9" step="1">
						<tr>
							<td>${x+1}</td>
							<td class="click">${itemsList[x].FP_Id}</td>
							<td class="td1">${itemsList[x].category}</td>
							<td class="td2">${itemsList[x].f_Unit}</td>
							<td class="td3">${itemsList[x].FP_Price}</td>
							<td class="td4">${itemsList[x].FP_Num}</td>
							<td class="td5">${itemsList[x].FP_Zl}</td>
							<td class="td6">${itemsList[x].FP_Money}</td>
							<td class="td7">${itemsList[x].FRemark}</td>
							
						</tr>

					</c:forEach>

				</tbody>
			</table>
		</div>
		<!-- -------------------- 底部------------------------- -->
		<div class="layui-row footer">
			<div class="layui-col-xs4">
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">合计重量：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="FZl" id="myzl" placeholder="" value="${mianList[0].FZl}" required>
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">合计数量：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input"  name="FSl" id="mynum"  placeholder="" required value="${mianList[0].FSl}">
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">合计金额：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" name="FJe" id="mymoney" placeholder="" required value="${mianList[0].FJe}">
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">运价：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" placeholder="" >
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">运费：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" placeholder="" value="${mianList[0].FYf}">
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>