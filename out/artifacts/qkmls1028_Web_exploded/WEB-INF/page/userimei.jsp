<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>83</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sb").click(function(){
		if($(".dfinput").val() == ""){
			layer.msg("角色名必须填写");	
			$(".dfinput").focus();
			return;
		}else{
		roles.submit();
		}
	});
});
var laySum = 0; //定义变量 介绍layer的index
function getlay() {  //利用这个方法向子页面传递layer的index
    return laySum;
}
function tanc(uid){
	laySum =layer.open({
	    type: 2,
	    title: '用户列表',
	    area: ['1000px', '500px'],
	    fix: false, //不固定
	    maxmin: true,
	    shadeClose:true,
	    content: '${pageContext.request.contextPath}/Manager/selImeiList/'+uid+'.do'
	});

}
</script>
<style>
.forminfo li {
    float: none;
    width:100%;
}
</style>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户管理</a></li>
			<li><a href="#">账户绑定</a></li>
		</ul>
	</div>
	<form name="roles"
		action='<st:url value="/Manager/addUserImei/1/${uId}.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>菜单信息</span>
			</div>
			<ul class="forminfo">
				<li>
					<label>用户：</label>
					<input name="nickName" class="dfinput"	value="${nickName}" /> 
					<input class="dfinput" type="hidden" value="${uId}" />
				</li>
				<li>
					<label>IMEI编码：</label>
					<input id="ImeiId" name="ImeiId" type="hidden" class="dfinput" value="" />
					<input id="imei" name="imei" type="text" class="dfinput" value="" /> 
					<input onclick="tanc('${uId}')" type="button" class="btn" value="选择" />
				</li>
				<li style="margin-left:85px">
					<input onclick="javascript:history.go(-1);" type="button" class="btn" value="返回" />
					<input id="sb" name="" type="button" class="btn" value="确认保存" />
				</li>
			</ul>
		</div>
	</form>
</body>

</html>
