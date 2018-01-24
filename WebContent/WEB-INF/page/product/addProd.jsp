<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>82</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

</head>
<style>
#acheck:hover{
color:#fff !important
}
</style>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">档案信息</a></li>
			<li><a href="#">产品管理</a></li>
			<li><a href="#">修改产品</a></li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/Manager/OperationProdInfo.do" method="POST" enctype="multipart/form-data" >
<input name="method" type="hidden"  value="${method}" />
<input name="id" type="hidden"  value="${item.id}" />
<input name="queryname" type="hidden"  value="${vo.queryname}" />
<input name="querystatus" type="hidden"  value="${vo.querystatus}" />
<div class="formbody">
			<div class="formtitle">
				<span>产品信息</span>
			</div>
			<ul class="forminfo" style="overflow:hidden;">
			
				<li><label>商品名称：</label><input name="name" type="text"
					class="dfinput" value="${item.name}" /><i></i></li>
					<li><label>商品种类：</label>
			
     <select name="category" class="dfinput">
      <option value="1"<c:if test="${item.category=='1' }">
          						 selected='selected'
        						 </c:if>>不老传说二代修复、保养类</option>
      <option value="2"<c:if test="${item.category=='2' }">
          						 selected='selected'
        						 </c:if>>不老红妍第三代修复、保养类</option>
     <option value="3"<c:if test="${item.category=='3' }">
          						 selected='selected'
        						 </c:if>>强化修复类</option>
      <option value="4"<c:if test="${item.category=='4' }">
          						 selected='selected'
        						 </c:if>>药液、修复类</option>
        
</select>
					<i></i></li>
					<li><label>价格：</label><input  name="price" type="number"
					class="dfinput" value="${item.price}" /><i></i></li>
					<li><label>单位：</label>
					     <select name="F_Unit" class="dfinput">
     		 			<option value="g"<c:if test="${item.f_Unit=='g'||item.f_Unit!='ml'}">
          						 selected='selected'
        						 </c:if>>g</option>
    		  <option value="ml"<c:if test="${item.f_Unit=='ml'}">
          						 selected='selected'
        						 </c:if>>ml</option>
        
						</select>
					
						<li><label>重量：</label><input name="F_Zl" type="number"
					class="dfinput" value="${item.f_Zl}" />	<li><label>分类：</label>
					     <select name="divName" class="dfinput">
     		 			<option value="护肤型"<c:if test="${item.divID=='H'||item.divID!='J'}">
          						 selected='selected'
        						 </c:if>>护肤型</option>
    		  <option value="技能型"<c:if test="${item.divID=='J'}">
          						 selected='selected'
        						 </c:if>>技能型</option>
        
						</select>
					</i><i></i></li>
				
					<li><label>描述信息：</label>
						<textarea name="description"  style="width:350px;border:1px solid #ccc" rows="8" cols="50"  >${item.description }</textarea><i></i></li>
				<li><label>商品图片：</label>
				<input name="imgurl" type="hidden"  value="${item.imgurl}" />
				
					
					
					<!-- <input type="file"  name="pictureFile"/>  -->
					<div style="width:200px;height:220px; overflow: hidden;">
					<div>
					
		<img src="../${item.imgurl}" id="imgcheck" alt="请添加商品图片" width="100%" height="180px" />
<%-- 	<img src="http://gsdy.eicp.net:9091/${item.imgurl}" id="imgcheck" alt="请添加商品图片" width="100%" height="180px" />  --%>
		</div>
		    <a href="javascript:;" style="position:relative; overflow: hidden;padding:5px 10px" class="btn" id="acheck">
			选择图片
			<input type="file"  name="pictureFile" id="choiceimg" style="position:absolute; top: 0;left: 0; z-index: 10; opacity: 0; width: 100%; height:100%;"/>
		</a>
	</div><i></i></li>
						
					<li></li>
					<li style="text-align:right; margin-top:30px;">
							<label>&nbsp;</label>  
							<input style="margin-right:100px;" name=""type="submit" class="btn" value="确认保存" />
						</li>
			</ul>
						
		</div></form>
</div>
<script >
$("#choiceimg").change(function(event){
	var src, $url=window.URL || window.webkitURL || window.mozURL, file = event.target.files;
	if($url){
		src = $url.createObjectURL(file[0]);
	}else{
		src = event.target.result;
	}
	$("#imgcheck").attr("src",src);
	console.log(src);
})
</script>
</body>
</html>