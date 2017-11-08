<%@ page language="java" contentType="text/html; charset=utf-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
     <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
     <script src="../../../laydate/laydate.js"></script>
    <link rel="stylesheet" href="../../../layer/skin/layui.css"  media="all">
    <title>商品采购进货 purchase/purchase.html</title>
    <style>
        body{
            min-width: 900px;
        }
        .purchase-top{
            margin-left:110px;
            font-size: 30px;
        }
        .layui-input{
             width: 163px;
        }
        .purchase-row{
            display: flex;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .purchase-row div{
            flex: 1;
        }
        .iframe{
            min-width:900px;
            height:600px;
            border: 1px solid red;
        }
        .purchase-total{
            overflow: hidden;
            padding:0 20px;
            margin:20px 0;
        }
        .purchase-total .purchase-input{
            float: right;
        }
        .flex{
            height:200px;
            overflow: hidden;
        }
        .flex div{
            display: inline-block;
            width: 50px;
            height:50px;
            font-size: 18px;
            margin-left: 17%;
            text-align: center;
            background: #eeee00;
            cursor: pointer;
        }
    </style>
</head>
<body>
	<ul class="toolbar">
					
					<div	onclick="tanc()"
						class="click"><span><img
							src="/qkmls/images/addpurchase.png" /></span><div>
				</ul>
 <div class="layui-inline">
            <label class="layui-form-label">入库日期：</label>
            <div class="layui-input-inline">
            <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
            </div>
            <div class="layui-inline purchase-top">商品采购进货</div>
        </div>

    <div class="purchase-row">
        <div>
            <label class="layui-form-label">进货单位：</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text">
            </div>
        </div>

        <div>
            <label class="layui-form-label">进货仓库：</label>
            <div class="layui-input-inline">
                <select class="layui-input">
                    <option>123</option>
                    <option>123</option>
                    <option>123</option>
                    <option>123</option>
                </select>
            </div>
        </div>
        <div>
            <label class="layui-form-label">制单人：</label>
            <div class="layui-input-inline">
                <select class="layui-input">
                    <option>123</option>
                    <option>123</option>
                    <option>123</option>
                    <option>123</option>
                </select>
            </div>
        </div>
    </div>
        <div class="purchase-row">
            <div>
                <label class="layui-form-label">原始单号：</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text">
                </div>
            </div>

            <div>
                <label class="layui-form-label">经手人：</label>
                <div class="layui-input-inline">
                    <select class="layui-input">
                        <option>123</option>
                        <option>123</option>
                        <option>123</option>
                        <option>123</option>
                    </select>
                </div>
            </div>
            <div>
                <label class="layui-form-label">驾驶员：</label>
                <div class="layui-input-inline">
                    <select class="layui-input">
                        <option>123</option>
                        <option>123</option>
                        <option>123</option>
                        <option>123</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="iframe">
            <iframe src="${pageContext.request.contextPath}/Manager/queryPurchase.do" name="iframe" frameborder="0" width="100%" height="100%"></iframe>
        </div>
        <div class="purchase-total">
            <span>合计重量：</span>
            <div class="purchase-input">
                <input type="text" value="0">
                <input type="text" value="0">
                <input type="text" value="0">
            </div>
        </div>
        <div class="flex">
            <div>打印预览</div><div>采购过账</div><div>存入草稿</div><div>废弃退出</div>
        </div>
        
<script>
    // 入库日期
    laydate.render({
        elem:"#test1"
    })
    function tanc(){
	window.parent.document.getElementById("index").rows="*,0";
	window.location.href = "${pageContext.request.contextPath }/Manager/showOrderEdit.do";
}

</script>
</body>
</html>