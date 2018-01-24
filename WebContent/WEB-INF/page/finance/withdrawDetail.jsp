<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
       <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script src="layui/layui.all.js"></script>
    <title>
    <c:choose>
    <c:when test="${WithdrawList[0].FStatus==0}"> 提现审核</c:when>
    <c:when test="${WithdrawList[0].FStatus==1}">提现转账</c:when>
    <c:when test="${WithdrawList[0].FStatus==2}">提现详情</c:when>
    <c:when test="${WithdrawList[0].FStatus==3}">提现已驳回</c:when>
   <c:otherwise>看不懂了，请找程序员小姐姐  </c:otherwise>
   </c:choose>
    </title>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">财务管理</a></li>
			<li><a href="#">操作</a></li>
		</ul>
	</div>
    <div class="layui-container">
    <h3 style="text-align: center; font-size: 22px; padding: 20px 0; ">    <c:choose>
    <c:when test="${WithdrawList[0].FStatus==0}"> 会员提现审核</c:when>
    <c:when test="${WithdrawList[0].FStatus==1}">会员提现转账</c:when>
    <c:when test="${WithdrawList[0].FStatus==2}">会员提现详情</c:when>
    <c:when test="${WithdrawList[0].FStatus==3}">提现已驳回</c:when>
   <c:otherwise>看不懂了，请找程序员小姐姐  </c:otherwise>
   </c:choose></h3>
    	<form  action="${pageContext.request.contextPath}/Manager/updateWithdrawStatus.do"  method="post"  class="layui-form" autocomplete="on">
    		<div class="layui-form-item">
    			<div class="layui-inline">
				    <label class="layui-form-label">会员姓名:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="1"class="layui-input" readonly="readonly" value="${WithdrawList[0].name }">
				    </div>
			    </div>
			    <div class="layui-inline">
				    <label class="layui-form-label">会员ID:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FUser_Id" class="layui-input" readonly="readonly"  value="${WithdrawList[0].FUser_Id }">
				    </div>
			    </div>
    	
    			<div class="layui-inline">
				    <label class="layui-form-label">银行账号:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="3" class="layui-input" readonly="readonly"  value="${WithdrawList[0].FBankaccount }">
				    </div>
			    </div>
			    <div class="layui-inline">
				    <label class="layui-form-label">提现金额:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FJe" class="layui-input" readonly="readonly"    value="${WithdrawList[0].FJe }">
				    </div>
			    </div>
    			
    			<div class="layui-inline">
				    <label class="layui-form-label">开户行:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FBankdeposit" class="layui-input" readonly="readonly"  value="${WithdrawList[0].FBankdeposit }">
				    </div>
			    </div>
    			<div class="layui-inline">
				    <label class="layui-form-label">账号姓名:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FName" class="layui-input" readonly="readonly"  value="${WithdrawList[0].FName }">
				    </div>
			    </div>
			    <c:choose>
                <c:when test="${WithdrawList[0].FStatus==1||WithdrawList[0].FStatus==2}"> 
    			<div class="layui-inline">
				    <label class="layui-form-label">流水号:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FRunnumber" class="layui-input"   required  value="${WithdrawList[0]. FRunnumber }">
				    </div>
			    </div>
			    <div class="layui-inline">
				    <label class="layui-form-label">手续费:</label>
				    <div class="layui-input-inline">
				        <input type="tel" name="FSxf" class="layui-input"   required  value="${WithdrawList[0]. FSxf }">
				    </div>
			    </div>
			    </c:when>
   </c:choose>
    		</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
	<c:choose>
    <c:when test="${WithdrawList[0].FStatus==0}"> 
    <input type="hidden" name="FId" value="${WithdrawList[0]. FId }">
    <input type="hidden" name="FStatus" value="1">
   <input type="submit" class="layui-btn" value="通过">
	<input type="button" id="reject" class="layui-btn" value="驳回">
  </c:when>
    <c:when test="${WithdrawList[0].FStatus==1}">
    <input type="hidden" name="FId" value="${WithdrawList[0]. FId }">
    <input type="hidden" name="FStatus" value="2">
   <input type="submit" class="layui-btn" value="转账">
   </c:when>
    <c:when test="${WithdrawList[0].FStatus==2}"></c:when>
    <c:when test="${WithdrawList[0].FStatus==3}"></c:when>
   <c:otherwise>看不懂了，请找程序员小姐姐  </c:otherwise>
   </c:choose>
				      
			    </div>
			</div>
    </form>	
    </div>
       <script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
    <script type="text/javascript">
    	layui.use('layer',function(){
    		var layer=layui.layer;
    		$("#reject").click(function(){
				  layer.prompt({
				  	title: '驳回理由', 
				  	formType: 2,
				  	shadeClose:true
				  }, 
				  function(text, index){
				    layer.close(index);
				    $.ajax({
				    	type:"post",
				    	url:"${pageContext.request.contextPath}/Manager/ajaxUpdateRefuseReason.do",
				    	data:{
				    		RefuseReson:text,
				    		FId:'${WithdrawList[0]. FId }'
				    		},
				    		dataType:'json',
				    	success:function(data){
				    		console.log(data);
				    		data=JSON.parse(data);
				    		if(data==0){
				    			layer.msg("驳回成功");
				    			setTimeout(function(){
					    			window.location.href='${pageContext.request.contextPath}/Manager/queryWithdrawList.do';
				    			},500)
				    		}
				    	}
				    });
				});
    		})
    	});
    	window.parent.document.getElementById("index").rows="*,0";
    </script>
</body>
</html>
