/*
	js实用方法 基于jQuery
	1、telSplit(class)	电话号码分割成 3 4 4 格式数字 方法中传入class类名即可(必填) input无效
	2、timer(obj,num,callback) 倒计时插件 obj参数(必填) 是jquery id名，num参数(必填) 是倒计时总秒数，callback是倒计时完成后的回调函数（可选）。
	3、clickEdit(obj,cb)  obj参数(必填) obj必须是jquery对象 cb回调函数(可选) 返回修改后的值
*/
	// 电话号码用空格分割
	function telSplit(obj){
    	obj.each(function(){
    		if($(this).html() != ""){
    			var text=$(this).html().replace(/ /g,"");
    			var txt=text.substring(0,3)+" "+text.substring(3,7)+" "+text.substring(7,11);
    			$(this).html(txt);
    		};
    	})
	};
	// 倒计时 分 秒
	function timer(obj,num,callback){
		var shu=0;
		var timer=setInterval(function(){
		var ss=Math.floor(num/60);
			num--;
			if(num<=0){
				clearInterval(timer);
				callback && callback();
			}
			shu=num%60;
			obj.html(ss+"分"+shu+"秒");
		},1000);
	};
	// 点击编辑元素内容
	function clickEdit(obj,cb){
		obj.click(function(){
			$(this).css("position","relative");
			if($(this).find("input").length>0){
				$(this).remove("input");
			}else{
				$(this).append("<input style='position:absolute; left:0; top:0; width:100%; height:100%; border: none;'>");
			};
			var val=$(this).contents().text();
			$(this).find("input").val(val);
			$(this).find("input").focus();
			//失去焦点时调用
			$(this).find("input").blur(function(){
				//改变this指向
				cb && cb.call($(this).parent(),$(this).val());
				$text=$(this).parent().contents().text();
				if($(this).val() != $text){
					$(this).parent().html($(this).val());
				}else{
					$(this).parent().html($text);
					$(this).parent().remove("input");
				};
				obj.css("position","");
			});
		});
	};
