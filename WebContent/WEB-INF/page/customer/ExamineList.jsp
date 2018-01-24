<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
	<link href="${pageContext.request.contextPath}/layui/css/layui.css" rel="stylesheet"
	type="text/css" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
            window.location.href="${pageContext.request.contextPath}/customer/examineList.do?currentPage="+obj.value+"&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus}";
        }
    }
   // window.parent.document.getElementById("index").rows="*,0";
    </script>
	<title>代理审核列表</title>
	<style>
		.place{
			overflow:hidden;
		}
		.place a{
			color:#dc4e00;
		}
		.layui-form-label{
			width:110px;
		}
		.layui-form{
			padding-top:20px;
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
		</ul>
	</div>
	<!-- search start -->
	<form class="layui-form">
		<div class="layui-form-item">
		<!-- ========= -->
		    <div class="layui-inline">
		      <label class="layui-form-label">代理级别:</label>
		      <div class="layui-input-inline">
		        <select name="queryFLevel">
		          	<option value=""<c:if test="${vo.queryFLevel=='' }">
          						 selected='selected'
        						 </c:if>>全部</option>
							<option value="1"<c:if test="${vo.queryFLevel=='1' }">
          						 selected='selected'
        						 </c:if>>省级</option>
							<option value="2"<c:if test="${vo.queryFLevel=='2' }">
          						 selected='selected'
        						 </c:if>>市级</option>
							<option value="3"<c:if test="${vo.queryFLevel=='3' }">
          						 selected='selected'
        						 </c:if>>县级</option>
		        </select>
		      </div>
		    </div>
		<!-- ========= -->
		    <div class="layui-inline">
		      <label class="layui-form-label">状态:</label>
		      <div class="layui-input-inline">
		        	<select name="queryFStatus" lay-verify="required" lay-search="">
				         <option value=""<c:if test="${vo.queryFStatus=='' }">
          						 selected='selected'
        						 </c:if>>全部</option>
							<option value="0"<c:if test="${vo.queryFStatus=='0' }">
          						 selected='selected'
        						 </c:if>>待审核</option>
							<option value="1"<c:if test="${vo.queryFStatus=='1' }">
          						 selected='selected'
        						 </c:if>>已通过</option>
        						 <option value="2"<c:if test="${vo.queryFStatus=='2' }">
          						 selected='selected'
        						 </c:if>>未通过</option>
		          	</select>
		      </div>
		    </div>
		<!-- ========= -->
		    <div class="layui-inline">
			      <input type="submit" class="layui-btn" value="查询">
			  </div>
		  </div>
	</form>
	
		<div class="formbody">
<div class="container">
	
	<div class="table-responsive">
		<table class="table table-bordered table-hover table-striped ">
			<thead>
				<tr class="success">
					<th style="width:50px;">序号</th>
					<th>用户名</th>
					<th>代理级别</th>
					<th>状态</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				  <c:forEach items="${requestScope.pageBean.queryNumberList}" var="item" varStatus="s">
		                 <tr>
					    <td>${s.index + 1}</td>
					<td>${item.username}</td>
						<td>${item.FLevel==1?'省级':(item.FLevel==2?'市级':'县')}</td>
							<td>${item.FStatus=='0'?'待审核':(item.FStatus=='1'?'已通过':'未通过')}</td>
							<td>${item.FCreatedate.substring(0,19)}</td>
					<td>
					<c:if test="${vo.queryFStatus=='0' }">
          					
        						 
						<a
						href='<st:url value="/customer/queryExamineInfo/${item.FExamine_id}.do?currentPage=${pageBean.currentPage }&queryFLevel=${vo.queryFLevel }&queryFStatus=${vo.queryFStatus}"></st:url>'
						class="tablelink">审核</a> 
						</c:if>
				</tr>
			</c:forEach>
			</tbody>
			
		</table>
	</div>
</div>
    </div>
    
    
    
  <!-- 上一页 -->
<!-- 判断当前页是否是第一页 -->
	<div style="margin-top: 20px; text-align:center;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
	  <li>    
	         <a>共${pageBean.totalCount }条记录</a>
     <a>共${pageBean.totalPage} 页</a>
 <c:if test="${pageBean.currentPage!=1&&not empty pageBean.totalCount }">
<a href="${pageContext.request.contextPath}/customer/examineList.do?currentPage=1&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus} ">首页</a>
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
					<a href="${pageContext.request.contextPath}/customer/examineList.do?currentPage=${pageBean.currentPage-1}&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus}"  aria-label="Previous">
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
     <li ><a href="${pageContext.request.contextPath}/customer/examineList.do?currentPage=${i}&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus}">${i}</a></li>
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
					<a href="${pageContext.request.contextPath}/customer/examineList.do?currentPage=${pageBean.currentPage+1}&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus}" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
       </c:if>	
 <c:if test="${pageBean.currentPage!=pageBean.totalPage&&not empty pageBean.totalPage }"> 
<li><a href="${pageContext.request.contextPath}/customer/examineList.do?currentPage=${pageBean.currentPage }&queryFLevel=${vo.queryFLevel}&queryFStatus=${vo.queryFStatus} ">尾页</a></li>
</c:if>
 <li> <a>跳到<input type="text"
value="${pageBean.currentPage }" style="width: 50px; height:17px; outline:none; " onchange="changePage(this)"/>页 
  </a></li>
   <li><a href="javascript:void(0);"style="">跳转</a></li>
   </ul>


</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
   
      
</body>
</html>