<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>11</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/select-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sb").click(function(){
		if($("#RoleName").val() == ""){
			layer.msg("角色名必须填写");
			$("#RoleName").focus();
			return;
		}
		roles.submit();
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
	    area: ['1000px', '500px'],
	    fix: false, //不固定
	    maxmin: true,
	    shadeClose:true,
	    content: '${pageContext.request.contextPath}/Manager/selectEnt/null/1.do'
	});

}

function tanp(){
	laySum =layer.open({
	    type: 2,
	    title: '菜单列表',
	    area: ['1000px', '500px'],
	    fix: false, //不固定
	    maxmin: true,
	    shadeClose:true,
	    content: '${pageContext.request.contextPath}/Manager/selectFun/null/1.do'
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
			<li><a href="#">添加角色</a></li>
		</ul>
	</div>
	<form name="roles"
		action='<st:url value="/Manager/addRoleInfo.do"></st:url>'
		method="post">
		<div class="formbody">
			<div class="formtitle">
				<span>菜单信息</span>
			</div>
			<ul class="forminfo">
				<li><label>角色名：</label><input id="RoleName" name="RoleName"
					type="text" class="dfinput" value="" /><i>${error}</i></li>
				<li><label>相关用户：</label> <input id="users" name="users"
					type="hidden" class="dfinput" value="" /> <input id="userNames"
					name="userNames" type="text" class="dfinput_s" value="" /> <input
					onclick="tanc()" type="button" class="btn_s" value="选择" /> <i></i></li>
				<li><label>菜单权限：</label> <input id="Power" name="Power"
					type="hidden" class="dfinput" value="" /> <input id="PowerNames"
					name="PowerNames" type="text" class="dfinput_s" value="" /> <input
					onclick="tanp()" type="button" class="btn_s" value="选择" /> <i></i></li>
				<li><label>备注：</label><input name="Remark" type="text"
					class="dfinput" value="" /><i></i></li>
				<li><label>是否手机端：</label> <cite> <select id="oneType"
						name="IsMoveby" style="width: 100px;">
							<option value="0" selected="selected">否</option>
							<option value="1">是</option>
					</select> <i>请下拉选择类别</i>
				</cite></li>
				<li><label>&nbsp;</label> <input name="page" type="hidden"
					value="${page}" /> <input onclick="javascript:history.go(-1);"
					type="button" class="btn" value="返回" /> <input id="sb" name=""
					type="button" class="btn" value="确认保存" /></li>
			</ul>
		</div>
	</form>
</body>

</html>
