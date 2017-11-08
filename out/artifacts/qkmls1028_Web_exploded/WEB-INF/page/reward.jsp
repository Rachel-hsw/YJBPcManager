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

.STYLE1 {
	font-size: 36px;
	font-weight: bold;
	margin:0px auto;
}
.STYLE2 {font-size: 24px}
table{margin:0px auto; }

</style>
</head>
<body>

<div id="ex2_container">
<table width="592" height="494" border="1"  bordercolor="#ffffff">
  <tr>
    <td ><span class="STYLE1">销售奖励</span></td>
  </tr>
  <tr>
    <td  class="STYLE2">
    <form action=' <st:url value="/Manager/Reward.do"></st:url>' 	method="post">
   直推奖： <input  name="Rate" value="${infoList[0].rate}" type="text" style="width:50px; font-size: x-large" onclick="JavaScript:this.value=''" >% 
    <input type="hidden" name="id" value="1" >
    <input type="submit" value="提交"  style="width:80px; font-size: x-large" >
    	
    </form>
    </td>
  </tr>
  <tr>
    <td class="STYLE2"> <form action=' <st:url value="/Manager/Reward.do"></st:url>' 	method="post">
  总监服务奖  <input type="text" name="Rate" value="${infoList[1].rate}"  style="width:50px; font-size: x-large" onclick="JavaScript:this.value=''">%
   <input type="hidden" name="id" value="2" >
    <input type="submit" value="提交"  style="width:80px; font-size: x-large" >
    	
    </form></td>
  </tr>
  <tr>
    <td class="STYLE2"> <form action=' <st:url value="/Manager/Reward.do"></st:url>' 	method="post">
  名下产生总监平级名下一层服务奖： <input  name="Rate" value="${infoList[2].rate}" type="text" style="width:50px; font-size: x-large" onclick="JavaScript:this.value=''" >% 
    <input type="hidden" name="id" value="3" >
    <input type="submit" value="提交"  style="width:80px; font-size: x-large" >
    	
    </form></td>
  </tr>
</table>
</div>

</body>
</html>