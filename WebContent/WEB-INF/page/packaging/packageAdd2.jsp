<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<title>生产组装修改</title>
<style>
	.place{
		overflow:hidden;
	}
</style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">生产组装</a></li>
			<li><a href="#">组装列表</a></li>
		</ul>
	</div>
	 <form action="${pageContext.request.contextPath}/Manager/packageChangeSubmit.do" id="selectForm"
		method="post"  > 
<!--=====表格开始====-->
<div class="container ">

	<div class="form-inline" style="padding: 20px 0;">
		<div class="form-group">
			<label for="P_Package">套餐名称：</label>
			<input class="form-control" required type="text" id="P_Package" name="" value="${packageList[0].p_Package}"/>
		</div>&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="form-group">
			<label for="P_Remark">备注：</label>
			<input  class="form-control" required type="text" id="P_Remark" value="${packageList[0].p_Remark}"/>
			<input type="hidden" value="${packageList[0].p_Package_id}" id="P_Package_id"/>
		</div>
	</div>

    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th style="width:20px;"></th>
            <th style="width:50px;">序号</th>
            <th>商品名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>金额</th>       
        </tr>
        </thead>
        <tbody>
      
         <c:forEach items="${productList}" var="item"  varStatus="s">
        <tr>
            <td><input class="check" name="delId" type="checkbox" value="${item.id}"></td>
            <td>${s['index']+1}</td>
            <td>${item.name}</td>
            <td class="t3">${item.price}</td>
            <td class="num t4" style="padding:0;"><input type="text" value="${item.p_Num}" readonly="readonly" style="width:100%;height:100%; text-indent:10px;"/></td>
            <td class="money t5"><input type="text" value="${item.p_Money}" readonly="readonly"/></td>   
            </tr>
            </c:forEach>
        
        <tr>
						<td></td>
						<td>合计</td>
						<td></td>
						<td></td>			
						<td id="znum"></td>
						<td id="zmoney"></td>
					</tr>
        </tbody>
    </table>
<input type="button" class="btn btn-warning pull-right" value="修改套餐√" id="complete"> 
	</form>
</div>
    <script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script>
/*  判断默认选中  */
 (function(){
		 $("tr td.t4").find("input").each(function(i) {
			if(Number($(this).val())>0) {
				$(this).parent().siblings().eq(0).find("input").attr("checked","checked");
			}
		 })
		total();
 })()
 
/** 失去焦点是自动计算金额 **/
$("tr td.t4").find("input").blur(function() {
	$(this).parent().each(function(i) {
		t3 = $(this).siblings().eq(3).html(),
		t4 = $(this).find("input").val();
		$(this).siblings().eq(4).find("input").val(Number(t3) * Number(t4));
		total();
	});
});
/* -----判断是否被选中------ */
$("tr td .check").click(function() {
	total();
});

/*自动计算合计   公共函数*/
 function total(){
	var num= 0;
	var money= 0;
	var tot=0;
	 $("tr td .check").each(function(i) {
			if($(this).is(':checked')) {
				tot++;
				$(this).parent().siblings().eq(3).find("input").removeAttr("readonly");
				
				var P_Num = $(this).parent().siblings("td").eq(3).find("input").val();
				var P_Money = $(this).parent().siblings("td").eq(4).find("input").val();
				num += Number(P_Num);
				money += Number(P_Money);
				$("#znum").html(num);
				$("#zmoney").html(money);
			}else {
				$(this).parent().siblings().eq(3).find("input").attr("readonly", "readonly");
				$(this).parent().siblings().eq(3).find("input").val("");
				$(this).parent().siblings().eq(4).find("input").val("");
			}

		});
	 if(tot==0){
		$("#znum").html("");
		$("#zmoney").html("");
	 }
}


/* 提交 */
$("#complete").click(function () {
	var num=0;
	var money=0;
	
   $("tr td .check").each(function(i){
	   if($(this).is(':checked')){
		    var P_Id=$(this).val();
		    var P_Num=$(this).parent().siblings("td").eq(3).find("input").val();
		    var P_Money=$(this).parent().siblings("td").eq(4).find("input").val();
		   
		    num+=Number(P_Num);
		    money+=Number(P_Money);
		    
		    $("#selectForm").append(
		    		"<input type='hidden' name='itemsList["+i+"].P_Id' value='"+P_Id+"'>"+
 					"<input type='hidden' name='itemsList["+i+"].P_Num' value='"+P_Num+"'>"+
 					"<input type='hidden' name='itemsList["+i+"].P_Money' value='"+P_Money+"'>"		
		    );
       }
   });
   /* 判断数量不能为空 */
   if(num==0){
	   layui.use('layer',function(){
		   var layer=layui.layer;
		   layer.msg("请填写数量");
	   })
	   return;
   }

   var P_Package=$("#P_Package").val();
   if(P_Package==""){alert("套餐名不能为空");return;}
   var P_Package_id=$("#P_Package_id").val();
   var P_Remark=$("#P_Remark").val();//P_Package_id,	P_Package,	P_ZNum,	P_ZMoney,	P_Remark;
   $("#selectForm").append(
		    "<input type='hidden' name='P_Package_id' value='"+P_Package_id+"'>"+
			"<input type='hidden' name='P_Package' value='"+P_Package+"'>"+
			"<input type='hidden' name='P_ZNum' value='"+num+"'>"+
			"<input type='hidden' name='P_ZMoney' value='"+money+"'>"+
			"<input type='hidden' name='P_Remark' value='"+P_Remark+"'>"
		);
    $("#selectForm").submit();  
});

window.parent.document.getElementById("index").rows="*,0";
</script>
</body>
</html>