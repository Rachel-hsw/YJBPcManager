
function key_press(a){
					a.keypress(function(e) {
				  var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
				
				   if (keyCode == 13) // 判断所按是否回车键 FireFox下事件的keyCode 是只读的，不能修改
				   {
				    var inputs = a; // 获取表单中的所有输入框
				    var idx = inputs.index(this); // 获取当前焦点输入框所处的位置
				
				    if (idx == inputs.length - 1) // 判断是否是最后一个输入框
				    {
				     return false;// 取消默认的提交行为
				    } else {
				     inputs[idx + 1].focus(); // 设置焦点
				     inputs[idx + 1].select(); // 选中
				    }
				    return false;// 取消默认的提交行为
				   }
				  });
				}
