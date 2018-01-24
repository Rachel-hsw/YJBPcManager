<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
    <title>代理审核详情</title>
    <style type="text/css">
    	#img{
    		width:800px;
    		height:400px;
    		overflow: auto;
    	}
    	.layui-form-label{
    		width:90px;
    	}
    	.layui-inline{
    		margin-bottom: 15px;
    	}
    	.layui-input-block {
    text-align: center;
}
.place{
			overflow:hidden;
		}
		.place a{
			color:#dc4e00;
		}
		.xt{
		width:150px !important;
		float:left;
		}
		.fj{
		width:310px !important;
		overflow:hidden;
		}
		
    </style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">财务管理</a></li>
			<li><a href="#">审核列表</a></li>
			<li><a href="#"> 审核详情</a></li>
		</ul>
	</div>
<div class="layui-container">
		<div class="formtitle">
				<span>审核代理信息</span>
			</div>
	<!--=====代理提交内容 atart=====-->
	<form action="${pageContext.request.contextPath }/customer/updateExamineInfo.do" method="post" class="layui-form">
	   <input name="currentPage" type="hidden"
					class="dfinput" value="${vo.currentPage}" />
					<input name="queryFLevel" type="hidden"
					class="dfinput" value="${vo.queryFLevel}" />
					<input name="queryFStatus" type="hidden"
					class="dfinput" value="${vo.queryFStatus}" />
					<input name="FStatus" type="hidden"
					class="dfinput" value="1" />
					<input name="FExamine_id" type="hidden"
					class="dfinput" value="${info.FExamine_id }" />
		<div class="layui-inline">
	      	<label class="layui-form-label">用户名：</label>
	      	<div class="layui-input-inline">
	        	<input type="text" name="1" class="layui-input"  value="${info.username }" readonly>
	      	</div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">代理级别：</label>
		    <div class="layui-input-inline fj">
		 
		    	<input type="text" name="2" class="layui-input xt"  
	        value="  ${info.FLevel==1?'省级':(info.FLevel==2?'市级':'县')}"    readonly>
	  <input type="text"  name="2" class="layui-input xt" value="${info.PCityname}"   readonly> 
		    	 <input type="hidden"  name="FLevel" value="${info.FLevel}" readonly> 
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">开户行：</label>
		    <div class="layui-input-inline">
		    	<input type="text" name="3" class="layui-input"  value="${info.FOpen_Account}" readonly>
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">账号：</label>
		    <div class="layui-input-inline">
		    	<input type="text" name="4" class="layui-input" value="${info.FCart_Number}"   readonly>
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">金额：</label>
		    <div class="layui-input-inline">
		    	<input type="text" name="5" class="layui-input" value="${info.FMoney}" readonly>
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">交易流水号：</label>
		    <div class="layui-input-inline">
		    	<input type="text" name="6" class="layui-input" value="${info.FBatch}"  readonly>
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">转账凭证：</label>
		    <div class="layui-input-inline" id="img">
		    	<img src="${info.FPic}"/>
		    </div>
	    </div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      	<input type="submit" class="layui-btn" value="审核通过">
		      	<input type="button" class="layui-btn" id="notAgent" value="审核不通过">
		      	<a href="javascript:window.history.back();" class="layui-btn">返回</a>
		    </div>
	  	</div>
	</form>
	<!--=====代理提交内容 end=====-->
</div>
<!-- ===不通过信息返回==== -->
<form method="POST" action="${pageContext.request.contextPath }/customer/updateExamineInfo.do"  id="form" style="display:none;"></form>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
   <script type="text/javascript">
   	layui.use(['layer'],function(){
   		var layer=layui.layer;
   		$("#notAgent").click(function(){
   			layer.prompt({title: '请输入不通过的理由，并确认', formType: 2}, function(text, index){
			    layer.close(index);
			    layer.msg('您的理由是：'+ text);
			    // 审核不通过的信息返回
			    $("#form").append(
			    	"<input type='text' name='FRemark' value='"+text+"'>"+
			    	"<input type='text' name='FExamine_id' value='${info.FExamine_id}'>"+
			    	"<input type='text' name='FStatus' value='2'>"		
			    )
			  	$("#form").submit();
			});
   		})
   	})
   	window.parent.document.getElementById("index").rows="*,0";
   </script>
</body>
</html>