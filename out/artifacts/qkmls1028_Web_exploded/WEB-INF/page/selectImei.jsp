<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>76</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
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

function queding(){
	var str = "";
	 $("[name='ImeiId']:checkbox:checked").each(function(){
			str+=$(this).val()+",";
		});
	 alert("")
}

function tanc(){
	var ImeiId = document.getElementsByName("ImeiId");
	var imei = document.getElementsByName("imei");
	var os = "";
	var osName = "";
	for(i=0;i<ImeiId.length;i++){
		if(ImeiId[i].checked){
			os += ImeiId[i].value + ",";
			osName += imei[i].value + ",";
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
	window.parent.document.getElementById("ImeiId").value=","+os+",";
	window.parent.document.getElementById("imei").value=osName;
	parent.layer.close(index);

}

</script>


</head>


<body>




	<table class="tablelist">
		<thead>
			<tr>
				<th width="3%">序号<i class="sort"><img
						src="${pageContext.request.contextPath}/images/px.gif" /></i></th>
							<th>手机号码</th>
				<th width="8%">手机IMEI码</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selInfo}" var="info" varStatus="status">
				<tr>
					<td><input name="ImeiId" type="checkbox" onclick="xuanzhong()"
						value="${info.ID}" /> <input name="imei" type="hidden"
						value="${info.IMEI}" /></td>
					<td width="5%" class="telsplit">${info.phone}</td>
					<td>${info.IMEI}</td>
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
	$(".telsplit").each(function(){
		if($(this).html() != ""){
			var text=$(this).html();
			var txt=text.substring(0,3)+"-"+text.substring(3,7)+"-"+text.substring(7,11);
			$(this).html(txt);
		};
	})
	</script>
</body>
</html>