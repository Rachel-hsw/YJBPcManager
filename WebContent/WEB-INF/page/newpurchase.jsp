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

   

<title>商品采购入库单</title>
<style>
body {
	min-width: 1000px;
}

.top {
	padding: 2px 0;
	background: #FFF7EA;
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
	background-color: #ffdca9 !important;
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
.hidden-moment{
display: none !important;
}
</style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">进货管理</a></li>
			<li><a href="#">采购进货</a></li>
		</ul>
	</div>
<input type="hidden" id="ifsucesss" value="${ifsucesss}"/>

				<p style="font-size: 22px;text-align:center;padding:20px;">商品采购入库单</p>
			
	<form class="layui-form"
		action="${pageContext.request.contextPath}/Manager/purchasingUpdates.do"
		method="post">
		<!-- ------------------头部第一行----------------------- -->
		<div class="top">
		<div class="layui-col-xs4">
			<div class="layui-inline" >
				<label class="layui-form-label">入库日期：</label>
				<div class="layui-input-inline">
					<!-- placeholder="yyyy-MM-dd" -->
					<input type="text" name="FS_Date" class="layui-input"
						readonly="readonly"
						value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>">
				</div>
			</div>
			</div>
			<div class="layui-col-xs4">
			<div class="layui-inline" >
				<label class="layui-form-label">入库编号：</label>
				<div class="layui-input-inline">
					<input type="text" name="FOrder_Id" class="layui-input"
						readonly="readonly"
						value="J<%=new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())%>000">
				</div>
			</div>
			</div>
			<!-- -----------------头部第二行------------------------ -->
			<div style="margin-top: 4px;">
				<div class="layui-row">
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">进货单位：</label>
							<div class="layui-input-inline purchasingunit">
								<input type="text" class="layui-input " placeholder="双击选择" disabled >

								<input name="FSupplier_Id" class="kind" value="" type="hidden"  required>
							</div>
						</div>
					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">进货仓库：</label>
							<div class="layui-input-inline">
								<select name="FW_Id" id="" class="layui-input">

									<c:forEach items="${arealist}" var="item">
										<option value="${item.FId}">${item.FName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">制单人：</label>
							<div class="layui-input-inline">
								<input name="FZdy" type="text" class="layui-input"
									readonly="readonly" value="${managerUser.name}">
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
									placeholder="必填">
							</div>
						</div>
					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">采购员：</label>
							<div class="layui-input-inline">
								<select name="FJsr" id="" class="layui-input">
									<c:forEach items="${userlist}" var="item">
										<option value="${item.userid}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
					<div class="layui-col-xs4">
						<div class="layui-inline">
							<label class="layui-form-label">驾驶员：</label>
							<div class="layui-input-inline">
								<select name="FS_J_Id" id="" class="layui-input">
									<c:forEach items="${driverlist}" var="item">
										<option value="${item.FId}">${item.FName}</option>
									</c:forEach>
								</select>
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
							<td class="click"><input class="inp" type="text" disabled></td>
							<td class="td1"><input class="inp" type="text" disabled></td>
							<td class="td2"><input class="inp" type="text" disabled></td>
							<td class="td3"><input name="itemsList[${x}].FP_Price"
								class="inp" type="text"></td>
							<td class="td4"><input name="itemsList[${x}].FP_Num"
								class="inp" type="text"></td>
							<td class="td5"><input name="itemsList[${x}].FP_Zl"
								class="inp" type="text"></td>
							<td class="td6"><input name="itemsList[${x}].FP_Money"
								class="inp" type="text"></td>
							<td class="td7"><input name="itemsList[${x}].FRemark"
								class="inp" type="text"></td>
							<td class="td8" style="display:none;"><input name="itemsList[${x}].FP_Id"
								class="inp" type="hidden"></td>
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
						<input type="text" class="layui-input huang" name="FZl" id="myzl"  placeholder="" pattern="[0-9]*"  required>
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">合计数量：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input huang"  name="FSl" id="mynum"  placeholder=""  pattern="[0-9]*"    required>
					</div>
				</div>
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">合计金额：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input huang" name="FJe" id="mymoney" placeholder=""  pattern="[0-9]*"     required>
					</div>
				</div>
				<!-- <div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">运价：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" placeholder="">
					</div>
				</div> -->
				<div class="layui-inline" style="margin-bottom: 5px;">
					<label class="layui-form-label">运费：</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" placeholder="" name="FYf">
					</div>
				</div>
			</div>



			<div class="layui-col-xs4">
				<fieldset class="layui-elem-field site-demo-button"
					style="padding-left: 10px;">
					<legend>功能区</legend>
					<div>
						<button class="layui-btn layui-btn-normal tongji">统计</button>
						<button class="layui-btn layui-btn-normal del">删除行</button>
						<button class="layui-btn layui-btn-warm clear">清空表</button>
						<!--	<div class="layui-input-inline">
						<select name="" id="" style="height:38px; border:1px solid #f60; margin: 5px 0;">
							<option value="">1</option>
							<option value="">2</option>
							<option value="">3</option>
						</select>
					</div>
				-->
						<div class="layui-form-item" pane="">
						<!-- 	<input type="checkbox" name="" lay-skin="primary" title="仓库" class="hidden-moment">
							<input type="checkbox" name="" lay-skin="primary" title="打印" class="hidden-moment"> -->
						</div>
					</div>
				</fieldset>
			</div>
			<div class="layui-col-xs4">
				<div style="margin-left: 20px; margin-top: 20px;">
					<input type="submit"
						class="layui-btn layui-btn-big layui-btn-primary layui-btn-radius"
						value="采购保存">
				</div>
			<!-- 	<div style="margin-left: 20px; margin-top: 20px;">
					<button
						class="layui-btn layui-btn-big layui-btn-primary layui-btn-radius hidden-moment" value="废弃退出"></button>
				</div> -->
			</div>
		</div>
	</form>
	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
	<script>
	// 统计区
	$(".huang").focus(function(){
		$(this).blur();
	})
  function fn(){
	console.log($(this).html())
  }
 	layui.use(["laydate","table","layer"],function(){
 		var layer=layui.layer;
 		var ifsucesss="${ifsucesss}";
		if (ifsucesss!=null&&ifsucesss=="true") {
			
			layer.msg("成功");
			}else if(ifsucesss!=null&&ifsucesss=="false"){
				layer.msg("${result}");
			}
 	});
	/*----------------- 商品名称双击事件开始---------------------*/
	$(".click").each(function(i){
		$(this).dblclick(function(){
			layer.open({
				type: 1,
				title: '商品列表',
				btn:["取消"],
				area: ['1000px', '600px'],
				fix: false, //不固定
				maxmin: true,
				shadeClose:true,
				content: "<table class='layui-table'>"+
				"<thead>"+
					"<tr>"+
						"<th>序号</th>"+
						"<th>商品名称</th>"+
						"<th>规格</th>"+
						"<th>单位</th>"+
						"<th>单价</th>"+
						"<th>数量</th>"+
						"<th>重量</th>"+
						"<th>金额</th>"+
						"<th>备注</th>"+
					"</tr>"+
					"</thead>"+
					"<tbody>"+
					 <c:forEach items="${productlist}" var="item">
					 "<tr class='purchase'>"+
						"<td>1</td>"+
						"<td class='t1'>${item.name}</td>"+
						"<td class='t2'>${item.category}</td>"+
						"<td class='t3'>${item.f_Unit}</td>"+
						"<td class='t4'> <fmt:formatNumber value="${item.price}" pattern="#.0"/></td>"+
						"<td class='t5'>${item.f_Num}</td>"+
						"<td class='t6'>${item.f_Zl}</td>"+
						"<td class='t7'> <fmt:formatNumber value="${item.price}" pattern="#.00"/></td>"+
						"<td class='t8'>0</td>"+	
						"<td class='t9' style='display:none'>${item.id}</td>"+
					"</tr>"+
					 </c:forEach>			
					"</tbody>"+
				"</table>"
			})
			var that=$(this).parent();
			$(".purchase").click(function(){
				
				var t1=$(this).find(".t1").html(),
					t2=$(this).find(".t2").html(),
					t3=$(this).find(".t3").html(),
					t4=$(this).find(".t4").html(),
					t5=$(this).find(".t5").html(),
					t6=$(this).find(".t6").html(),
					t7=$(this).find(".t7").html(),
					t8=$(this).find(".t8").html(),
					t9=$(this).find(".t9").html();
					that.find(".click input").val(t1);
					that.find(".td1 input").val(t2);
					that.find(".td2 input").val(t3);
					that.find(".td3 input").val(t4);
					that.find(".td4 input").val(t5);
					that.find(".td5 input").val(t6);
					that.find(".td6 input").val(t7);
					that.find(".td7 input").val(t8);
					that.find(".td8 input").val(t9);
					layer.closeAll('page');
					tableVal();
					
				
					
			})
			
		})
	});
	/*----------------- 商品名称双击事件结束---------------------*/

	/*----------------- 进货单位开始---------------------*/
	$(".purchasingunit").dblclick(function(){
			layer.open({
				type: 1,
				title: '进货单位',
				btn:["取消"],
				area: ['1000px', '600px'],
				fix: false, //不固定
				maxmin: true,
				shadeClose:true,
				content: "<table class='layui-table'>"+
						"<thead>"+
							"<tr>"+
								"<th>单位名称</th>"+
								"<th>单位简称</th>"+
								"<th>拼音首字母</th>"+
								"<th>联系人</th>"+
								"<th>联系地址</th>"+
								"<th>联系电话</th>"+
							"</tr>"+
						"</thead>"+
						"<tbody class='eng'>"+
						 <c:forEach items="${supplierlist}" var="item" varStatus="ittt">
						   
		                   
						"<tr>"+
							"<td class='com'>${item.FName}</td>"+
							"<td>${item.FNick}</td>"+
							"<td>${item.FPym}</td>"+
							"<td>${item.FContacts}</td>"+
							"<td>${item.FAddress}</td>"+
							"<td>${item.FTel}</td>"+
							"<td style='display:none'><input type='text' class='kintwo' value='${item.FId}'></td>"+
						"</tr>"+
						 </c:forEach>
						"</tbody>"+
					"</table>"
			})
			var that=$(this).find("input");
			$("tbody.eng tr").click(function(){
				var chil=$(this).find(".kintwo").val();
				that.val($(this).find(".com").html());
		/* 		 console.log($(".kintwo").val());  */
				layer.closeAll("page");
				$(".kind").val(chil);
				
			})
	})
	/*----------------- 进货单位结束---------------------*/
	$("tbody.www tr").click(function(i){
		for(var i=0;i<$("tbody.www tr").length;i++){
			$("tbody.www tr").eq(i).removeClass("on");
		}
			$(this).addClass("on");
	})
	
	// 删除行
	$(".del").click(function(){
		var tr=$("tbody tr");
		for(var i=0;i<tr.length;i++){
			if(tr.eq(i).hasClass("on")){
				tr.eq(i).find(".click input").val("");
				tr.eq(i).find(".td1 input").val("");
				tr.eq(i).find(".td2 input").val("");
				tr.eq(i).find(".td3 input").val("");
				tr.eq(i).find(".td4 input").val("");
				tr.eq(i).find(".td5 input").val("");
				tr.eq(i).find(".td6 input").val("");
				tr.eq(i).find(".td7 input").val("");
			}
		}
		return false;
	})
	// 清空表
	$(".clear").click(function(){
		var tr=$("tbody tr");
		for(var i=0;i<tr.length;i++){
			tr.eq(i).find(".click input").val("");
			tr.eq(i).find(".td1 input").val("");
			tr.eq(i).find(".td2 input").val("");
			tr.eq(i).find(".td3 input").val("");
			tr.eq(i).find(".td4 input").val("");
			tr.eq(i).find(".td5 input").val("");
			tr.eq(i).find(".td6 input").val("");
			tr.eq(i).find(".td7 input").val("");
		}
		return false;
	})
	// 统计
	$(".tongji").click(function(){
		var sum=0.00;
		var Numsum=0;
		var Zlsum=0;
		$(".www tr").each(function(i){
		/* 	console.log($(".www tr").eq(i).find(".inp").eq(6).val()); */
		
	//合计金额
		if ($(".www tr").eq(i).find(".inp").eq(6).val() !='') { 
		
			sum =Number($(".www tr").eq(i).find(".inp").eq(6).val())+sum;
			$("#mymoney").val(sum);
		}
		//合计数量
		if ($(".www tr").eq(i).find(".inp").eq(4).val() !='') { 
			
			Numsum =parseInt($(".www tr").eq(i).find(".inp").eq(4).val())+Numsum;
			$("#mynum").val(Numsum);
		}
		//合计重量
			if ($(".www tr").eq(i).find(".inp").eq(5).val() !='') { 
			
				Zlsum =parseInt($(".www tr").eq(i).find(".inp").eq(5).val())+Zlsum;
			$("#myzl").val(Zlsum);
		}
		})
		
		
		return false;
	})
	function tableVal(){
		//数量金额计算
		$(".www tr").each(function(i){
			var val3=$(".www tr").eq(i).find(".inp").eq(3).val();
			var val4=$(".www tr").eq(i).find(".inp").eq(4).val();
			var valnot1=val3,valnot2=val4;
			if(val3!=""){
				$(".www tr").eq(i).find(".inp").eq(3).blur(function(){
					var val33=$(".www tr").eq(i).find(".inp").eq(3).val();
					if(val33!=val3){ 
						valnot1=val33;
						$(".www tr").eq(i).find(".inp").eq(6).val(Number(val33)*Number(valnot2));
					}
				})
			}
			if(val4!=""){
			
				$(".www tr").eq(i).find(".inp").eq(4).blur(function(){
					var val44=$(".www tr").eq(i).find(".inp").eq(4).val();
					if(val44!=val4){
						valnot2=val44;
						$(".www tr").eq(i).find(".inp").eq(6).val(Number(val44)*Number(valnot1));
					}
				})
			}
		})
	}
	window.parent.document.getElementById("index").rows="*,0";
  </script>
</body>
</html>