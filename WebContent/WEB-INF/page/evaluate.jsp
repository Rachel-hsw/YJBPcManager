<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/8
  Time: 13:17
  To change this template use File | Settings | File Templates.

  request: 默认查询所有数据
            /Manager/queryEvaluateList 查询所有评价数据 （含分页）
                /Manager/queryEvaluateList/${prePage}.do
            Manager/queryEvaluateListByCond 根据添加查询所有数据 （含分页）
                /Manager/queryEvaluateListByCond/${reqStr}/${prePage}.do

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="st" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
          type="text/css"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">辅助功能</a></li>
        <li><a href="#">评价管理</a></li>
    </ul>
</div>

<br/>
<form id="form" method="get">
    <div class="tools">
        <ul class="seachform1">
            <li>
                <label>用户名称：</label>
                <input id="reqStr" name='reqStr' class="scinput1" type="text"/>
                <input id="searchs" name="" class="scbtn" value="查询" type="button"/>
            </li>
        </ul>
    </div>
</form>
<script type="text/javascript">
    $("#searchs").click(function(){
        var $req=$("#reqStr").val();
        if ($req == ""){
            $req = "null";
            alert($req);
        }
        var src=' <st:url value="/Manager/queryEvaluateListByCond/'+$req+'/1.do"></st:url>'
        $("form").attr("action",src);
        $("form").submit();
    })
    window.parent.document.getElementById("index").rows="*,0";
</script>

<table class="tablelist">
    <thead>
    <tr>

        <th>序号</th>
        <th>发货时间</th>
        <th>评价时间</th>
        <th>备注</th>
        <th>物流星级</th>
        <th>发货速度星级</th>
        <th>产品描述星级</th>
        <th>服务态度星级</th>
        <th>订单编号</th>
        <th>评价用户</th>
        
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${evaluateList}" var="evaluateList">
        <tr>
            <td>${evaluateList.FID}</td>
            <td>${evaluateList.FDelivery_Time}</td>
            <td>${evaluateList.FDateTime}</td>
            <td>${evaluateList.FRemark}</td>
            <td>${evaluateList.evaluate1}颗星</td>
            <td>${evaluateList.evaluate2}颗星</td>
            <td>${evaluateList.evaluate3}颗星</td>
            <td>${evaluateList.evaluate4}颗星</td>
            <td>${evaluateList.FBillID}</td>
            <td>${evaluateList.fuser_id}</td>
           
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagin">
    <div class="message">
        共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${page}&nbsp;</i>页
    </div>
    <ul class="paginList">
        <li class="paginItem"><a
                href='<st:url value="/Manager/queryEvaluateListByCond/${reqStr}/${prePage}.do"></st:url>'><span
                class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">${page}</a></li>
        <li class="paginItem"><a
                href='<st:url value="/Manager/queryEvaluateListByCond/${reqStr}/${nextPage}.do"></st:url>'><span
                class="pagenxt"></span></a></li>
    </ul>
</div>
</body>
</html>
