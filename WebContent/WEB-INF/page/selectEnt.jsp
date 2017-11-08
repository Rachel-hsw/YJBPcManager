<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>72</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/css/token-input.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>


<script type="text/javascript">

	 
	$(document).ready(function(){
		 
		$("#userIds").click(function() {
		      $('input[name="userId"]').attr("checked",this.checked); 
		  });
		
	});
	

function delRow(obj){ //参数为A标签对象
    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标
    tb.deleteRow(rowIndex); //删除当前行
}

function tanc(){
	var userId = document.getElementsByName("userId");
	var userName = document.getElementsByName("userName");
	var os = "";
	var osName = "";
	for(i=0;i<userId.length;i++){
		if(userId[i].checked){
			os += userId[i].value + ",";
			osName += userName[i].value + ",";
		}
	}
	if(os == ""){
		alert("至少选一个");
		return;
	}
	
	
	if(os.length > 1) {
		os = os.substring(0,os.length-1);
		osName = osName.substring(0,osName.length-1);
	}
	
	var index = window.parent.getlay();
	window.parent.document.getElementById("users").value=","+os+",";
	window.parent.document.getElementById("userNames").value=osName;
	parent.layer.close(index);

}
</script>
</head>

<body>

	<table class="tablelist">
		<thead>
			<tr>
				<th><input id="userIds" name="" type="checkbox" value="" /></th>
				<th>账户</th>
				<th>昵称</th>
				<th>用户类型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${infoList}" var="info" varStatus="status">
				<tr>
					<td><input name="userId" type="checkbox"
						value="${info.userid}" /> <input name="userName" type="hidden"
						value="${info.username}" /></td>
					<td>${info.username}</td>
					<td onclick="dawo()">${info.name}</td>
					<td>${info.userlb == "1"?"管理员":(info.userlb == "2"?"采购员":"会员")}</td>
								</tr>
			</c:forEach>


		</tbody>
	</table>

	<div class="tools">
		<ul class="toolbar">
			<li class="click"><span><img onclick="tanc()"
					src="${pageContext.request.contextPath}/images/t01.png" /></span>确定</li>

		</ul>
	</div>
	<script type="text/javascript">
    var relIds = '${relIds}';

    if(relIds != "null"&&relIds!=""){
    	var us = document.getElementsByName("userId");
    	//alert(us);
    	//alert(us.length);
    	for(i=0;i<us.length;i++){
    		//alert(us[i].value);
    		if(relIds.indexOf(us[i].value)>-1){
    			us[i].checked = true;
    		}
    	
    	}
    }
    
    </script>

</body>

</html>
