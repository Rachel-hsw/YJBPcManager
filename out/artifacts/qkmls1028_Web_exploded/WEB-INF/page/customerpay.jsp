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

    <title>客户、会员付款单</title>
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
<!--=====头部开始====-->
<div class="layui-container">
    <h2 style="text-align: center;font-size: 22px;padding:10px 0; ">客户、会员付款单</h2>
    <form action="${pageContext.request.contextPath}/Manager/paysubmit.do"
		method="post" class="layui-form" >
    <input type="hidden" value="" name="paylist" id="inphide">
        <div class="top">
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">会员名称：</label>
                        <div class="layui-input-inline purchasingunit">
                            <input type="text" class="layui-input" disabled placeholder="双击选择" value="${FName}" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款方式：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" required>
                        </div>
                    </div>
                    </div>
                <div class="layui-col-xs4">
                     <div class="layui-inline">
                        <label class="layui-form-label">收款账户：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">收款日期：</label>
                        <div class="layui-input-inline ">
                            <input required type="text" class="layui-input" placeholder="" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>" >
                        </div>
                    </div>
                </div>
             
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">流水号：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" value=""  required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">收款金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder=""  required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">手续费：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">到账金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input money" placeholder="" readonly required>
                        </div>
                    </div>

                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">制单员：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">收款人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" placeholder="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="text-align: right; margin-bottom: 10px;">
            <input class="layui-btn layui-btn-small" type="submit" value="确认付款">
            <input class="layui-btn layui-btn-small" type="button" value="打印">
        </div>
  
</div>
<!--=====头部结束====-->

<!--=====表格开始====-->
<div class="container ">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th style="width:20px;"></th>
            <th style="width:50px;">序号</th>
            <th>会员ID</th>
            <th>申请级别</th>
            <th>区域代码</th>
            <th>公司账号ID</th>
            <th>交易流水号</th>
            <th>申请提交时间</th>      
            <th>操作</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        ${purchasinglist[0].FBillID}
         <c:forEach items="${purchasinglist}" var="item"  varStatus="s">
        <tr>
            <td><input class="check" name="delId" type="checkbox" value="${item.FBillID }"></td>
            <td>${s['index']+1}</td>
            <td>${item.FBillID}</td>
            <td>${item.FS_Date}</td>
            <td>${FName}</td><%-- ${item.FSupplier_Id} --%>
            <td>${item.FSl}</td>
            <td>${item.FZl}</td>
            <td>${item.FJe}</td>      
           		<td> <a name="mx"	onclick="window.parent.document.getElementById('index').rows='400,*';"
					href="${pageContext.request.contextPath}/Manager/purchaseDetail.do?FBillID=${item.FBillID}"
					target="rightbottomFrame">明细</a></td>
            <td>${item.FRemark}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
  </form>
<!--=====表格结束====-->
<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script>
    layui.use(["layer"],function(){
        var layer=layui.layer;
    });
    /*----------------- 供应商名称开始---------------------*/
    $(".purchasingunit").dblclick(function(){
        layer.open({
            type: 1,
            title: '进货单位',
            btn:["取消"],
            area: ['1000px', '600px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose:true,
            content: "<table class='layui-table'>"+
            "<thead>"+
            "<tr>"+
            "<th>单位名称</th>"+
            "<th>单位简称</th>"+
            "<th>拼音首字母</th>"+
            "<th>联系人</th>"+
            "<th>联系地址</th>"+
            "<th>联系电话</th>"+
            "</tr>"+
            "</thead>"+
            "<tbody class='table1'>"+
            <c:forEach items="${supplierlist}" var="item" varStatus="ittt">
			   
            
			"<tr>"+
				"<td class='com'>${item.FName}</td>"+
				"<td>${item.FNick}</td>"+
				"<td>${item.FPym}</td>"+
				"<td>${item.FContacts}</td>"+
				"<td>${item.FAddress}</td>"+
				"<td>${item.FTel}</td>"+
				"<td style='display:none'><input type='text' class='kintwo' value='${item.FId}'></td>"+
			"</tr>"+
			 </c:forEach>
            "</tbody>"+
            "</table>"
        })
        var that=$(this).find("input");
        $("tbody.table1 tr").click(function(){
            that.val($(this).find(".com").html());
            var enl=$(this).find(".kintwo").val();
            var enl1=$(this).find(".com").html();
          /*
            $.ajax({
            	url:"${pageContext.request.contextPath}/Manager/shouldpay.do",
            	data:{
            		FId:enl
            	}
            })
            */
            $("#canshu").val(enl);
            $("#canshu1").val(enl1);
          $("#form").submit();
            layer.closeAll("page");
        })
    });
    /*----------------- 供应商名称结束---------------------*/

    /*----------------- 到账金额开始---------------------*/

    /*----------------- 到账金额开始---------------------*/
    var num=0;
    var str=[];
    $(".check").click(function () {
        var html=$(this).parent().siblings("td").eq(6).html();
        var lis=$(this).parent().siblings("td").eq(1).html();
        if($(this).is(':checked')){
            num+=Number(html);
            str.push(lis);
        }else{
            num-=Number(html);
            str = $.grep(str, function(value) {
                return value != lis;
            });
        };
        $(".money").val(num);
        $("#inphide").val(str);
    })
    /*----------------- 到账金额结束---------------------*/
    /*----------------- 到账金额结束---------------------*/
window.parent.document.getElementById("index").rows="*,0";
</script>
<form action="${pageContext.request.contextPath}/Manager/shouldpay.do" id="form" method="post">
	<input type="hidden" id="canshu" name="FId">
		<input type="hidden" id="canshu1" name="FName">
</form>
</body>
</html>
