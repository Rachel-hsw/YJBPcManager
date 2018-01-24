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
    function changePage(obj){
        if((!/^[0-9]\d*$/.test(obj.value))){
            alert("必须是正整数!");
            obj.value=${pageBean.currentPage}
            return;
        }else if(obj.value<=0 || (obj.value>(${pageBean.totalPage})) ){
            alert("页码必须在有效范围内!");
            obj.value=${pageBean.currentPage}
            return;
        }else{
            window.location.href="{pageContext.request.contextPath}/customer/customerList.do?currentPage="+obj.value+"&username=${vo.username }";
        }
    }
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
function isDel(userid,FStatus){
	//询问框
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	},function(){
		window.location.href = "";
	});
}
</script>

<style>tr th{cursor:pointer;}</style>
</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">档案信息</a></li>
			<li><a href="#">用户管理</a></li>
		</ul>
	</div>

	<br />
	<form
		action=' <st:url value="/customer/customerList.do"></st:url>'
		method="post">
		<div class="tools">
			<ul class="seachform1">
				<li><label>名称：</label><input id="uName" name="username" value="${vo.username }"
					class="scinput1" type="text" /></li>
				<li><input id="searchs" name="" class="scbtn" value="查询"
					type="submit" /></li>
			</ul>
		</div>
	</form>
	<table class="tablelist">
		<thead>
			<tr>
				<th width="3%">用户ID<i class="sort"><img
						src="${pageContext.request.contextPath}/images/px.gif" /></i></th>
						<th>推荐ID</th>
					<th>账号</th>
				<th dataType="roomType">姓名</th>
				<th>手机号</th>
				<th>身份证号</th>
				<th>邮箱</th>
				<th>类型</th>
				<th>状态</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		
		      <c:forEach items="${requestScope.pageBean.queryNumberList}" var="item" varStatus="s">
		                 <tr>
					    <td>${item.userid}</td>
					    <td>${item.recommendId}</td>
					<td>${item.username}</td>
					<td>${item.name}</td>
						<td >${item.tel}</td>
						<td>${item.iccard}</td>
						<td>${item.email}</td>
						
						  <c:choose>
                     <c:when test="${item.userlb=='1'}"><td>管理员</td></c:when>
                      <c:when test="${item.userlb=='2'}"><td>采购</td></c:when>
                       <c:when test="${item.userlb=='3'}"><td>会员</td></c:when>
						</c:choose>
						
							<td>${item.FStatus==0?'有效':'无效'}</td>
					<td>
						<a
						href='<st:url value="/customer/delUser.do?deluserid=${item.userid}&status=${item.FStatus}&currentPage=${pageBean.currentPage}&username=${vo.username}"></st:url>'
						class="tablelink">${item.FStatus==0?'无效':'有效'}</a>
						<a
						href='<st:url value="/customer/queryUserInfo/${item.userid}.do?currentPage=${pageBean.currentPage }&username=${vo.username }"></st:url>'
						class="tablelink">修改</a> <a
						href='<st:url value="/customer/showUpdatePW/${item.userid}.do?currentPage=${pageBean.currentPage }&username=${vo.username }"></st:url>'
						class="tablelink">重置密码</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


  <!-- 上一页 -->
<!-- 判断当前页是否是第一页 -->
	<div style="margin-top: 20px; text-align:center;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
	  <li>    
	         <a>共${pageBean.totalCount }条记录</a>
     <a>共${pageBean.totalPage} 页</a>
 <c:if test="${pageBean.currentPage!=1&&not empty pageBean.totalCount }">
<a href="${pageContext.request.contextPath}/customer/customerList.do?currentPage=1&username=${vo.username } ">首页</a>
	</c:if>
<%-- 	<c:if test="${pageBean.currentPage==1|| empty pageBean.totalCount }"> 
	 <a href="javascript:void(0);">首页</a>
	 </c:if> --%>	
		</li>
       <c:if test="${pageBean.currentPage==1|| empty pageBean.totalCount}">
       <li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
       </c:if>
         <c:if test="${pageBean.currentPage!=1 &&not empty pageBean.totalCount}">
       <li >
					<a href="${pageContext.request.contextPath}/customer/customerList.do?currentPage=${pageBean.currentPage-1}&username=${vo.username }"  aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
       </c:if>
     <c:if test="${pageBean.totalPage<=5}">
  		<c:set var="begin" value="1" scope="page"></c:set>
  		<c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
  	</c:if>
  	<c:if test="${pageBean.totalPage>5}">
		<c:choose>
			<c:when test="${pageBean.currentPage<=3}">
				<c:set var="begin" value="1" scope="page"></c:set>
  				<c:set var="end" value="5" scope="page"></c:set>
			</c:when>
			<c:when test="${pageBean.currentPage>=pageBean.totalPage-2}">
				<c:set var="begin" value="${pageBean.totalPage-4}" scope="page"></c:set>
  				<c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
  			</c:when>
  			<c:otherwise>
  				<c:set var="begin" value="${pageBean.currentPage-2}" scope="page"></c:set>
  				<c:set var="end" value="${pageBean.currentPage+2}" scope="page"></c:set>
  			</c:otherwise>
		</c:choose>
  	</c:if>
  	
  	<c:forEach begin="${begin}" end="${end}" step="1" var="i">
  		<c:if test="${i == pageBean.currentPage}">
  		 <li class="active"><a href="javascript:void(0);">${i}</a></li>
  		</c:if>
  		<c:if test="${i != pageBean.currentPage&&not empty pageBean.totalCount&& pageBean.totalCount!=0}">
     <li ><a href="${pageContext.request.contextPath}/customer/customerList.do?currentPage=${i}&username=${vo.username }">${i}</a></li>
  		</c:if>
  	</c:forEach>  
       
 
   <!-- 判断当前页是否是最后一页 -->
   <c:if test="${pageBean.currentPage==pageBean.totalPage }">
   <li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				
	</c:if>	
	    <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
				
				<li>
					<a href="${pageContext.request.contextPath}/customer/customerList.do?currentPage=${pageBean.currentPage+1}&username=${vo.username }" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
       </c:if>	
 <c:if test="${pageBean.currentPage!=pageBean.totalPage&&not empty pageBean.totalPage }"> 
<li><a href="${pageContext.request.contextPath}/customer/customerList.do?currentPage=${pageBean.currentPage }&username=${vo.username } ">尾页</a></li>
</c:if>
  <li> <a>跳到<input type="text"
value="${pageBean.currentPage }" style="width: 50px; height:17px; outline:none; " onchange="changePage(this)"/>页 
  </a></li>
   <li><a href="javascript:void(0);"style="">跳转</a></li>
   </ul>


</div>



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