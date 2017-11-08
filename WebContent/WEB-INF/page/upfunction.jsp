<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>81</title>
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
		if($("#NavigationText").val() == ""){
			layer.msg("角色名必须填写");	
			$("#NavigationText").focus();
			return;
		}
		fin.submit();
	});
});
var laySum = 0; //定义变量 介绍layer的index
function getlay() {  //利用这个方法向子页面传递layer的index
    return laySum;
}

function tanc(){
	laySum =layer.open({
	    type: 2,
	    title: '用户列表',
	    area: ['1000px', '600px'],
	    fix: false, //不固定
	    maxmin: true,
	    shadeClose:true,
	    content: '${pageContext.request.contextPath}/Manager/selectFun2/null/1.do'
	});

}
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">修改菜单</a></li>
		</ul>
	</div>
	<form name="fin"
		action='<st:url value="/Manager/updateINInfo.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>菜单信息</span>
			</div>
			<ul class="forminfo">
				<li><label>菜单名：</label><input id="NavigationText"
					name="NavigationText" type="text" class="dfinput"
					value="${info.navigationText}" /><i></i></li>
				<li><label>上级菜单：</label> <input id="ParentNavigationId"
					name="ParentNavigationId" type="hidden"
					value="${info.parentNavigationId}" /> <input
					id="ParentNavigationName" name="ParentNavigationName" type="text"
					class="dfinput_s" readonly value="${info.parentNavigationName}" /> <input
					onclick="tanc()" type="button" class="btn_s" value="选择" /> <i></i></li>
				<li><label>链接地址：</label><input name="NavigationUrl" type="text"
					class="dfinput" value="${info.navigationUrl}" /><i></i></li>
				<li><label>图片链接：</label><input name="ImageUrl" type="text"
					class="dfinput" value="${info.imageUrl}" /><i></i></li>
				<li><label>是否手机端：</label> <cite> <select id="oneType"
						name="IsMoveby" style="width: 100px;">
							<option value="0" ${info.isMoveby == 0?"selected":""}>否</option>
							<option value="1" ${info.isMoveby == 1?"selected":""}>是</option>
					</select> <i>请下拉选择类别</i>
				</cite></li>
				<li><label>&nbsp;</label> <input name="NavigationId"
					type="hidden" value="${info.navigationId}" /> <input name="page"
					type="hidden" value="${page}" /> <input
					onclick="javascript:history.go(-1);" type="button" class="btn"
					value="返回" /> <input id="sb" name="" type="button" class="btn"
					value="确认保存" /></li>
			</ul>
		</div>
	</form>

</body>

</html>
