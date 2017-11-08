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
        <li><a href="#">仓库管理</a></li>
        <li><a href="#">${method=="update"?'修改':'新增'}仓库</a></li>
    </ul>
</div>

<form action=' <st:url value="/Warehouse/saveWarehouse.do"></st:url>'
      method="post">
    <div class="formbody">
        <div class="formtitle">
            <span>仓库信息</span>
        </div>
        <ul class="forminfo">

            <input name="method" type="hidden"
                   class="dfinput" value="${method}" />

            <li><label>仓库名称：</label>
                <input name="FName" type="text" class="dfinput" value="${warehouse.FName}" /><i></i></li>

            <li><label>仓库地址：</label>
                <input name="FAddress" type="text" class="dfinput" value="${warehouse.FAddress}" /><i></i></li>

            <li><label>联系电话：</label>
                <input name="FTel" id="PhoneNum" oninput="inputChange()" type="text" class="dfinput" value="${warehouse.FTel}" /><i></i>
            </li>
            <li><label>备注：</label>
                <input name="FRemark" type="text" class="dfinput" value="${warehouse.FRemark}" /><i></i>
            </li>
                <li><label>&nbsp;</label>
                    <input name="FId" type="hidden" class="dfinput" value="${warehouse.FId}" />
                    <input name="" type="submit" class="btn" value="确认保存" /></li>


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
