<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <title>供应商付款单</title>
    <style>
    .top > div{
        margin-top: 10px;
    }
    .layui-form-label {
         width: 120px;
     }
    .select182{
        width: 182px;
    }
    </style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">财务管理</a></li>
			<li><a href="#">其他收入</a></li>
		</ul>
	</div>
<!--=====头部开始====-->	

<div class="layui-container">
    <h2 style="text-align: center;font-size: 22px;padding:10px 0; ">其他收入填写单</h2>
    <form action="${pageContext.request.contextPath}/Manager/otherearningSubmit.do"
		method="post" class="layui-form" >
		<input type="hidden" value="1" name="FDefault">
   <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">费用类型：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FType" required placeholder="必填" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FJe" required placeholder="必填" > 
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FRemark" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">操作人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FCzy" required placeholder="必填" >
                        </div>
                    </div>
                </div>
                  <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">制单人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FZdy" value="${managerUser.name}" required placeholder="必填" >
                        </div>
                    </div>
                </div>
                </div>
        <div style="text-align: right; margin-bottom: 10px;">
            <input class="layui-btn layui-btn-small" type="submit" value="确认提交">
        </div>
  
</div>
</from>
<!--=====头部结束====-->
<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script type="text/javascript">
layui.use(["laydate","table","layer"],function(){
		var layer=layui.layer;
		var ifsucesss="${ifsucesss}";
	if (ifsucesss!=null&&ifsucesss=="true") {
		
		layer.msg("成功");
		}else if(ifsucesss!=null&&ifsucesss=="false"){
			layer.msg("失败");
		}
	});
window.parent.document.getElementById("index").rows="*,0";
</script>
</body>
</html>
