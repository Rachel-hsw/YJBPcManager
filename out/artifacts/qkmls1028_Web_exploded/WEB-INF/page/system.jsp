<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>78</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript">
window.parent.document.getElementById('index').rows='*,0';
$(document).ready(function(e) {
	var i = "${id}";
	var n = "${name}";
	var s = "${state}";
	if(n == "null") n = "";
	if(s == "null") s = "";
	if(i == "null") i = "";
	$("#id").val(i);
	$("#name").val(n);
	$("#State").val(s)
	$(".select3").uedSelect({
		width : 100
	});
	
	$("#searchs").click(function(){
		var id = $("#id").val();
		var name = $("#name").val();
		var state = $("#state").val();
		if(id != null&&id != ""){
			id = encodeURI(id);
			id = encodeURI(id);
		}else{
			id = "null";
		}
		if(name != null&&name != ""){
			name = encodeURI(name);
			name = encodeURI(name);
		}else{
			name = "null";
		}
		if(state != null&&state != ""){
			state = encodeURI(state);
			state = encodeURI(state);
		}else{
			state = "null";
		}		
		window.location.href = "${pageContext.request.contextPath}/Manager/system/"+id+"/"+name+"/"+state+"/1.do";
	});
	
	
});

function getPage(id,name,state,nextPage){
	if(id != null&&id != ""){
		id = encodeURI(id);
		id = encodeURI(id);
	}else{
		id = "null";
	}
	if(name != null&&name != ""){
		name = encodeURI(name);
		name = encodeURI(name);
	}else{
		name = "null";
	}	
	if(state != null&&state != ""){
		state = encodeURI(state);
		state = encodeURI(state);
	}else{
		state = "null";
	}
	window.location.href = "${pageContext.request.contextPath}/Manager/system/"+id+"/"+name+"/"+state+'/'+nextPage+".do";
}



function tanc(id,name,state,page){
	if(id != null&&id != ""){
		id = encodeURI(id);
		id = encodeURI(id);
	}else{
		id = "null";
	}
	if(name != null&&name != ""){
		name = encodeURI(name);
		name = encodeURI(name);
	}else{
		name = "null";
	}
	if(state != null&&state != ""){
		state = encodeURI(state);
		state = encodeURI(state);
	}else{
		state = "null";
	}
	window.location.href = '${pageContext.request.contextPath}/Manager/systemAdd/'+id+"/"+name+"/"+state+'/1.do';
}


function edits(id,name,state,page){

	if(name != null&&name != ""){
		name = encodeURI(name);
		name = encodeURI(name);
	}else{
		name = "null";
	}
	if(state != null&&state != ""){
		state = encodeURI(state);
		state = encodeURI(state);
	}else{
		state = "null";
	}
	window.location.href = '${pageContext.request.contextPath}/Manager/systemAdd/'+id+"/"+name+"/"+state+'/'+page+'.do';
}
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统设置</a></li>
			<li><a href="#">系统参数</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<ul class="seachform1">
			<li><label>系统参数：</label><input id="name" name="name"type="text" class="scinput1" /></li>
			
			<li><label>&nbsp;</label><input id="searchs" name="" type="button" class="scbtn" value="查询" /></li>
		</ul>
		<!-- 查询参数显示开始 -->
		<table class="tablelist">
			<thead>
				<tr>
					<th style="display:none;">id</th>
					<th>参数名称</th>
					<th>参数状态</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 参数尚未修改 --><c:forEach items="${configList}" var="config" varStatus="status">
					<tr>
					                        <td style="display:none;">${config.id}</td>
						<!-- 参数尚未修改 --><td>${config.name}</td>
						<!-- 参数尚未修改 --><td>${config.state==0?'否':'是'}</td>
						<td>
							<!-- 参数尚未修改 --><a onclick="edits('${config.id}','${config.name}','${config.state}',${page})" href='#'>修改</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 查询参数显示开结束-->
		<!-- 分页开始 --><!-- 参数尚未修改 -->
		<div class="pagin">
			<div class="message">
				共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem">
					<a onclick="getPage('${id}','${name}','${state}','${prePage}')" href='#'>
						<span class="pagepre"></span>
					</a>
				</li>
				<li class="paginItem"><a href="javascript:;">${page}</a></li>
				<li class="paginItem">
					<a onclick="getPage('${id}','${name}','${state}','${nextPage}')" href='#'>
						<span class="pagenxt"></span>
					</a>
				</li>
			</ul>
		</div>
		<!-- 分页结束 -->
	</div>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>