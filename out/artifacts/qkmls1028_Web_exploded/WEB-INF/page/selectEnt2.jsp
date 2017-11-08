<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>73</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/css/token-input.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>



<script type="text/javascript">
$(document).ready(function(){
	 
	
	
});
function delRow(obj){ //参数为A标签对象
    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标
    tb.deleteRow(rowIndex); //删除当前行
}




function tanc(id,name,phone,CarNumber,shippington){
	
	
	
	var index = window.parent.getlay();
	window.parent.document.getElementById("DriverID").value=id;
	window.parent.document.getElementById("DriverName").value=name;
	window.parent.document.getElementById("DriverTel").value=phone;
	window.parent.document.getElementById("CarNumber").value=CarNumber;
	window.parent.document.getElementById("shippington").value=shippington;
	parent.layer.close(index);

}



</script>
</head>

<body>




	<table class="tablelist">
		<thead>
			<tr>

				<th>账户</th>
				<th>昵称</th>
				<th>用户类型</th>
				<th>状态</th>
				<th width="10%">选择</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${infoList}" var="info" varStatus="status">
				<tr>
					<td>${info.userName}</td>
					<td onclick="dawo()">${info.nickName}</td>
					<td>${info.userType == "1"?"驾驶员":(info.userType == "0"?"管理员":"统计员")}</td>
					<td>${info.status}</td>
					<td><img
						onclick="tanc('${info.userId}','${info.nickName}','${info.phone}','${info.CID}','${info.shippington}')"
						src="${pageContext.request.contextPath}/images/t01.png" /></td>
				</tr>
			</c:forEach>


		</tbody>
	</table>






</body>

</html>
