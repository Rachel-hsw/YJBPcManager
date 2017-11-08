<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"
          type="text/css" />
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
</head>
<body>

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">仓库管理</a></li>
            <li><a href="#">仓库列表</a></li>
        </ul>
    </div>
    <br>
    <form action=' <st:url value="/Warehouse/warehousePageLise.do"></st:url>' method="post">
        <div class="tools">
            <ul class="seachform1">
                <li><label>仓库名称：</label><input id="FName" name=FName value="${vo.FName}"
                                             class="scinput1" type="text" /></li>
                <li><input id="searchs" name="" class="scbtn" value="查询"
                           type="submit" /></li>
            </ul>
        </div>
    </form>
    <div class="tools">
        <ul class="toolbar">
            <li >
                <a href="${pageContext.request.contextPath }/Warehouse/toAddWarehouse.do?method=add">
                    <span><img src="../images/t01.png" /></span>添加
                </a>

            </li>
        </ul>
    </div>
<table class="tablelist">
    <tr>
        <th>序号</th>
        <th>仓库名称</th>
        <th>仓库地址</th>
        <th>联系电话</th>
        <th>备注</th>
        <th width="15%">操作</th>
        <%--warehouseEntityList--%>
    </tr>
    <c:forEach items="${requestScope.pageBean.queryNumberList}" var="warehouse" varStatus="s">

        <tr>
            <td>${s.index + 1}</td>
            <td>${warehouse.FName}</td>
            <td>${warehouse.FAddress}</td>
            <td>${warehouse.FTel}</td>
            <td>${warehouse.FRemark}</td>
            <td><a
                    href='<st:url value="/Warehouse/deleteWarehouse.do?FId=${warehouse.FId}"></st:url>'
                    class="tablelink">${warehouse.FStatus==0?'无效':'有效'}</a>
                <a href='<st:url value="/Warehouse/toUpdate.do?FId=${warehouse.FId}&method=update"></st:url>'
                        class="tablelink">修改</a>
            </td>
            <td>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
<!-- 上一页 -->
<!-- 判断当前页是否是第一页 -->
<div style="margin-top: 20px; text-align:center;">
    <ul class="pagination" style="text-align: center; margin-top: 10px;">
        <li>
            <a>共${pageBean.totalCount }条记录</a>
            <a>共${pageBean.totalPage} 页</a>
            <c:if test="${pageBean.currentPage!=1&&not empty pageBean.totalCount }">
                <a href="${pageContext.request.contextPath}/Warehouse/warehousePageLise.do?currentPage=1&FName=${vo.FName}&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel} ">首页</a>
            </c:if>
            <%-- 	<c:if test="${pageBean.currentPage==1|| empty pageBean.totalCount }">
                 <a href="javascript:void(0);">首页</a>
                 </c:if> --%>
        </li>
        <c:if test="${pageBean.currentPage==1|| empty pageBean.totalCount}">
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage!=1 &&not empty pageBean.totalCount}">
            <li >
                <a href="${pageContext.request.contextPath}/Warehouse/warehousePageLise.do?currentPage=${pageBean.currentPage-1}&FName=${vo.FName}&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel}"  aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.totalPage<=5}">
            <c:set var="begin" value="1" scope="page"></c:set>
            <c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
        </c:if>
        <c:if test="${pageBean.totalPage>5}">
            <c:choose>
                <c:when test="${pageBean.currentPage<=3}">
                    <c:set var="begin" value="1" scope="page"></c:set>
                    <c:set var="end" value="5" scope="page"></c:set>
                </c:when>
                <c:when test="${pageBean.currentPage>=pageBean.totalPage-2}">
                    <c:set var="begin" value="${pageBean.totalPage-4}" scope="page"></c:set>
                    <c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${pageBean.currentPage-2}" scope="page"></c:set>
                    <c:set var="end" value="${pageBean.currentPage+2}" scope="page"></c:set>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:forEach begin="${begin}" end="${end}" step="1" var="i">
            <c:if test="${i == pageBean.currentPage}">
                <li class="active"><a href="javascript:void(0);">${i}</a></li>
            </c:if>
            <c:if test="${i != pageBean.currentPage&&not empty pageBean.totalCount&& pageBean.totalCount!=0}">
                <li ><a href="${pageContext.request.contextPath}/Warehouse/warehousePageLise.do?currentPage=${i}&FName=${vo.FName }&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel}">${i}</a></li>
            </c:if>
        </c:forEach>


        <!-- 判断当前页是否是最后一页 -->
        <c:if test="${pageBean.currentPage==pageBean.totalPage }">
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>

        </c:if>
        <c:if test="${pageBean.currentPage!=pageBean.totalPage}">

            <li>
                <a href="${pageContext.request.contextPath}/Warehouse/warehousePageLise.do?currentPage=${pageBean.currentPage+1}&FName=${vo.FName }&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage!=pageBean.totalPage&&not empty pageBean.totalPage }">
            <li><a href="${pageContext.request.contextPath}/Warehouse/warehousePageLise.do?currentPage=${pageBean.totalPage}&FName=${vo.FName }&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel}">尾页</a></li>
        </c:if>
        <li> <a>跳到<input type="text"
                         value="${pageBean.currentPage }" style="width: 50px; height:17px; outline:none; " onchange="changePage(this)"/>页
        </a></li>
        <li><a href="javascript:void(0);"style="">跳转</a></li>
    </ul>


</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
    $(".telsplit").each(function(){
        if($(this).html() != ""){
            var text=$(this).html().trim();
            var txt=text.substring(0,3)+"-"+text.substring(3,7)+"-"+text.substring(7,11);
            $(this).html(txt);
        };
    })


    function changePage(obj){
        if((!/^[0-9]\d*$/.test(obj.value))){
            alert("必须是正整数!");
            obj.value=${pageBean.currentPage}
            return;
        }else if(obj.value<=0 || (obj.value>(${pageBean.totalPage})) ){
            alert("页码必须在有效范围内!");
            obj.value=${pageBean.currentPage}
            return;
        }else{

            window.location.href="${pageContext.request.contextPath }/Warehouse/warehousePageLise.do?currentPage="+obj.value+"&FName=${vo.FName }&FAddress=${vo.FAddress}&FRemark=${vo.FRemark}&FTel=${vo.FTel}";
        }
    }

</script>
</html>
