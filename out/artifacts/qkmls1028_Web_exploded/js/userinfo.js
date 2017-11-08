$(document).ready(function(){
	
});



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

