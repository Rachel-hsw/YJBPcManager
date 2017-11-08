<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>青稞麦绿素管理系统界面</title>
<style>
	*{margin:0;padding:0;}
	#home-top-msg{
		position:absolute;
		top:45px;
		right:15px;
		width:50px;
	}
	#hidden{
		position:absolute;
		top:30px;
		right:0px;
		width:160px;
		padding:5px;
		background:rgba(0,0,0,0.1);
	}
	#hidden p{
		line-height:30px;
		margin-bottom:1px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
	}
	#hidden > p > span.spanMsg{
		color:red;
		font-size:12px;
		vertical-align:top;
	}
	/* rgb(215, 163, 114)*/
</style>
</head>
<body style="margin: 0px;height: 900px;"> 
<iframe src='<st:url value="/Manager/showMain_main.do"></st:url>' 
style="height:100%;width:100%;border-width: 0px;"> 
</iframe> 

</body> 
</html>