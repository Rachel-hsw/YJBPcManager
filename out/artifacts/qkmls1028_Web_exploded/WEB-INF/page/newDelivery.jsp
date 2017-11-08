<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <title>订单明细表</title>
    <style>
        tr{
            width:100%;
        }

        tr th{
            width:auto;
        }
        .layui-form-label{
            padding: 9px 6px;
            width: 30px;
        }
        .layui-table-view {
            margin: 0px;
        }
        .layui-form-item {
            margin-bottom: 0px;
        }
        .layui-form-item .layui-input-inline {
            width: 100px;
        }
        .layui-form-item .layui-inline {
            margin-right: 0px;
        }
    </style>
</head>
<body>
<!--======头部查询开始=======-->
<form action="">
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">至</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test1-1" placeholder="yyyy-MM-dd">
                </div>
            </div>
            <input type="submit" value="查询" class="layui-btn">
        </div>
    </div>
</form>
<!--======头部查询结束=======-->
<!--======表格开始=======-->
<div class="layui-form">
    <table class="layui-table" lay-data="{url:'data.json'}" lay-filter="demo"}>
        <!--======表格头部开始=======-->
        <thead>
            <tr>
                <th lay-data="{field:'id', width:'80', sort: true}">序号</th>
                <th lay-data="{field:'uid', width:'80', sort: true}">会员ID</th>
                <th lay-data="{field:'order', width:'150', sort: true}">订单号</th>
                <th lay-data="{field:'date', width:'120', sort: true}">订单日期</th>
                <th lay-data="{field:'username', width:'100'}">会员姓名</th>
                <th lay-data="{field:'tel', width:'130', sort: true}">手机号码</th>
                <th lay-data="{field:'product', width:'120'}">商品名称</th>
                <th lay-data="{field:'guige', width:'100'}">规格</th>
                <th lay-data="{field:'Company', width:'80'}">单位</th>
                <th lay-data="{field:'num', width:'80'}">数量</th>
                <th lay-data="{field:'weight', width:'80'}">重量</th>
                <th lay-data="{field:'name', width:'150'}">仓库名称</th>
                <th lay-data="{field:'shang', width:'100'}">仓库上限</th>
                <th lay-data="{field:'caozuo', width:'100', toolbar: '#barDemo'}">操作</th>
                <th lay-data="{field:'beizhu', width:'120'}">备注</th>
            </tr>
        </thead>
        <!--======表格头部结束=======-->

    </table>
</div>
<!--======表格结束=======-->
<script src="${pageContext.request.contextPath }/layui/public.js"></script>
<script src="${pageContext.request.contextPath }/layui/layui.all.js"></script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">发货</a>
</script>
<script>
    /* =========电话号码用空格分割开始========= */
    telSplit($("tr td div.laytable-cell-1-sex"));
    /* =========电话号码用空格分割结束========= */

    /* =========框架代码开始========= */
    layui.use(['table','laydate','layer'], function(){
        var table = layui.table,
            laydate = layui.laydate,
            layer = layui.layer;
        laydate.render({
            elem: '#test1'
        });
        laydate.render({
            elem: '#test1-1'
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    type: 1
                    ,area:["1000px","600px"]
                    ,content: data.id
                    ,btn: '关闭',
                    moveType: 1,
                    shade: 0.2,
                    shadeClose:true
                })
            }
        });
    });
    /* =========框架代码结束========= */

</script>
</body>
</html>