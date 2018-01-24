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

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">档案信息</a></li>
			<li><a href="#">进货源管理</a></li>
			<li><a href="#">修改进货源</a></li>
		</ul>
	</div>

	<form action=' <st:url value="/Manager/OperationSupplierInfo.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>进货源信息</span>
			</div>
			<ul class="forminfo">
			 <input name="currentPage" type="hidden"
					class="dfinput" value="${vo.currentPage}" />
					<input name="name" type="hidden"
					class="dfinput" value="${vo.fname}" />
					<input name="address" type="hidden"
					class="tel" value="${vo.faddress}" />
					<input name="address" type="hidden"
					class="dfinput" value="${vo.ftel}" />
					<input name="status" type="hidden"
					class="dfinput" value="${vo.fstatus}" />
					<input name="contacts" type="hidden"
					class="dfinput" value="${vo.fcontacts}" />
						<input name="method" type="hidden"
					class="dfinput" value="${method}" />
				<li><label>单位名称：</label><input name="fName" type="text"
					class="dfinput" value="${supplierInfo.FName}" /><i></i></li>
					<li><label>单位简称：</label><input name="fNick" type="text"
					class="dfinput" value="${supplierInfo.FNick}" /><i></i></li>
					<li><label>单位地址：</label><input name="fAddress" type="text"
					class="dfinput" value="${supplierInfo.FAddress}" /><i></i></li>
						<li><label>联系人：</label><input name="fContacts" type="text"
					class="dfinput" value="${supplierInfo.FContacts}" /><i></i></li>
				<li><label>手机：</label><input name="fTel" id="PhoneNum" oninput="inputChange()" type="text"
					class="dfinput" value="${supplierInfo.FTel}" /><i></i></li>
						<li><label>描述：</label><input name="fRemark" type="text"
					class="dfinput" value="${supplierInfo.FRemark}" /><i></i></li>
					<li><label>期末金额：</label><input name="fending_balance" type="text"
					class="dfinput" value="${supplierInfo.fending_balance}" /><i></i></li>
				 <li><label>登记员：</label><input name="fRegistrars" type="text"
					class="dfinput" 
		<c:if test="${empty supplierInfo.FRegistrars}">
		value="${managerUser.name}"
		</c:if>
			<c:if test="${ not empty supplierInfo.FRegistrars}">
		value="${supplierInfo.FRegistrars}"
		</c:if>	 readonly="readonly" /><i></i></li>
							<li><label>&nbsp;</label> <input name="fId" type="hidden"
					class="dfinput" value="${supplierInfo.FId}" /> <input name=""
					type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</div>

	</form>
	<script>
	var phone=$("#PhoneNum").val();
		if(phone!=""){
			if(phone.indexOf(" ")==-1){
			var sub=phone.substring(0,3)+" "+phone.substring(3,7)+" "+phone.substring(7,11);
				$("#PhoneNum").val(sub);
			}
		}
		//分割手机号
		function inputChange(){
			var val=$("#PhoneNum").val();
				if(val.length==3 || val.length==8 ){
				$("#PhoneNum").val(val+" ");
				}
			if(val.trim().length==11){
				if(val.indexOf(" ")==-1){
				var sub=val.substring(0,3)+" "+val.substring(3,7)+" "+val.substring(7,11);
					$("#PhoneNum").val(sub);
				}
			}
		}
		$("#PhoneNum").keydown(function(e){
				var val=$("#PhoneNum").val();
			if(e.which==8){
				if(val[val.length-1]==" "){
					var val1=val.substring(0,val.length-1);
					$("#PhoneNum").val(val1);
				}else{
					var val1=val.substring(0,val.length);
					$("#PhoneNum").val(val1);
				}
			};
		})
	</script>

</body>

</html>
