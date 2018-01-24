<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"   content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <title>发货页</title>
    <style>
        .form-group{
            margin-top:10px;
        }
        label {
            width: 80px;
            text-align:justify;
            text-justify:distribute-all-lines;
            text-align-last:justify;
        }
        .left{
            margin-left:170px;
        }
        @media screen and (max-width: 1200px) {
            .left {
                margin-left:50px;
            }
        }
        @media screen and (max-width: 995px) {
            .left {
                margin-left:0px;
            }
        }
    </style>
</head>
<body>
<div class="layui-container" id="content">
    <h3 style="text-align: center;">补发货单</h3>
    <form class="form-inline" action="${pageContext.request.contextPath}/Manager/deliveryBymanSubmit.do"  method="POST" enctype="multipart/form-data"  id="form">
        <div class="form-group">
            <label for="inupt3">会员ID:</label>
            <input type="User_id" class="form-control input-sm" id="User_id" name="User_id" value="" placeholder="必填">
        </div>
        <div class="form-group left">
            <label>发货仓库:</label>
          <select name="deliveryBean.FW_ID" value=""
					class="form-control input-sm select1" style="width: 166px;">
					<c:forEach items="${arealist}" var="item">
						<option value="${item.FId}">${item.FName}</option>
					</c:forEach>
				</select>
        </div>
        <div class="form-group left">
            <label>快递公司:</label>
             <select name="deliveryBean.FExpress" value=""
					class="form-control input-sm select2" style="width: 166px;">
					<option>中通</option>
					<option>圆通</option>
					<option>申通</option>
					<option>韵达</option>
					<option>顺丰</option>
				</select>
        </div>
        <div class="form-group">
            <label for="inupt0">订单日期:</label>
            <input type="text" class="form-control input-sm" id="inupt0" name="FS_Date"  placeholder="">
        </div>
       
        <div class="form-group left">
            <label for="inupt5">发货日期:</label>
            <input type="text" class="form-control input-sm" id="inupt5" name="deliveryBean.FDelivery_Time"  placeholder="">
        </div>
        <div class="form-group left">
            <label for="inupt02">快递单号:</label>
            <input type="text" class="form-control input-sm" id="inupt02" name="deliveryBean.FCourier_Number"    value="" placeholder="必填">
        </div>
        <div class="form-group ">
            <label for="Fje">金额:</label>
            <input type="text" class="form-control input-sm" id="Fje" name="Fje" value="" placeholder="必填">
        </div>
        <!---->
        <div class="form-group left">
            <label for="inupt9">发货人:</label>
            <input type="text" class="form-control input-sm" id="inupt9" name="deliveryBean.FConsignor" value="" placeholder="">
        </div>
        <div class="form-group left">
            <label for="inupt33">制单人:</label>
            <input type="text" class="form-control input-sm" id="inupt33" name="FJsr" value="" placeholder="">
        </div>    <div style="display:flex;">
        <div class="form-group">
            <label for="inupt56">发货单:</label>
            				<a href="javascript:;" class="btn btn-success" style="position:relative; overflow: hidden;     float: right;  margin-bottom: 10px;">
					选择图片
					<input type="file" id="inupt56" name="pictureFile" style="position:absolute; top: 0;left: 0; z-index: 10; opacity: 0; width: 100%; height:100%;"/>
				</a>
            
           <div style="width:500px; text-align: center; overflow: auto;">
           		<div style="border:1px solid green;">
					<img src="" id="imgcheck" alt="请上传发货单图片凭证" width="100%" />
				</div>
			</div>
        </div>
    <div style="text-align: right;width:100%;" class="hidden-print">
        <input type="button" id="btnSubmit" class="btn btn-success" style="margin-left: 60px; margin-top:10px;" value="确认发货">
   		<button type="button" class="btn btn-success" onclick="window.history.go(-1)" style="margin-left: 10px; margin-top:10px;">返回</button>
    </div></div>
</form>
</div>
<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script>
	layui.use(['layer','laydate'],function(){
		var layer=layui.layer,
			laydate=layui.laydate;
		var ifsucesss="${ifsucesss}";
		if (ifsucesss=="true") {
			
			layer.msg("${result}");
			}else if(ifsucesss=="false"){
				layer.msg("${result}");
			}
		
			laydate.render({
				elem:"#inupt0"
			})
			laydate.render({
				elem:"#inupt5"
			})
			// 默认日期
			var data=new Date();
			var year=data.getFullYear(),
					month=data.getMonth()+1,
					da=data.getDate();
			$("#inupt0").val(year+"-"+month+"-"+da);
			$("#inupt5").val(year+"-"+month+"-"+da);
			
		$("#btnSubmit").click(function(){
			var Fje=$("#Fje").val();
			var User_id=$("#User_id").val();
			var inupt56=$("#inupt56").val();
			if (Fje==""||User_id==""||inupt56=="") {
				layer.msg("请完善所有必填选项");
				return;
			}
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/Manager/queryIfEnough.do",
				async:true,
				data:{
					User_id:User_id,
				    Fje:Fje
				},
				success:function(data){
					console.log(data);
					if (data[0]==1) {//判断为1，弹出提醒框
						layer.msg(data[1]);
					}else if (data[0]==2) {//判断为2，弹出确认框
						layer.open( {
					        time:0,
					        btn: ['确认', '取消'],
					        content:data[1],
					        success: function(layero){
					          layero.find('.layui-layer-btn0').click(function(){
					        	  //点击确认进行升级
					          	$("#form").submit();
					          })
					          layero.find('.layui-layer-btn1').click(function(){
					      //点击取消什么操作都不要做
					   
					          })
					        }
					     });	
					}
				
				}
			});
		})
	});
	/***** 图片上传********/
    $("#inupt56").change(function(event){
    	console.log("1");
		var src, $url=window.URL || window.webkitURL || window.mozURL, file = event.target.files;
		if($url){
			src = $url.createObjectURL(file[0]);
		}else{
			src = event.target.result;
		}
		$("#imgcheck").attr("src",src);
	})
	window.parent.document.getElementById("index").rows="*,0";
</script>
</body>
</html>