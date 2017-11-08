<%@ page language="java" contentType="text/html; charset=utf-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="/ResPcManager/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="/ResPcManager/css/select.css" rel="stylesheet"
	type="text/css" />
	
	<style>
	
	.forminfo li {
    	width: 550px;
	}
</style>
</head>
<body>
<ul class="forminfo" style="">



     <form action=' <st:url value="/Manager/addPurchase.do"></st:url>' 	method="post">
			<li style="width: 100%; line-height: 34px;"><label></label>
					</li>
				<li><label>进货单位：</label> <input id="SaleoidInfo"
					name="User_id" type="text" class="dfinput"
					 /> <i
					id="SaleoidError"></i></li>

				<li><label>进货仓库：</label> <input id="SendoidInfo"
					name="W_ID" type="text" class="dfinput"
					 /> <i
					id="SourceoidError"></i></li>

				<li><label>制单人：</label> <input id="SendoidInfo"
					name="ZDY" type="text" class="dfinput"
					/> <i
					id="SendoidError"></i></li>

<li><label>原始单号：</label> <input id="SendoidInfo"
					name="Order_id" type="text" class="dfinput"
					 /> <i
					id="SourceoidError"></i></li>
<li><label>经手人：</label> <input id="SendoidInfo"
					name="JSR" type="text" class="dfinput"
					 /> <i
					id="SourceoidError"></i></li>

			<!-- 	<li><label>原始单号：</label> <input id="CustomerID"
					name="Order_id" type="hidden"
					/>
					<input id="CustomerName" name="ordercustomerInfo" readOnly
					type="text" class="dfinput_s"
					/> <input
					onclick="selectCustomer()" type="button" class="btn_s" value="选择" />
					<i id="ordercustomerError"></i></li> -->
<!-- 
				<li><label>经手人：</label> <input id="TownInfo" readOnly
					name="JSR" type="text" class="dfinput"
					 /> <input onclick="selectTown()" type="button" class="btn" value="选择"/>
					<i id="TownError"></i></li> -->

				<li><label>驾驶员：</label> <input id="ShippingaddressInfo"
					name="S_J_ID" type="text" class="dfinput"
					/>
					<i id="ShippingaddressError"></i></li>

	

					
						
						<li><label>重量(吨)：</label> <input id="ShippingweightInfo"
					name="ZL" type="text" class="dfinput"
					 /> <i
					id="ShippingweightError"></i></li>
							<li><label>金额：</label> <input id="QuantityInfo"
					name="JE" type="text" class="dfinput"
					/> <i
					id="QuantityError"></i></li>

				   <li><label>数量：</label> <input id="ShippingweightInfo"
					name="SL" type="text" class="dfinput"
					 /> <i
					id="ShippingweightError"></i></li>
					
					

				<li>
				<span style="color: red">${error }</span><label>&nbsp;</label><input id="sb" type="submit"
					class="btn" value="确认保存" />
					</li>
					 </form>
			</ul>
</body>
</html>