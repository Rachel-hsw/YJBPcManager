// JavaScript Document
/*
author 任力维 date 2011/05/31 
引用了一部分老外的代码
facebook 输入框效果
*/
(function ($) {

    $.fn.tokenInput = function (url, options) {
        //options = options || {};
        var settings = $.extend({
            url: url,  //数据源
            showName: "name", // 显示的内容
            saveId: "id",     // 储存的id
            imgSrc: "src",    // 显示的图片
            input_of: 0,
            hintText: "请输入...",
            noResultsText: "找不到与你输入内容相匹配的数据",
            searchingText: "加载中...",
            selectWho: 1,   //选择哪种方式
            selectWidth: "400px",       /*总宽度*/
            selectWidthInput: "100px",	/*input宽度*/
            searchDelay: 300,   /*下拉框弹出时间*/
            prePopulate: null, //已有数据
            haveDate :null,
            minChars: 1,      /*输入几个字符开始匹配,出现下拉框*/
            tokenLimit: null,   //可以选择多少项！null时为无限制
            jsonContainer: null, /*是否需要强制改变选中项的内容*/
            method: "GET",    /*ajax 调用传值返回方式*/
            contentType: "json",  /*请求返回格式*/
            queryParam: "schoolName", /*ajax 调用提交参数名*/
            onResult: null,   /*是否需要强制改变下拉框的内容*/
            shitSipangzi: null, /*第2种方式点击显示详细信息传值参数(显示框名)*/
            fuckSipangzi: null	/*第2种方式点击显示详细信息传值参数(显示区域详细内容,json字符串形式传递)*/
        }, options);
        //是做程序的都看得懂，这里是样式
        settings.classes = $.extend({
            tokenList: "token-input-list",
            token: "token-input-token",
            tokenDelete: "token-input-delete-token",
            selectedToken: "token-input-selected-token",
            highlightedToken: "token-input-highlighted-token",
            dropdown: "token-input-dropdown",
            dropdownItem: "token-input-dropdown-item",
            dropdownItem2: "token-input-dropdown-item2",
            selectedDropdownItem: "token-input-selected-dropdown-item",
            inputToken: "token-input-input-token"
        }, options.classes);

        return this.each(function () {
				//debugger;
            var list = new $.TokenList(this, settings);
        });
    };

    $.TokenList = function (input, settings) {
		//debugger;

        // 显示位置先定义 "enum"
        var POSITION = {
            BEFORE: 0,
            AFTER: 1,
            END: 2
        };

        // 按键 "enum"
        var KEY = {
            BACKSPACE: 8,
            TAB: 9,
            RETURN: 13,
            ESC: 27,
            LEFT: 37,
            UP: 38,
            RIGHT: 39,
            DOWN: 40,
            COMMA: 188
        };

        // 存储列表的数组
        var saved_tokens = [];

		/*记录选中条数*/
        var token_count = 0;

        // 创建一个存储
        var cache = new $.TokenList.Cache();

        // 过期时间
        var timeout;
        // debugger;
        // 创建一个新的文本输入框，就是我们的输入框(原先的输入框变隐藏了)

        var input_box = $("<input type=\"text\">")
        .css({
            outline: "none",
            width: settings.selectWidthInput
        })
        .focus(function () {
           //debugger;
            if (settings.tokenLimit == null || settings.tokenLimit != token_count) {
                show_dropdown_hint();
            }
        })
        .blur(function () {
        	//debugger;
            hide_dropdown();
            if(settings.input_of == 1 && input_box.val().length>0){
            	
               /* var callback = function(bl){
                	var id_string = "";
                	var this_token;
                	if(bl!=""){
                		bl = eval(bl);
                		this_token = insert_token_1(bl[0].userId,input_box.val(),bl[0].icon ,"ren");
                		id_string = id_string + bl[0].userId + ","
                		hidden_input.val(hidden_input.val()+id_string);
                	}else{
                		this_token = insert_token_1("ren",input_box.val(),"" ,"ren");
 
                		this_token.get(0).firstChild.style.color = "red";
                		id_string = id_string + "ren" + ","
                		hidden_input.val(hidden_input.val()+id_string);
                		
                	}
                	input_box.val("");
                	token_count += 1;
                	if (settings.tokenLimit != null && settings.tokenLimit <= token_count) {
                        input_box.parent().hide();
                        hide_dropdown();
                    }
                	//alert(hidden_input.val());
                };
                var act = '{"username": "' + input_box.val() + '" };';
                eval('p= ' + act);
                alert(settings.url+"?query="+input_box.val());
            	$.post(settings.url+"?query="+input_box.val(), p, callback, "text");*/
            	    	
            	
            }
        })
        .keydown(function (event) {
            // 定义快捷键
            var previous_token;
            var next_token;
            switch (event.keyCode) {
                case KEY.LEFT:
                case KEY.RIGHT:
                case KEY.UP:
                case KEY.DOWN:
                    if (!$(this).val()) {
                        previous_token = input_token.prev();
                        next_token = input_token.next();

                        if ((previous_token.length && previous_token.get(0) === selected_token) || (next_token.length && next_token.get(0) === selected_token)) {
                            //debugger;
                            //检查是否有前一个/下一个标记，它被选中
                            if (event.keyCode == KEY.LEFT || event.keyCode == KEY.UP) {

                                deselect_token($(selected_token), POSITION.BEFORE);
                            } else {

                                deselect_token($(selected_token), POSITION.AFTER);
                            }
                        } else if ((event.keyCode == KEY.LEFT || event.keyCode == KEY.UP) && previous_token.length) {

                            select_token($(previous_token.get(0)));
                        } else if ((event.keyCode == KEY.RIGHT || event.keyCode == KEY.DOWN) && next_token.length) {

                            select_token($(next_token.get(0)));
                        }
                    } else {
                        var dropdown_item = null;

                        if (event.keyCode == KEY.DOWN || event.keyCode == KEY.RIGHT) {
                            dropdown_item = $(selected_dropdown_item).next();
                        } else {
                            dropdown_item = $(selected_dropdown_item).prev();
                        }

                        if (dropdown_item.length) {
                            select_dropdown_item(dropdown_item);
                        }
                        return false;
                    }
                    break;

                case KEY.BACKSPACE:
					//debugger;
					if(settings.selectWho == 2){
						previous_token = input_token.next();
					}else{
                    	previous_token = input_token.prev();
					}
                    if (!$(this).val().length) {
                        if (selected_token) {
                            delete_token($(selected_token));
                        } else if (previous_token.length) {
                            select_token($(previous_token.get(0)));
                        }

                        return false;
                    } else if ($(this).val().length >1 ) {
                    	setTimeout(function () { do_search(false); }, 5);
                        
                    } else {
                    	hide_dropdown();
                    }
                    break;

                case KEY.TAB:
                case KEY.RETURN:
                case KEY.COMMA:
                    if (selected_dropdown_item) {
                        add_token($(selected_dropdown_item));
                        return false;
                    }
                    break;

                case KEY.ESC:
                    hide_dropdown();
                    return true;

                default:
                	//debugger;
                    if (is_printable_character(event.keyCode)) {
             
                    		setTimeout(function () { do_search(false); }, 5);
                    }
                    else {
          
                    		setTimeout(function () { do_search(false); }, 5);
                    }
                    break;
            }
        });

        // 建立隐藏的文本框对象,这个是用来存储我选中项的id的,方便以后提交到服务端储存.(数组形式)
        var hidden_input = $(input)
                           .hide()
                           .focus(function () {
                               input_box.focus();
                           })
                           .blur(function () {
                               input_box.blur();
                           });

        // 鼠标选中 
        var selected_token = null;
        var selected_dropdown_item = null;

        // 选中列表,(包括文本框)
        var token_list = $("<ul />")
        .addClass(settings.classes.tokenList)
        .css("width", settings.selectWidth)
        .insertAfter(hidden_input)
        .click(function (event) {
            //debugger;
            var li = get_element_from_event(event, "li");
            if (li && li.get(0) != input_token.get(0)) {
                toggle_select_token(li);
                return false;
            } else {
                input_box.focus();

                if (selected_token) {
                    deselect_token($(selected_token), POSITION.END);
                }
            }
        })
        .mouseover(function (event) {
            var li = get_element_from_event(event, "li");
            if (li && selected_token !== this) {
                li.addClass(settings.classes.highlightedToken);
            }
        })
        .mouseout(function (event) {
            var li = get_element_from_event(event, "li");
            if (li && selected_token !== this) {
                li.removeClass(settings.classes.highlightedToken);
            }
        })
        .mousedown(function (event) {

            var li = get_element_from_event(event, "li");
            if (li) {
                return false;
            }
        });


        // 创建下拉列表<div>
        var dropdown = $("<div>")
        .addClass(settings.classes.dropdown)
        .css("width", settings.selectWidth)
        .insertAfter(token_list)
        .hide();

        // 创建输入框<li>
        var input_token = $("<li/>")
        .addClass(settings.classes.inputToken)
        .appendTo(token_list)
        .append(input_box);
        hidden_input.val(""); //清空隐藏文本框
		
		// 建立一个隐藏标签,用于辅助重绘选中项列表的显示顺序

        /*var this_token_first = $("<li id='fffwdfff'></li>")

        .addClass("xk_token-facebook")
        .insertBefore(input_token);*/
		
		
		
        init_list();
        
        //debugger;
       /* if(settings.input_of == 1){
        	$("li[id^='friend_name_']").click(function(){
    			//$("#friend_name_1").click(function(){	
    	    		//debugger;
    	    		var token_names = get_all_token_names();
    	            var this_token = null;
    	            if ($.inArray($(this).text(), token_names) == -1) {
    	            	if(token_names.length<3){
	    	    			this_token = insert_token_1($(this).next().text(),$(this).text(),$(this).next().next().next().text() ,"ren");
	    	    			var id_string = "";
	        	    		id_string = id_string + $(this).next().text() + ","
	        	    		hidden_input.val(hidden_input.val()+id_string);
	        	    		input_box.val("");
	                    	token_count += 1;
	                    	if (settings.tokenLimit != null && settings.tokenLimit <= token_count) {
	                            input_box.parent().hide();
	                            hide_dropdown();
	                        }
    	            	}else{
    	            		this_token = find_token_by_name(token_names[1]);
    	            		delete_token(this_token);
    	            		this_token = insert_token_1($(this).next().text(),$(this).text(),$(this).next().next().next().text() ,"ren");
	    	    			var id_string = "";
	        	    		id_string = id_string + $(this).next().text() + ","
	        	    		hidden_input.val(hidden_input.val()+id_string);
	        	    		input_box.val("");
	                    	token_count += 1;
	                    	if (settings.tokenLimit != null && settings.tokenLimit <= token_count) {
	                            input_box.parent().hide();
	                            hide_dropdown();
	                        }
    	            	}
    	            }else{
    	            	this_token = find_token_by_name($(this).text());
    	                select_token(this_token);
    	            }
    	    		
                	//alert(hidden_input.val());
    	    	});
        }*/



        //
        // 函数
        //  



        // 从服务端返回数据,写到文本框中.
        function init_list() {
            // debugger;
            li_data = settings.prePopulate;
            if (li_data != "") {

                if (settings.contentType == "text") {
                    li_data = eval(li_data);
                }
                var id_string = "";
                if (li_data && li_data.length) {
                    for (var i = 0; i < li_data.length; i++) {

                        var this_token;
                        switch (settings.selectWho) {
                            case 1:
                                this_token = insert_token_1(eval("li_data[" + i + "]." + settings.saveId), eval("li_data[" + i + "]." + settings.showName), eval("li_data[" + i + "]." + settings.imgSrc));
                                break;
                            case 2:
                                this_token = insert_token_2(eval("li_data[" + i + "]." + settings.saveId), eval("li_data[" + i + "]." + settings.showName), eval("li_data[" + i + "]." + settings.imgSrc));
                                break;
                            case 3:
                                this_token = insert_token_3(eval("li_data[" + i + "]." + settings.saveId), eval("li_data[" + i + "]." + settings.showName), eval("li_data[" + i + "]." + settings.imgSrc));
                                break;
                            default:
                                break;
                        }
                        //$.data(this_token.get(0), "tokeninput", li_data);
                        //cache.add(eval("li_data[" + i + "]." + settings.saveId), li_data[i]);
                        // 请空文本框选中框
                        input_box.val("");

                        // 隐藏下拉框
                        hide_dropdown();
                        //debugger;
                        // 把id存储到隐藏文本框中
                        id_string = id_string + eval("li_data[" + i + "]." + settings.saveId) + ","
                        token_count++;
                    }
                    if (settings.tokenLimit != null && settings.tokenLimit <= token_count) {
                        input_box.parent().hide();
                        hide_dropdown();
                    }
                    cache.add("token_list", li_data);
                    hidden_input.val(id_string);
                }
            }
        }

        function is_printable_character(keycode) {
            if ((keycode >= 48 && keycode <= 90) ||      // 0-1a-z
           (keycode >= 96 && keycode <= 111) ||     // numpad 0-9 + - / * .
           (keycode >= 186 && keycode <= 192) ||    // ; = , - . / ^
           (keycode >= 219 && keycode <= 222)       // ( \ ) '
          ) {
                return true;
            } else {
                return false;
            }
        }

        // 获得一份特定类型的元素事件（点击/鼠标悬停等）
        function get_element_from_event(event, element_type) {
            var target = $(event.target);
            var element = null;
            //debugger;
            if (target.is(element_type)) {
                element = target;
            } else if (target.parent(element_type).length) {
                element = target.parent(element_type + ":first");
            }

            return element;
        }


        /* 建立一个隐藏标签,用于辅助选中项的显示顺序(选中项在最后一个)
        var this_token_last = $("<li></li>")
        .addClass("xk_token-facebook")
        .insertAfter(input_token);*/
        // 添加选中项第1种效果
        function insert_token_1(id, value, src, query) {


            //画出选中项显示标签
            var this_token = $("<li title='" + value + "'><p>" + highlight_term(value, query) + "</p> </li>")
                .addClass(settings.classes.token)
                .insertBefore(input_token);

            // 添加删除方法
            $("<span>x</span>")
            //.addClass(settings.classes.tokenDelete)
                .appendTo(this_token)
                .click(function () {
                    delete_token($(this).parent());
                    return false;
                });

            // 将我输入的条件传入显示框,方便删除时缓存控制。
            $("<p style='display:none;' id='cache_query' title='" + query + "'></p>")
                .appendTo(this_token);

            var act = '{ "' + settings.saveId + '": "' + id +
					  '","' + settings.showName + '": "' + value +
					  '","' + settings.imgSrc + '": "' + src + '"}'
            eval('p= ' + act);

            this_token.get(0).id = id;
            $.data(this_token.get(0), "tokeninput", p);
            return this_token;
        }



        // 添加选中项第2种效果
        function insert_token_2(id, value, src, query) {
            // debugger;
            var objString;
            var iscn = isChinese(value.substring(0, 6));
            if (iscn) {
                if (value.length > 5) {
                    objString = value.substring(0, 4) + "...";
                } else {
                    objString = value;
                }
            } else {
                if (value.length > 8) {
                    objString = value.substring(0, 6) + "...";
                } else {
                    objString = value;
                }
            }
            //画出选中项显示标签
            var this_token = $("<li title='" + value + "'><p style='float:left;'>" + objString + "</p></li>")
                .addClass(settings.classes.token)
                .insertAfter(input_token);
            // 添加删除方法
            $("<span style='float:right;'>x</span>")
            //.addClass(settings.classes.tokenDelete)
                .appendTo(this_token)
                .click(function () {
                    delete_token($(this).parent());
                    return false;
                });

            // 图片显示
            $("<p><img src='" + src + "'></p>")
                    .addClass("xk_token-facebook_img")
                    .appendTo(this_token);

            // 将我输入的条件传入显示框,方便删除时缓存控制。
            $("<p style='display:none;' id='cache_query' title='" + query + "'></p>")
                .appendTo(this_token);
            this_token.children().height(15);
            // if (iscn == false) {//因为中文和英文的高度不一样,所以英文的高度要加２.
            //                // debugger;
            //                this_token.children().height(this_token.children().height() + 2);
            //				alert(this_token.children().height());
            //            }
            hide_second_img();
            //debugger;
            input_box.parent().addClass("token-input-input-token-facebook-dashed-2"); //加虚线
            var act = '{ "' + settings.saveId + '": "' + id +
					  '","' + settings.showName + '": "' + value +
					  '","' + settings.imgSrc + '": "' + src + '"}'
            eval('p= ' + act);

            this_token.get(0).id = id;
            //debugger;
            $.data(this_token.get(0), "tokeninput", p);
            //debugger;

            return this_token;
        }
        //控制第2种显示方式的列表图片显示方式(第2行不显示图片)
        function hide_second_img() {

            token_list.children("li").each(function (i, item) {
                //debugger;
                if (i > 1 && i < 7) {
                    $(item).children(".xk_token-facebook_img").show();
                }
                if (i > 6) {
                    $(item).children(".xk_token-facebook_img").hide();
                }


            });

        }
        //第三种显示方式,带选择框.
        function insert_token_3(id, value, src, query) {
            // debugger;
            var objString;
            var iscn = isChinese(value);
            //            if (iscn) {
            //                if (value.length > 5) {
            //                    objString = value.substring(0, 4) + "...";
            //                } else {
            //                    objString = value;
            //                }
            //            } else {
            //                if (value.length > 8) {
            //                    objString = value.substring(0, 6) + "...";
            //                } else {
            //                    objString = value;
            //                }
            //            }
            //画出选中项显示标签
            var this_token = $("<li title='" + value + "'><p>" + highlight_term(value, query) + "</p></li>")
                .addClass(settings.classes.token)
                .insertBefore(input_token);
            //debugger;
            // 添加复选框
            $("<p style = 'display:block;height:12px;'><input id='Checkbox1' style = 'width:14px;float:left;cursor: pointer; background-color: #eff2f7;' value='" + value + "' checked='true' type='checkbox' /></p>")
                .insertBefore(this_token.children("p"))
                .change(function () {
                    // debugger;
                    var id_string = id + ",";
                    if (this.children[0].checked) {
                        //alert($(this.children).val());
                        if (hidden_input.val().indexOf(id_string) == -1)
                            hidden_input.val(hidden_input.val() + id_string);
                        //alert(hidden_input.val());
                    } else {
                        //                        var str = hidden_input.val();
                        //                        str = str.replace(id_string, "");
                        //                        hidden_input.val(str);
                        hidden_input.val(hidden_input.val().replace(id_string, ""));
                        //alert(hidden_input.val());
                    }
                });

            // 添加删除方法
            $("<span style='float:right;'>x</span>")
            //.addClass(settings.classes.tokenDelete)
                .appendTo(this_token)
                .click(function () {
                    delete_token($(this).parent());
                    return false;
                });


            // 将我输入的条件传入显示框,方便删除时缓存控制。
            $("<p style='display:none;' id='cache_query' title='" + query + "'></p>")
                .appendTo(this_token);
            this_token.children().height(15);
            // if (iscn == false) {//因为中文和英文的高度不一样,所以英文的高度要加２.
            // debugger;
            // this_token.children().height(this_token.children().height() + 2);
            //  }

            //debugger;
            //input_box.parent().addClass("token-input-input-token-facebook-dashed-2"); //加虚线

            var act = '{ "' + settings.saveId + '": "' + id +
					  '","' + settings.showName + '": "' + value +
					  '","' + settings.imgSrc + '": "' + src + '"}'
            eval('p= ' + act);

            this_token.get(0).id = id;
            $.data(this_token.get(0), "tokeninput", p);
            //debugger;

            return this_token;
        }



        // 获得所有li标签title内容
        function get_all_token_names() {
            var tokens = [];
            token_list.children("li").each(function () {
                tokens.push($(this).attr("title"));
            });
            return tokens;
        }
        // 根据name 找到选中项
        function find_token_by_name(token_name) {
            var this_token = null;
            token_list.children("li").each(function () {
                if ($(this).attr("title") == token_name) {
                    this_token = $(this);
                }
            });
            return this_token;
        }
        // 添加一个选中项到当前列表中
        function add_token(item) {
        	//debugger;
            var token_names = get_all_token_names();
            var li_data = $.data(item.get(0), "tokeninput");
            var this_token = null;
            if ($.inArray(eval("li_data." + settings.showName), token_names) == -1) {
                //debugger;
                switch (settings.selectWho) {
                    case 1:
                        this_token = insert_token_1(eval("li_data." + settings.saveId), eval("li_data." + settings.showName), eval("li_data." + settings.imgSrc), input_box.val());
                        break;
                    case 2:
                        this_token = insert_token_2(eval("li_data." + settings.saveId), eval("li_data." + settings.showName), eval("li_data." + settings.imgSrc), input_box.val());
                        break;
                    case 3:
                        this_token = insert_token_3(eval("li_data." + settings.saveId), eval("li_data." + settings.showName), eval("li_data." + settings.imgSrc), input_box.val());
                        break;
                    default:
                        break;
                }
                // 选中项的记数
                token_count+=1;
                // 存储选中内容到隐藏文本框
                var id_string = eval("li_data." + settings.saveId) + ","
                hidden_input.val(hidden_input.val() + id_string);
            } else {
                this_token = find_token_by_name(eval("li_data." + settings.showName));
                select_token(this_token);
            }

            // 隐藏下拉框
            hide_dropdown();

            
            //alert(hidden_input.val());

            // 选中后从当前缓存中清除已经选中的项
            //debugger;
            var results_filtering = cache.get(input_box.val());
            for (var i in results_filtering) {
                if (results_filtering.hasOwnProperty(i)) {
                    if (eval("li_data." + settings.saveId) == eval("results_filtering[" + i + "]." + settings.saveId)) {
                        //alert(hide_id[j]);
                        //debugger;
                        results_filtering.splice(i, 1);
                        i = i - 1;
                    }
                }
            }
            cache.add(input_box.val(), results_filtering);

            //建立选中列表项缓存
            if ($.inArray(eval("li_data." + settings.showName), token_names) == -1) {
                var tk_list = cache.get("token_list");
                var zzz_array = new Array();
                if (tk_list && tk_list.length) {
                    zzz_array = zzz_array.concat(tk_list, li_data);
                    cache.add("token_list", zzz_array);
                } else {
                    cache.add("token_list", li_data);
                }
            }
            // 选中后删除当前文本框输入内容,让文本框得到光标
            input_box
            .val("");

            // 是否超过选择上限
            //debugger;
            if(settings.tokenLimit != null && settings.tokenLimit <= token_count) {
                input_box.parent().hide();
                hide_dropdown();
            }
            if(settings.tokenLimit == null||(settings.tokenLimit != null && settings.tokenLimit >= token_count)){
            	 input_box
                 .focus();
            }
            //alert(hidden_input.val());
        }

        // 选中某列表项
        function select_token(token) {
			//debugger;
            token.addClass(settings.classes.selectedToken);
            selected_token = token.get(0);
            //
            // 清空文本框
            // input_box.val("");
            if (settings.selectWho == 2) {
                var danteng;
                var tk_list = cache.get("token_list");
                if (tk_list.length) {
                    for (var i in tk_list) {
                        if (tk_list.hasOwnProperty(i)) {
                            if (eval("tk_list[" + i + "]." + settings.saveId) == selected_token.id) {
                                danteng = tk_list[i];
                            }
                        }
                    }
                } else {
                    danteng = tk_list;
                }
                 //debugger;
				if(danteng){
                	var label_block = settings.shitSipangzi;
					if (label_block) {
						$(label_block).css("display", "block");
						$(label_block).get(0).title = eval("danteng." + settings.saveId);
					}
					var labels = settings.fuckSipangzi;
					if (labels && labels.length) {
						for (var i in labels) {
							if (labels.hasOwnProperty(i)) {
								var labels_class = labels[i].label_class;
								switch (labels_class) {
									case "src":
										$(labels[i].label_id).get(0).src = eval("danteng." + labels[i].label_text);
										break;
									case "innerText":
										$(labels[i].label_id).get(0).innerText = eval("danteng." + labels[i].label_text);
										break;
									default:
										break;
								}
							}
						}
					}
				}
            }

            // 隐藏下拉框
            hide_dropdown();
        }

        //取消选中
        function deselect_token(token, position) {
            token.removeClass(settings.classes.selectedToken);
            selected_token = null;

            //            debugger;
            //                        if (position == POSITION.BEFORE) {
            //                            if (settings.selectWho == 1)
            //                                input_token.insertBefore(token);
            //                            else
            //                                this_token_first.insertAfter(token_list);
            //                        } else if (position == POSITION.AFTER) {
            //                            if (settings.selectWho == 1)
            //                            //input_token.insertAfter(token);
            //                                input_token.appendTo(token_list);
            //                            else
            //                                this_token_first.insertAfter(token_list);
            //                        } else {
            //                            if (settings.selectWho == 1) {//在这里需要注意，做通用的时候要改进，测试先
            //                                input_token.appendTo(token_list);
            //                            } else {
            //                                this_token_first.insertAfter(token_list);
            //                            }
            //                        }

            // 让文本框重新获得光标
            input_box.focus();
        }

        //重复选中选中列表
        function toggle_select_token(token) {

            if (selected_token == token.get(0)) {
                deselect_token(token, POSITION.END);
            } else {
                if (selected_token) {
                    deselect_token($(selected_token), POSITION.END);
                }
                select_token(token);
            }
        }
        //   删除数组中第一个匹配的元素，成功则返回位置索引，失败则返回   -1。 
        Array.prototype.deleteElementByValue = function (varElement) {
            // debugger;
            var numDeleteIndex = -1;
            for (var i = 0; i < this.length; i++) {
                //   严格比较，即类型与数值必须同时相等。 
                if (eval("this[" + i + "]." + settings.saveId) == eval("varElement." + settings.saveId)) {
                    this.splice(i, 1);
                    numDeleteIndex = i;
                    break;
                }
            }
            return numDeleteIndex;
        }

        // 删除选中项
        function delete_token(token) {
        	var c_query = token.children("#cache_query").attr("title");
        	//if(settings.input_of == 0){
            // 清除缓存数据
		
        		var token_data = $.data(token.get(0), "tokeninput");
				
            //当删除选中项时,我们要把相应项添加到缓存中.
        		
        		var cached_results = cache.get(c_query); // 取缓存
        		var zzz_array = new Array();
        		if(cached_results && token_data){
        			zzz_array = zzz_array.concat(cached_results, token_data);

        			cached_results = zzz_array;

        			cache.add(c_query, cached_results);
        		}
        		//debugger;
        		//删除选中列表项缓存中的删除项
        	//}
        	//debugger;
			if(c_query){
				var tk_list = cache.get("token_list");
				if (tk_list && tk_list.length ) {
					tk_list.deleteElementByValue(token_data);
	
					cache.add("token_list", tk_list);
				} else {
	
					cache.add("token_list", "");
	
				}
			


				// 清除选中项
				token.remove();
				selected_token = null;
	
	
				//if(settings.input_of == 0){
				// 删除隐藏文本框中相应项

					var str = hidden_input.val()
					var start = str.indexOf(eval("token_data." + settings.saveId) + ",");
					var end = str.indexOf(",", start) + 1;
					if (start != -1) {
						if (end >= str.length) {
							hidden_input.val(str.slice(0, start));
						} else {
							hidden_input.val(str.slice(0, start) + str.slice(end, str.length));
						}
					}
				//hidden_input.val().replace(eval("token_data." + settings.saveId) + ",", "")
				//alert(hidden_input.val());
					hide_second_img();
				//}
				token_count--;
				
				if (settings.tokenLimit != null) {
					input_box
					.show()
					.parent().show()
					.val("")
					.focus();
				} else {
					// 文本框得到光标
					input_box.focus();
				}
				// debugger;
				if (token_list.children().length < 3 && (settings.selectWho == 2))
					input_box.parent().removeClass("token-input-input-token-facebook-dashed-2");
	
				//debugger;
				if (settings.shitSipangzi && settings.selectWho == 2 && $(settings.shitSipangzi).get(0).style.display == "block") {
					if (settings.shitSipangzi && $(settings.shitSipangzi).get(0).title == eval("token_data." + settings.saveId)) {
						$(settings.shitSipangzi).css("display", "none");
					}
				}
			}
        }

        // 显示各种状态
        function hide_dropdown() {
            dropdown.hide().empty();
            selected_dropdown_item = null;
        }

        function show_dropdown_searching() {
            dropdown
            .html("<p>" + settings.searchingText + "</p>")
            .show();
        }

        function show_dropdown_hint() {
            dropdown
            .html("<p>" + settings.hintText + "</p>")
            .show();
        }

        // 匹配关键字
        function highlight_term(value, term) {
            return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + term + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<b>$1</b>");
        }

        // 画出选择列表
        function populate_dropdown(query, results) {
            if (results == "") {
                dropdown
                    .html("<p>" + settings.noResultsText + "</p>")
                    .show();
                return;
            }
            if (results.length) {
                dropdown.empty();
                var dropdown_ul = $("<ul>")
                    .appendTo(dropdown)
                    .mouseover(function (event) {
                        select_dropdown_item(get_element_from_event(event, "li"));
                    })
                    .mousedown(function (event) {
                        //debugger;
                        add_token(get_element_from_event(event, "li"));
                        return false;
                    })
                    .hide();


                var hide_id = hidden_input.val().split(",");
                // var results_filtering = results
                // var data_tokens = new Array();
                for (var i in results) {
                    //debugger;

                    if (results.hasOwnProperty(i)) {
                        //去除已选项
                        //                        for (var j = 0; j < hide_id.length; j++) {
                        //                            //debugger;

                        //                            if (hide_id[j] == eval("results[" + i + "]." + settings.saveId)) {
                        //                                //alert(hide_id[j]);
                        //                                // debugger;
                        //                                //$.data(this_li.get(0), "tokeninput", results[i]);
                        //                              //  data_tokens = data_tokens.concat(results[i]) ;
                        //                                results.splice(i, 1);
                        //                            }
                        //                        }
                        if (i < results.length) {
                            var this_li = $("<li>" + highlight_term(eval("results[" + i + "]." + settings.showName), query) + "</li>")
                                        .appendTo(dropdown_ul);

                            if (i % 2) {
                                this_li.addClass(settings.classes.dropdownItem);
                            } else {
                                this_li.addClass(settings.classes.dropdownItem2);
                            }

                            if (i == 0) {
                                select_dropdown_item(this_li);
                            }

                        }

                        //                        var act = '{ "' + settings.saveId + '": "' + eval("results[" + i + "]." + settings.saveId) +
                        //						   		'","' + settings.showName + '": "' + eval("results[" + i + "]." + settings.showName) +
                        //						        '","' + settings.imgSrc + '": "' + eval("results[" + i + "]." + settings.imgSrc) + '"}'
                        //                        eval('p= ' + act);
                        $.data(this_li.get(0), "tokeninput", results[i]);




                    }
                }
                //                if (data_tokens != null) {
                //                    var tokens = cache.get(query);
                //                    var zzz_array = new Array();
                //                    zzz_array = zzz_array.concat(tokens, data_tokens);
                //                    cache.add(query, zzz_array);
                //                }

                dropdown.show();
                dropdown_ul.slideDown("fast");

            } else {
                // var tk_list = cache.get("token_list");

                dropdown
                    .html("<p>" + settings.noResultsText + "</p>")
                    .show();
            }
        }

        // 选中下拉内容
        function select_dropdown_item(item) {
            if (item) {
                if (selected_dropdown_item) {
                    deselect_dropdown_item($(selected_dropdown_item));
                }

                item.addClass(settings.classes.selectedDropdownItem);
                selected_dropdown_item = item.get(0);
            }
        }

        // 取消选中下拉内容
        function deselect_dropdown_item(item) {
            item.removeClass(settings.classes.selectedDropdownItem);
            selected_dropdown_item = null;
        }

        // 搜索数据,准备弹出下拉框
        function do_search(immediate) {
            //  debugger;
            var query = input_box.val().toLowerCase();

            if (query && query.length) {
                if (selected_token) {
                    deselect_token($(selected_token), POSITION.AFTER);
                }
                if (query.length >= settings.minChars) {
                    show_dropdown_searching();
                    if (immediate) {
                        run_search(query);
                    } else {
                        clearTimeout(timeout);
                        timeout = setTimeout(function () { run_search(query); }, settings.searchDelay);
                    }
                } else {
                    hide_dropdown();
                }
            }
        }

        // 匹配数据(下拉框弹出前)
        function run_search(query) {
           // debugger;
        	//alert(settings.haveDate);
            var cached_results;
            //if (is_ascii(input_box.val())) {
            cached_results = cache.get(input_box.val()); // 取缓存
            //cached_results = cache.get(input_box.val().toUpperCase());
            //} else {
            // cached_results = cache.get(input_box.val());
            //}

            // debugger;
            //var wdas = results;
            if (cached_results) {  //判断是否有缓存
                // debugger;
                populate_dropdown(query, cached_results);
            } else {
                var queryStringDelimiter = settings.url.indexOf("?") < 0 ? "?" : "&";
              // debugger;
                if(settings.haveDate){
                	//alert(settings.haveDate);
                	var haveDate_list = eval(settings.haveDate);
                	if(haveDate_list&&haveDate_list.length>0){
                		var results_name_0;
                        for (var i = 0; i < haveDate_list.length; i++) {
                        	results_name_0 = eval("haveDate_list[" + i + "]." + settings.showName).toLowerCase();
                            if (results_name_0.indexOf(input_box.val().toLowerCase()) == -1) {//
                                //if (results[i].name.indexOf(input_box.val()) == -1) {
                            	haveDate_list.splice(i, 1);
                                i = i - 1;
                            }
                        }
                		cache.add(input_box.val(), settings.jsonContainer ? haveDate_list[settings.jsonContainer] : haveDate_list); //生成缓存
                		populate_dropdown(query, settings.jsonContainer ? haveDate_list[settings.jsonContainer] : haveDate_list);
                	}
                }else{
	                var callback = function (results) {
	                    //debugger;
	                    if (results != "") {
	                        if (settings.contentType == "text") {
	                            results = eval(results);
	                        }
	
	                        if ($.isFunction(settings.onResult)) {
	
	                            results = settings.onResult.call(this, results);
	                        }
	                        //debugger;
	                        var results_name;
	                        for (var i = 0; i < results.length; i++) {
	                            results_name = eval("results[" + i + "]." + settings.showName).toLowerCase();
	                            if (results_name.indexOf(input_box.val().toLowerCase()) == -1) {//
	                                //if (results[i].name.indexOf(input_box.val()) == -1) {
	                                results.splice(i, 1);
	                                i = i - 1;
	                            }
	                        }
	
	                        cache.add(input_box.val(), settings.jsonContainer ? results[settings.jsonContainer] : results); //生成缓存
	                    }
	
	                    populate_dropdown(query, settings.jsonContainer ? results[settings.jsonContainer] : results);
	                };
	
	                var queryParam_ = settings.queryParam;
	                var act = '{"' + queryParam_ + '": "' + query + '" };';
	
	                eval('p= ' + act);
	
	                if (settings.method == "POST") {
	                	//sendSync("/general_1/_ext/rcap/getContent.jsp?date=" + SY+"-" + (SM+1) + "-" + (sD+1) + "&type=day");
	                    $.post(settings.url, p, callback, settings.contentType);
	                } else {
	
	                    $.get(settings.url, p, callback, settings.contentType);
	
	                }
                }
            }
        }

        function is_ascii(str) {
            var min = '\u0000';
            var max = '\u007f';
            var t, t2, r = [];
            var pad = [];
            for (var i = 0; i < str.length; i++) {
                pad = [];
                t2 = t = str.charCodeAt(i).toString(16);

                for (var j = 0; j < 4 - t.length; j++) { pad.push(0); }
                t = "\\u" + pad.join("") + "" + t + "";

                if (str.charAt(i) >= min && str.charAt(i) <= max) {
                    r.push([str.charAt(i), t2]);
                }
            }
            if (r.length > 0) {
                return true;
            }
            else {
                return false;
            }

            //            var item;
            //            for(var i = 0; i< r.length; i++) {
            //                item = r[i];
            //                alert("字符：" + item[0] + "；ASCII码：" + item[1]);
            //            }

        }

        /* 
        判断是否包含中文
        */
        function isChinese(str) {
            //debugger
            var pattern = /[u4e00-u9fa5]/;
            var badChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            badChar += "abcdefghijklmnopqrstuvwxyz";
            badChar += "0123456789";
            badChar += " " + "　"; //半角与全角空格
            badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/"; //不包含*或.的英文符号
            if ("" == str) {
                return false;
            }

            var teg = false;
            for (var i = 0; i < str.length; i++) {
                var c = str.charAt(i); //字符串str中的字符
                flag = pattern.exec(c);
                //if(badChar.indexOf(c) > -1){
                //return false;
                //}
                if (c == " " || c == "　") {
                    teg = false;
                } else {
                    if (!flag) {
                        teg = true; //包含中文返回true
                    }
                }
            }
            return teg;
        }




    };




    // 缓存处理
    $.TokenList.Cache = function (options) {
        var settings = $.extend({
            max_size: 50
        }, options);

        var data = {};
        var size = 0;

        var flush = function () {
            data = {};
            size = 0;
        };

        this.add = function (query, results) {
            // debugger;
            if (size > settings.max_size) {
                flush();
            }

            if (!data[query]) {
                size+=1;
            }

            data[query] = results;
        };

        this.get = function (query) {
            return data[query];
        };
    };
    
})(jQuery);