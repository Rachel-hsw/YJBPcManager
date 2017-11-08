// 查询统计
$(function(){
    // 客户筛选内容清空
    $(".reset").click(function(){
        $(".reset1").val("");
    })
    $(".tabclick").click(function(){
        var Month = $(".month").val(),
        	Month1 = $(".month1").val(),
            Reset = $(".reset1").val(),
            select1 =$(".select1").val(),
            select2 =$(".select2").val(),
            select2 =$(".select2").val(),
            radioVal= $('input:radio[name="tcode"]:checked').val(),
            T2 = $("#t2").val();
            //radioVal=$(".box input[name='radio']:checked").val();
           // ${pageContext.request.contextPath}/Manager/queryNumber
            console.log(Month+","+Reset+","+select1+","+select2+","+radioVal+","+T2);
            $.ajax({
            	url:"/ResPcManager/Manager/queryNumber.do",
            	type:"get",
            	data:{
            		kssj:Month,
            		jssj:Month1,
            		tcode:radioVal,
            		customerid:select1
            	},
            success:function(data){
            	console.log(data);
            }
            })
    })
})