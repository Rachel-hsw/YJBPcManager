<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
<style type="text/css">

.STYLE1 {
	font-size: 24px;
	font-weight: bold;
	margin:0px auto;
}
.STYLE2 {font-size: 16px}
table{margin:0px auto; }

</style>
</head>
<body>
<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">档案信息</a></li>
			<li><a href="#">返利设置</a></li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/Manager/Reward.do" 	method="post">
<div id="ex2_container" style="padding-top:40px;">
<table width="592" height="494" border="1"  bordercolor="#ffffff" >
  <tr>
    <td ><span class="STYLE1">会员升级</span></td>
  </tr>
  <tr>
    <td  class="STYLE2">
    
  第一次购买9800升级为高级会员，第一次购买19800升级为加盟店会员
    
    </td>
  </tr>
   <tr>
    <td  class="STYLE2">
    
  升级购买本人折扣： <input  name="itemsList[0].Rate" value="${spdBeanList[0].rate}" type="number" style="width:44px; font-weight:900; "  required class="STYLE2">% 
    
    </td>
  </tr>
  <tr>
    <td  class="STYLE2">
   一级推荐奖： <input  name="itemsList[1].Rate" value="${spdBeanList[1].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">% 
    
    </td>
  </tr>
  <tr>
    <td class="STYLE2"> 
 二级推荐奖：  <input type="number" name="itemsList[2].Rate" value="${spdBeanList[2].rate}"  style="width:44px;font-weight:900;  " required class="STYLE2">%
   
	
    </td>
  </tr>
    <tr>
    <td  class="STYLE2">  
   省区域代理福利奖： <input  name="itemsList[3].Rate" value="${spdBeanList[3].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">% 
    

    </td>
  </tr>
    <tr>
    <td  class="STYLE2">  
  市区域代理福利奖： <input  name="itemsList[4].Rate" value="${spdBeanList[4].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">% 
    

    </td>
  </tr>
    <tr>
    <td  class="STYLE2">  
  县区域代理福利奖： <input  name="itemsList[5].Rate" value="${spdBeanList[5].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
  <tr>
     <td ><span class="STYLE1">复销</span></td>
     </tr>
      <tr>
    <td  class="STYLE2">
    
  复销本人折扣： <input  name="itemsList[6].Rate" value="${spdBeanList[6].rate}" type="number" style="width:56px; "  required class="STYLE2">%  
    
    </td>
  </tr>
       <tr>
    <td  class="STYLE2">
    
   一级推荐奖： <input  name="itemsList[7].Rate" value="${spdBeanList[7].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
<tr>
    <td class="STYLE2"> 
 二级推荐奖：  <input type="number" name="itemsList[8].Rate" value="${spdBeanList[8].rate}"  style="width:44px;font-weight:900;  " required class="STYLE2">% 
   	
    </td>
  </tr>
    <tr>
    <td  class="STYLE2">
   省区域代理福利奖： <input  name="itemsList[9].Rate" value="${spdBeanList[9].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
    <tr>
    <td  class="STYLE2">
  市区域代理福利奖： <input  name="itemsList[10].Rate" value="${spdBeanList[10].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    

    </td>
  </tr>
    <tr>
    <td  class="STYLE2">
    
  县区域代理福利奖： <input  name="itemsList[11].Rate" value="${spdBeanList[11].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
  <tr>
   <td ><span class="STYLE1">代理</span></td>
   </tr>
     <tr>
    <td  class="STYLE2">
    
  购买代理本人折扣： <input  name="itemsList[12].Rate" value="${spdBeanList[12].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>     
       <tr>
    <td  class="STYLE2">
    
  一级推荐奖： <input  name="itemsList[13].Rate" value="${spdBeanList[13].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
 <tr>
    <td  class="STYLE2">
    
   二级推荐奖： <input  name="itemsList[14].Rate" value="${spdBeanList[14].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
  <tr>
    <td  class="STYLE2">
    
   直接上级区域代理福利奖： <input  name="itemsList[15].Rate" value="${spdBeanList[15].rate}" type="number" style="width:44px;font-weight:900;  "  required class="STYLE2">%  
    
    </td>
  </tr>
 <tr>
    <td  class="STYLE2">
    
   间接上级区域代理福利奖： <input  name="itemsList[16].Rate" value="${spdBeanList[16].rate}" type="number" style="width:44px;font-weight:900; " required class="STYLE2">%  
    
    </td>
  </tr>
  


</table>
</div>

<input type="submit" value="提交"  style="width:57px;font-size: x-large;display:block;margin:0px auto;" >
</form>
	<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script>
layui.use(["laydate","table","layer"],function(){
		var layer=layui.layer;
		var ifsucesss="${ifsucesss}";
	if (ifsucesss!=null&&ifsucesss=="true") {
		
		layer.msg("修改成功");
		}else if(ifsucesss!=null&&ifsucesss=="false"){
			layer.msg("修改失败");
		}
	});
</script>
</body>
</html>