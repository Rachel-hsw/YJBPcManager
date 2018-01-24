<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
   <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <title>商品盘库损益表</title>
    <style type="text/css">
    	.selectAll{
    		width: 150px;
    		height:30px;
    		border-color: #C9C9C9;
    		color:#909090;
    	}
    </style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">库存管理</a></li>
			<li><a href="#">盘点仓库</a></li>
		</ul>
	</div>
	<div class="container">
     <h4 style="padding:10px 0; margin: 20px 0 50px; text-align: center;" class="layui-bg-blue">商品盘库损益表</h4>
     <!--====top start=====-->
 	<div class="row" style="padding:10px;">
 		<div class="col-md-4 col-sm-4 col-lg-4">
	 			<span>盘货仓库：</span>
		        <select name="" class="selectAll" id="selectall">
		        <option value="1000">请选择盘货仓库</option>
		          	<c:forEach items="${arealist}" var="item">
										<option value="${item.FId}"
										<c:if test="${item.FId==FW_Id}">
										selected='selected'
										</c:if>
										 >${item.FName}</option>
									</c:forEach>
								
		       请选择盘货仓库 </select>
 		</div>
 		<div class="col-md-4 col-sm-4 col-lg-4"><span>盘点单号：</span><span class="Porder">${PDDH}  </span></div>
 		<div class="col-md-4 col-sm-4 col-lg-4"><span>日期：</span><span class="Pdate" ><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%></span></div>
 	</div>
 	<!--====top end=====-->
 	
 	<!--====table start=====-->
     <table class="table table-bordered table-hover table-striped">
        <thead>
            <tr>
                <th>序号</th>
                <th>商品名称</th>
                <th>规格型号</th>
                <th>库存数量</th>
                <th>实际数量</th>
                <th>损溢数量</th>
                <th>单位</th>
                <th>单价</th>
                <th>金额</th>
                <th>备注</th>
            </tr>
        </thead>
        <tbody id="tableBody">
      <%--   <c:forEach var="x" begin="0" end="9" step="1">
        <tr>
            <td>${x+1}</td>
            <td>${Inventory[x].name}</td>
            <td>${Inventory[x].category}</td>
            <td>${Inventory[x].FNum_E}</td>
            <td class="edit"></td>
            <td></td>
            <td>${Inventory[x].f_Unit}</td>
            <td>${Inventory[x].price}</td>
            <td>${Inventory[x].price}</td>
            <td class="Remarks">45</td>
            <td style="display:none">${Inventory[x].id}</td>
        </tr>  
        </c:forEach>  --%>
      	<c:forEach items="${Inventory}" var="item">
        <tr>
            <td>${s['index']+1}</td>
            <td>${item.name}</td>
            <td>${item.category}</td>
            <td>${item.FNum_E}</td>
            <td class="edit"></td>
            <td> </td>
            <td>${item.f_Unit}</td>
            <td>${item.price}</td>
            <td>${item.price}</td>
            <td class="Remarks"> </td>
            <td style="display:none">${item.id}</td>
        </tr>  
        </c:forEach>
        </tbody>
    </table>
 	<!--====table end=====-->
 	
 	<!--======footer start========-->
 	<div class="row" style="padding:0 20px;">
 		<div class="col-md-4 col-sm-4 col-lg-4"><span>制单人：</span><span class="zhidan">${managerUser.name} </span></div>
 		<div class="col-md-4 col-sm-4 col-lg-4">
 			<!---->
 			<span>填单人：</span>
	        <select name="" class="selectAll tiandan">
	          	
	            	<c:forEach items="${userlist}" var="item">
										<option value="${item.userid}">${item.username}</option>
									</c:forEach>
	        </select>
 			<!---->
 		</div>
 		<div class="col-md-4 col-sm-4 col-lg-4">
 			<span>审核员：</span>
	        <select name="" class="selectAll shenhe">
	         	<c:forEach items="${userlist}" var="item">
										<option value="${item.userid}">${item.username}</option>
									</c:forEach>
	        </select>
 		</div>
 	</div>
 	<!--======footer end========-->
 	
 	 <button type="button" id="subForm" class="btn btn-primary pull-right" style="margin-right: 5%; margin-top:20px;">确 认 提 交 </button>
 	</div>
 	<!--======form submit========-->
 	<form action="${pageContext.request.contextPath}/Manager/InventoryWSUpdates.do" method="post" id="form" style="display: none;"></form>
 	<form action="${pageContext.request.contextPath}/Manager/InventoryWS.do" method="post" id="selectForm" style="display: none;"></form>
 	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/public.js"></script>
 	<script type="text/javascript">
 			layui.use(['layer'],function(){
 				var layer=layui.layer;
 				var ifsucesss="${ifsucesss}";
 				if (ifsucesss!=null&&ifsucesss=="true") {
 					
 					layer.msg("成功");
 					}else if(ifsucesss!=null&&ifsucesss=="false"){
 						layer.msg("失败");
 					}
 			});
 		// 盘货仓库
 		$("#selectall").change(function(){
 			var val=$(this).val();
 			if(val!=""){
 				$("#selectall option").each(function(i){			
	 				var optionval=$("#selectall option").eq(i);
	 				if(optionval.val()==val){
	 					$("#selectForm").append(
	 						"<input name='FW_Id' value='"+val+"'>"+
	 						"<input name='FName' value='"+optionval.html()+"'>"
	 					)
	 					$("#selectForm").submit();
	 					
		 			}
	 			})
 			};
 		})
 		// 页面点击提交
 		$("#subForm").click(function(){
 		
 			var $tr=$("#tableBody tr"),num5=0,num8=0;
 			$tr.each(function(i){
 				var $txt3=$tr.eq(i).find("td").eq(3).html();
 				var $txt4=$tr.eq(i).find("td").eq(4).html();
 				var $txt5=$tr.eq(i).find("td").eq(5).html();
 				var $txt7=$tr.eq(i).find("td").eq(7).html();
 				var $txt8=$tr.eq(i).find("td").eq(8).html();
 				var $txt9=$tr.eq(i).find("td").eq(9).html();
 				var $txt10=$tr.eq(i).find("td").eq(10).html();
 				num5+=Number($txt5);
 				num8+=Number($txt8);
 				console.log($txt4+"--"+$txt5+"--"+$txt8+"--"+$txt9+"--"+$txt10);
 				$("#form").append(
 					"<input name='itemsList["+i+"].FP_Q' value='"+$txt3+"'>"+
 					"<input name='itemsList["+i+"].FP_S' value='"+$txt4+"'>"+
 					"<input name='itemsList["+i+"].FP_Lost' value='"+$txt5+"'>"+
 					"<input name='itemsList["+i+"].price' value='"+$txt7+"'>"+
 					"<input name='itemsList["+i+"].F_Money' value='"+$txt8+"'>"+
 					"<input name='itemsList["+i+"].FRemark' value='"+$txt9+"'>"+
 					"<input name='itemsList["+i+"].FP_Id' value='"+$txt10+"'>"
 				);
 			});
				$("#form").append(
	 					"<input name='FSl' value='"+num5+"'>"+
	 					"<input name='F_Money' value='"+num8+"'>"+
	 					"<input name='FW_Id' value='"+$("#selectall").val()+"'>"+
	 					"<input name='FPDDH' value='"+$(".Porder").html()+"'>"+
	 					"<input name='FS_Date' value='"+$(".Pdate").html()+"'>"+
	 					"<input name='FZdy' value='"+$(".zhidan").html()+"'>"+
	 					"<input name='FPdy' value='"+$(".tiandan").val()+"'>"+
	 					"<input name='FShy' value='"+$(".shenhe").val()+"'>"
	 				);
				var a=$("#selectall").val();
	 			if (a==1000) {
	 			layui.use(['layer'],function(){
	 				var layer=layui.layer;
	 		
	 		
	 				layer.msg("请选择盘库仓库");
					return;
					
				
	 			});
	 			}else{
   			$("#form").submit();}
 		})
 		// 备注编辑
 		clickEdit($(".Remarks"));
 		// 点击编辑
 		clickEdit($(".edit"),function(val){
 			var $stock=$(this).siblings().eq(3).html();
 			var $damage=$(this).siblings().eq(4);
 			var $price=$(this).siblings().eq(6).html();
 			var $total=$(this).siblings().eq(7);
 			var $html=Number(val)-Number($stock);
 			$damage.html($html);
 			$total.html($html*Number($price));
 		});
 		window.parent.document.getElementById("index").rows="*,0"; 		
 	</script>
</body>
</html>