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

    <script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
    <title>会员升级付款</title>
    <style>
    .top > div{
        margin-top: 10px;
    }
    .layui-form-label {
         width: 120px;
     }
    .select182{
        width: 143px;
    }
    </style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">财务管理</a></li>
			<li><a href="#">会员升级付款</a></li>
		</ul>
	</div>
<!--=====头部开始====-->	

<div class="layui-container">
    <h2 style="text-align: center;font-size: 22px;padding:10px 0; ">会员升级付款</h2>
    <form action="${pageContext.request.contextPath}/Manager/custmercostSubmit.do"
		method="post" class="layui-form" id="selectForm" >
		<input type="hidden" value="0" name="FDefault">
   <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">会员ID:：</label>
                        <div class="layui-input-inline">
                            <input type="number" class="layui-input"  name="User_id"  id="User_id" required placeholder="必填">
                        </div>
                    </div>
                </div>
         <!--         <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">收货人姓名：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FSname" required placeholder="必填">
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">收货人电话：</label>
                        <div class="layui-input-inline ">
                            <input type="number" class="layui-input"  name="Ftel" required placeholder="必填" >
                        </div>
                    </div>
                </div> -->
                   <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label" >金额：</label>
                        <div class="layui-input-inline select182" >
                            <select name="Fje"  id="Fje" class="layui-input"  required style="display:block;"   placeholder="必填">
                            <option value="9800">9800</option>
                                <option value="19800">19800</option>
                            </select>
                        </div>
                    </div>
                </div>
              <!--   <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">金额：</label>
                        <div class="layui-input-inline">
                          <select name="Fje"  class="layui-input" required id="Fje">
                                <option value="9800">9800</option>
                                <option value="19800">19800</option>
                            </select>
                        </div>
                    </div>
                </div>
                 -->
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">操作人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FJsr" required placeholder="必填">
                        </div>
                    </div>
                </div>
                  <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">制单人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FZdy" value="${managerUser.name}" required placeholder="必填">
                        </div>
                    </div>
                </div>
                 <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FRemark" value="流水号：">
                        </div>
                    </div>
                </div>
               <!--  <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">发货地址：</label>
                        <div class="layui-input-inline">
                            省：<input type="text" class="layui-input"  name="Province" >
            市：<input type="text" class="layui-input"  name="City" >县： <input type="text" class="layui-input"  name="County" >   
            详细地址:<input type="text" class="layui-input"  name="FAddress" >            
                        </div>
                    </div>
                </div> -->
                </div>
        <div style="text-align: center; margin-bottom: 10px;">
            <input class="layui-btn layui-btn-small" type="button" value="确认提交" id="complete">
        </div>
  
</div>
</from>
<!--=====头部结束====-->

	<script>
	
	layui.use(["laydate","table","layer"],function(){
 		var layer=layui.layer;
 		var ifsucesss="${ifsucesss}";
		if (ifsucesss=="true") {
			
			layer.msg("${result}");
			}else if(ifsucesss=="false"){
				layer.msg("${result}");
			}
		
		
		
		$("#complete").click(function () {
			var Fje=$("#Fje").val();
			var User_id=$("#User_id").val();
			if (Fje==""||User_id=="") {
				layer.msg("请完善所有必填选项");
				return;
			}
			if(parseInt(Fje)==9800||parseInt(Fje)==19800){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/Manager/queryUpdate.do",
				data:{
					User_id:User_id,
				    Fje:Fje
				},
				success:function(data){
					console.log(data);
					if (data[0]==1) {//判断为1，弹出提醒框
						layer.msg(data[1]);
					}else if (data[0]==2) {//判断为2，弹出确认框
						layer.open( {
					        time:0,
					        btn: ['确认', '取消'],
					        content:data[1],
					        success: function(layero){
					          layero.find('.layui-layer-btn0').click(function(){
					        	  //点击确认进行升级
					          	$("#selectForm").submit();
					          })
					          layero.find('.layui-layer-btn1').click(function(){
					      //点击取消什么操作都不要做
					   
					          })
					        }
					     });	
					}
				
				}
			});


		  
		 
		}else{
			$("#selectForm").submit();		
		}
 	});
	});
	window.parent.document.getElementById("index").rows="*,0";
</script>
</body>
</html>
