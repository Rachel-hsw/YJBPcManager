<%@ page language="java" contentType="text/html; charset=utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table width="929" height="103" border="1">
  <tr>
    <td><div align="center">ID</div></td>
    <td><div align="center">商品编号</div></td>
    <td><div align="center">商品名称</div></td>
    <td><div align="center">规格</div></td>
    <td><div align="center">单位</div></td>
    <td><div align="center">单价</div></td>
    <td><div align="center">重量</div></td>
    <td><div align="center">金额</div></td>
    <td><div align="center">数量</div></td>
  </tr>
 
     <c:forEach items="${infoList}" var="info"> 
     <tr>
    <td><div align="center">ID</div></td>
    <td><div align="center">商品编号</div></td>
    <td><div align="center">商品名称</div></td>
    <td><div align="center">规格</div></td>
    <td><div align="center">单位</div></td>
    <td><div align="center">单价</div></td>
    <td><div align="center">${info.ZL}</div></td>
    <td><div align="center">${info.JE}</div></td>
    <td><div align="center">${info.SL}</div></td>
    </tr>
     </c:forEach> 
  
</table>
</body>
</html>