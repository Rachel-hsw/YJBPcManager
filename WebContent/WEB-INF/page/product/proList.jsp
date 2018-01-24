<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>86</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
});

window.parent.document.getElementById("index").rows="*,0";

function dawo(){
	var index = layer.open({
	    type: 2,
	    content: '',
	    area: ['300px', '195px'],
	    maxmin: true
	});
	layer.full(index);
}

</script>

<style>
	tr th{cursor:pointer;}
	td.miaoshu{
		overflow : hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 3;
		-webkit-box-orient: vertical;
	    line-height: 20px;
	    text-align: left;
	    text-indent:20px;
	}
</style>
</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">档案信息</a></li>
			<li><a href="#">产品管理</a></li>
		</ul>
	</div>

	<br />
	<form
		action=' <st:url value="/Manager/prodList.do"></st:url>'
		method="post">
		<div class="tools">
			<ul class="seachform1">
				<li><label>名称：</label><input id="uName" name=queryname value="${vo.queryname}"
					class="scinput1" type="text" /></li>
				<li><input id="searchs" name="" class="scbtn" value="查询"
					type="submit" /></li>
			</ul>
		</div>
	</form>
	<div class="tools">
			<ul class="toolbar">
				<li>
				<a href="${pageContext.request.contextPath }/Manager/queryProdInfo.do?method=add">
					<span><img src="${pageContext.request.contextPath }/images/t01.png" /></span>添加
					</a>
				</li>
			</ul>
		</div>
	<table class="tablelist">
		<thead>
			<tr>
			<th width="3%">序号<i class="sort"><img
						src="${pageContext.request.contextPath}/images/px.gif" /></i></th>
					<th>产品图片</th>
				<th>商品名称</th>
				<th>价格</th>
				<th>种类</th>
				<th>单位</th>
				<th>重量</th>
				<th>产品描述</th>
				<th>状态</th>
				<th width="5%">分类</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		
		  <c:forEach items="${requestScope.list}" var="item" varStatus="s">
		                 <tr>
					    <td>${s.index + 1}</td>
				<td width="20%">
            <img src="../${item.imgurl}"  width="20%" style="cursor:pointer;"/></a></td>
    <%--       <img src="http://gsdy.eicp.net:9091/${item.imgurl}"  width="20%" style="cursor:pointer;"/></a></td>  --%>
					<td>${item.name}</td>
					<td>${item.price}</td>
						<td><c:if test="${item.category=='1' }">
          						 不老传说二代修复、保养类
        						 </c:if>
        						 <c:if test="${item.category=='2' }">
          					不老红妍第三代修复、保养类
        						 </c:if>
        						 <c:if test="${item.category=='3' }">
          						强化修复类
        						 </c:if>
        						 <c:if test="${item.category=='4' }">
          					   药液、修复类
        						 </c:if>
        						 </td>
						<td>${item.f_Unit}</td>
						<td>${item.f_Zl}</td>
						<td class="miaoshu">${item.description}</td>
						<td>${item.FStatus==0?'下架':'上架'}</td>
						<td>${item.divName}</td>
					<td>
						
					<a
						href='<st:url value="/Manager/delProd.do?id=${item.id}&FStatus=${item.FStatus}&queryname=${vo.queryname }&querystatus=${vo.querystatus}"></st:url>'
						class="tablelink">${item.FStatus==0?'上架':'下架'}</a>
					
						<a
						href='<st:url value="/Manager/queryProdInfo.do?Id=${item.id}&method=update&queryname=${vo.queryname }&querystatus=${vo.querystatus}"></st:url>'
						class="tablelink">修改</a> 
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


 



	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	$(".telsplit").each(function(){
		if($(this).html() != ""){
			var text=$(this).html().trim();
			var txt=text.substring(0,3)+"-"+text.substring(3,7)+"-"+text.substring(7,11);
			$(this).html(txt);
		};
	})
	
		
	</script>

</body>

</html>