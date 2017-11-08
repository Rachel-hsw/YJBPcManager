<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
<style type="text/css">
<!--
.STYLE1 {font-size: x-large}
.STYLE2 {font-size: xx-large}
-->
</style>
</head>
<body>
<center>
<p>&nbsp;</p>
<p class="STYLE2">青稞麦绿素全国统一代理方案</p>
<table width="636" height="558" border="1" bordercolor="#FFFFFF">

   <c:forEach items="${infoList}" var="info"> 
   <tr>
       <form action=' <st:url value="/Manager/zk.do"></st:url>' 	method="post">
    <td width="142" align="center"><span class="STYLE1">${info.p_Package}</span></td>
    <td width="120" align="center"><span class="STYLE1">
      <input  name="p_Num" value="${info.p_Num}" type="text" style="width:80px; font-size: x-large" >盒
    </span></td>
    <td width="143" align="center"><span class="STYLE1">
   <input  name="p_Money" value="${info.p_Money}" type="text" style="width:80px; font-size: x-large"  >元
    </span></td>
    <td width="200" align="center"><span class="STYLE1">
    <input  name="P_Price" value="${info.p_Price}" type="text" style="width:80px; font-size: x-large"  >/盒
    <input type="hidden" name="daili" value="${info.p_Price_id}" >
    <input type="submit" value="提交"  style="width:70px; font-size: x-large" > 
    </span></td>
     </form>
  </tr>
   </c:forEach> 
</table>
</center>
</body>
</html>