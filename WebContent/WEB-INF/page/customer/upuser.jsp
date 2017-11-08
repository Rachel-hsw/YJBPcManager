<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
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
			<li><a href="#">用户管理</a></li>
			<li><a href="#">修改用户</a></li>
		</ul>
	</div>

	<form action=' <st:url value="/customer/updateUserInfo.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>用户信息</span>
			</div>
			<ul class="forminfo">
			 <input name="currentPage" type="hidden"
					class="dfinput" value="${vo.currentPage}" />
					<input name="vousername" type="hidden"
					class="dfinput" value="${vo.username}" />
					 <input name="userid" type="hidden"
					class="dfinput" value="${userInfo.userid}" />
				<li><label>姓名：</label><input name="name" type="text"
					class="dfinput" value="${userInfo.name}" /><i></i></li>
				<li><label>手机：</label><input name="tel" id="PhoneNum" oninput="inputChange()" type="text"
					class="dfinput" value="${userInfo.tel}" /><i></i></li>
				<li><label>邮箱：</label><input name="email" type="text"
					class="dfinput" value="${userInfo.email}" /><i></i></li>
				<li><label>身份证号：</label><input name="iccard" type="text"
					class="dfinput" value="${userInfo.iccard}" /><i></i></li>
				<li><label>类别</label> <cite> <select id="oneType"
						name="userlb" style="width: 100px;">
							<option ${userInfo.userlb == "1"?"selected":""} value="1">管理员</option>
							<option ${userInfo.userlb == "2"?"selected":""} value="2">采购员</option>
							<option ${userInfo.userlb == "3"?"selected":""} value="3">会员</option>
					</select> <i>请下拉选择类别</i>
				</cite></li>
							<li><label>&nbsp;</label> <input name="userid" type="hidden"
					class="dfinput" value="${userInfo.userid}" /> <input name=""
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
