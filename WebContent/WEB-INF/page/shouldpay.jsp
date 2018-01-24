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
    dl, dt, dd, span {

	display: inline-block;
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
    <h2 style="text-align: center;font-size: 22px;padding:10px 0; " class="title-top">供应商付款单</h2>
    <form action="${pageContext.request.contextPath}/Manager/paysubmit.do"
		method="post" class="layui-form" >
    <input type="hidden" value="" name="paylist" id="inphide">
        <div class="top">
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">供应商名称：</label>
                        <div class="layui-input-inline purchasingunit ">
                            <input type="text" class="layui-input layui-input0" disabled placeholder="双击选择" value="${FName}"  >
                            <input type="hidden" value="${FId}" name="FSupplier_Id">
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label" >付款方式：</label>
                        <div class="layui-input-inline select182">
                            <select name="FIsDefault" class="layui-input layui-input1" required>
                                <option value="承兑汇票">承兑汇票</option>
                                <option value="转账">转账</option>
                                <option value="支票">支票</option>
                                <option value="现金">现金</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label" >付款账户：</label>
                        <div class="layui-input-inline select182">
                            <select name="FPayment_id" class="layui-input layui-input2" required>
                                <option value="建设">建设</option>
                                <option value="农业">农业</option>
                                <option value="工商">工商</option>
                                <option value="邮政">邮政</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款日期：</label>
                        <div class="layui-input-inline ">
                            <input type="text" class="layui-input layui-input3" placeholder="" name="FS_Date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>" >
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">流水号：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input4" value="" name="FPay_Id" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款总金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input5"  name="FFk_Zje" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">手续费：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input6"  name="FSxf" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">到账金额：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input money layui-input7"  readonly name="FFk_Je" required>
                        </div>
                    </div>

                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">制单员：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input8"  name="FZdy" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">付款人：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input9" name="FPay_Name" required>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs4">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input layui-input10" name="FRemark" >
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="text-align: right; margin-bottom: 10px;">
            <input class="layui-btn layui-btn-small" type="submit" value="确认付款">
            <input class="layui-btn layui-btn-small" type="button" value="打印" id="dayin">
        </div>
  
</div>
<!--=====头部结束====-->

<!--=====表格开始====-->
<div class="container ">
    <table class="table table-bordered table-hover" id="table-title">
        <thead>
        <tr>
            <th style="width:20px;" class="hidden-print"></th>
            <th style="width:50px;">序号</th>
            <th>采购单号</th>
            <th>日期</th>
            <th>供应商</th>
            <th>数量</th>
            <th>重量</th>
            <th>金额</th>
            <th>制单员</th>
            <th>经手人</th>
            <th class="hidden-print">操作</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
      
         <c:forEach items="${purchasinglist}" var="item"  varStatus="s">
        <tr>
            <td class="hidden-print"><input class="check" name="delId" type="checkbox" value="${item.FBillID }"></td>
            <td>${s['index']+1}</td>
            <td>${item.FBillID}</td>
            <td>${item.FS_Date}</td>
            <td>${FName}</td><%-- ${item.FSupplier_Id} --%>
            <td>${item.FSl}</td>
            <td>${item.FZl}</td>
            <td>${item.FJe}</td>
            <td>${item.FZdy}</td>
            <td>${item.username}</td>
           		<td class="hidden-print"> <a name="mx"
					onclick="window.parent.document.getElementById('index').rows='400,*';"
					href="${pageContext.request.contextPath}/Manager/purchaseDetail.do?FBillID=${item.FBillID}"
					target="rightbottomFrame">明细</a></td>
            <td>${item.FRemark}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
  </form>
   <script src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!--=====表格结束====-->
<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>
<script>
    layui.use(["layer"],function(){
        var layer=layui.layer;

        /*----------------- 打印开始---------------------*/
        $("#dayin").click(function () {
            layer.open({
                type:1,
                btn:['打印','取消'],
                area:["1000px","600px"],
                content:"<div id='con' class='layui-container'>" +
                "<h2 style='text-align: center;'>"+$('.title-top').html()+"</h2>" +
//                "<h3 style='text-align: center; margin-top:0px;'>发货清单</h3>" +
                "<table style='width:100%;font-size: 12px; margin-bottom: 0px;'>"+
                "<tbody>"+
                "<tr>" +
                "<td><span class='spanWidth'>供应商称：</span><span>"+$('.layui-input0').val()+"</span></td>" +
                "<td><span class='spanWidth'>付款方式：</span><span>"+$('.layui-input1').val()+"</span></td>" +
                "<td><span class='spanWidth'>付款账户：</span><span>"+$('.layui-input2').val()+"</span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span class='spanWidth'>付款日期：</span><span>"+$('.layui-input3').val()+"</span></td>" +
                "<td><span class='spanWidth'>流&nbsp;水&nbsp;号&nbsp;：</span><span>"+$('.layui-input4').val()+"</span></td>" +
                "<td><span class='spanWidth'>付款金额：</span><span>"+$('.layui-input5').val()+"</span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span class='spanWidth'>手&nbsp;续&nbsp;费&nbsp;：</span><span>"+$('.layui-input6').val()+"</span></td>" +
                "<td><span class='spanWidth'>到账金额：</span><span>"+$('.layui-input7').val()+"</span></td>" +
                "<td><span class='spanWidth'>制&nbsp;单&nbsp;员&nbsp;：</span><span>"+$('.layui-input8').val()+"</span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span class='spanWidth'>付&nbsp;款&nbsp;人&nbsp;：</span><span>"+$('.layui-input9').val()+"</span></td>" +
                "<td><span class='spanWidth'>备&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;：</span><span>"+$('.layui-input10').val()+"</span></td>" +
                "</tr>" +
                "</tbody>"+
                "</table>"+
                "<table class='table table-bordered table-condensed' id='table-con' style='width: 100%;font-size: 12px; margin-bottom: 5px;'>"+$('#table-title').html()+"</table>"+
//                "<div style='display: flex;'>" +
//                "<div style='flex: 1'><span>制单人:</span><span>"+$('#inupt55').val()+"</span></div>" +
//                "<div style='flex: 1'><span>发货人:</span><span></span>"+$('#inupt11').val()+"</div>" +
//                "<div style='flex: 1'><span>签收人:</span><span></span>"+$('#inupt22').val()+"</div>" +
//                "</div>"+
                "</div>",
                shadeClose:true,
                success:function (layero) {
                    var btn = layero.find('.layui-layer-btn0');
                    btn.click(function () {
                        $("#con").jqprint();
                    })
                }
            })
        })
        /*----------------- 打印结束---------------------*/
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
    });

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
