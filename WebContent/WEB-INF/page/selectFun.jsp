<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>74</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/css/token-input.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	 
	$("#navigationIds").click(function() {
	      $('input[name="navigationId"]').attr("checked",this.checked); 
	  });
	
});
function delRow(obj){ //参数为A标签对象
    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标
    tb.deleteRow(rowIndex); //删除当前行
}




function tanc(){
	var userId = document.getElementsByName("navigationId");
	var userName = document.getElementsByName("navigationText");
	var os = "";
	var osName = "";
	for(i=0;i<userId.length;i++){
		if(userId[i].checked){
			os += userId[i].value + ",";
			osName += userName[i].value + ",";
		}
	}
	if(os == ""){
		alert("至少且只能选一个");
		return;
	}
	
	
	if(os.length > 1) {
		os = os.substring(0,os.length-1);
		osName = osName.substring(0,osName.length-1);
	}
	
	var index = window.parent.getlay();
	window.parent.document.getElementById("Power").value=os;
	window.parent.document.getElementById("PowerNames").value=osName;
	parent.layer.close(index);

}

</script>
</head>

<body>




	<table class="tablelist">
		<thead>
			<tr>
				<th><input id="navigationIds" name="" type="checkbox" value="" /></th>
				<th width="15%">菜单名称</th>
				<th width="15%">parent</th>
				<th>链接地址</th>
				<th width="10%">手机端</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${infoList}" var="info" varStatus="status">
				<tr>
					<td><input id="navigationId" name=navigationId type="checkbox"
						value="${info.navigationId}" /> <input name="navigationText"
						type="hidden" value="${info.navigationText}" /></td>
					<td>${info.navigationText}</td>
					<td>${info.parentNavigationName}</td>
					<td style="text-align:left;">${info.navigationUrl}</td>
					<td>${info.isMoveby==0?"否":"是"}</td>
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
    	var us = document.getElementsByName("navigationId");
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
