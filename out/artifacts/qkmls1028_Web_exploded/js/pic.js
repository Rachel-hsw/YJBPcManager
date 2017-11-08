$(document).ready(function(){
	
});


var s_url = "http://localhost:8080/wxsh_interface";
//去掉字符串空格
function jtrimstr(str) {
	var i = 0;
	var j;
	var len = str.length;
	trimstr = "";
	while (i < len) {
		if (str.charAt(i) != " ") {
			trimstr = trimstr + str.charAt(i);
		}
		i++;
	}
	return (trimstr);
}


//上传图片
function uploadPic(key,s){
	$.ajaxFileUpload({  
        url:'http://localhost:8080/wxsh_interface/SystemConntro/upload/5.do',  
        secureuri:false,  
        fileElementId:key,//file标签的id  
        dataType: 'json',//返回数据的类型  
        success: function (data, status) {
        	if("1" == s){//修改类别图片
        		$("#typePic1").val(jtrimstr(data));
        		$("#shopPic").attr("src",jtrimstr(s_url+data));
        	}
        	else if("2" == s){
        		$("#typePic2").val(jtrimstr(data));
        		$("#shopPic1").attr("src",jtrimstr(s_url+data));
        	}
        },  
        error: function (data, status, e) {  
            alert("上传失败，请重新上传" + e);
        }  
    });  
}