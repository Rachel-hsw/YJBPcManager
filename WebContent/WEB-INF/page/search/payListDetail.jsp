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
			<li><a href="#">采购应付</a></li>
		</ul>
	</div>
<!--=====头部开始====-->	

<div class="layui-container">
    <h2 style="text-align: center;font-size: 22px;padding:10px 0; ">供应商付款单</h2>
 
    <input type="hidden" value="" name="paylist" id="inphide">
        <div class="top">
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">供应商名称：</label>
                        <div class="layui-input-inline purchasingunit">
                            <input type="text" class="layui-input" disabled  value="${payList[0].FSupplier_Id}"  readonly="readonly" >
                            
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label" >付款方式：</label>
                         <div class="layui-input-inline purchasingunit">
                            <input type="text" class="layui-input" disabled  value="${payList[0].FIsDefault}" readonly="readonly"  >
                            
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label" >付款账户：</label>
                        <div class="layui-input-inline purchasingunit">
                            <input type="text" class="layui-input" disabled value="${payList[0].FPayment_id}"  readonly="readonly" >         
                        </div>           
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款日期：</label>
                        <div class="layui-input-inline ">
                            <input type="text" class="layui-input"  name="FS_Date" value="${payList[0].FS_Date}"  readonly="readonly" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">流水号：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"   value="${payList[0].FPay_Id}" required readonly="readonly" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款总金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FFk_Zje" required value="${payList[0].FFk_Zje}" readonly="readonly" >
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">手续费：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FSxf" required value="${payList[0].FSxf}" readonly="readonly" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">到账金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input money"  readonly name="FFk_Je" required value="${payList[0].FFk_Je}" readonly="readonly" >
                        </div>
                    </div>

                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">制单员：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input"  name="FZdy" required value="${payList[0].FZdy}" readonly="readonly" >
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" name="FPay_Name" required value="${payList[0].FPay_Name}" readonly="readonly" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" name="FRemark"  value="${payList[0].FRemark}" readonly="readonly" >
                        </div>
                    </div>
                </div>
            </div>
        </div>
  
</div>
<!--=====头部结束====-->

<!--=====表格开始====-->
<div class="container ">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
          <!--   <th style="width:20px;"></th> -->
            <th style="width:50px;">序号</th>
            <th>采购单号</th>
            <th>日期</th>
            <th>供应商</th>
            <th>数量</th>
            <th>重量</th>
            <th>金额</th>
            <th>制单员</th>
            <th>经手人</th>
            <th>操作</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
         <c:forEach items="${mianList}" var="item"  varStatus="s">
        <tr>
         <%--    <td><input class="check" name="delId" type="checkbox" value="${item.FBillID }"></td> --%>
            <td>${s['index']+1}</td>
            <td>${item.FSource_Id}</td>
            <td>${item.FS_Date}</td>
            <td>${item.FSupplier_Id}</td><%-- ${item.FSupplier_Id} --%>
            <td>${item.FSl}</td>
            <td>${item.FZl}</td>
            <td>${item.FJe}</td>
            <td>${item.FZdy}</td>
            <td>${item.FJsr}</td>
           		<td> <a name="mx"
					onclick="window.parent.document.getElementById('index').rows='400,*';"
					href="${pageContext.request.contextPath}/Manager/purchaseDetail.do?FBillID=${item.FBillID}"
					target="rightbottomFrame">明细</a></td>
            <td>${item.FRemark}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
 
<!--=====表格结束====-->

</body>
</html>
