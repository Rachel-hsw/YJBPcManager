<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>manager/customerquery.jsp</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
    <link rel="stylesheet" href="/qkmls/css/details.css">
	<link rel="stylesheet" href="/qkmls/css/datecss/jquery-ui-1.9.2.custom.css" type="text/css"> <!-- 日历插件样式 -->
	<script type="text/javascript" src="/qkmls/layer/layer.js"></script>
	<script src="/qkmls/laydate/laydate.js"></script>
    <style>
        .table-hover{
            background: #FFF7EA;
        }
        .table-hover>tbody>tr:hover {
            background-color: #FFDCA9;
        }
        .glyphicon-plus:before {
		    content: "\002b";
		    color: #ED5C14;
		}
		.yearMouth{
			margin-left:10px;
		}
    </style>
    <script type="text/javascript">
		function changePage(obj){
			if((!/^[0-9]\d*$/.test(obj.value))){
				layer.msg("必须是正整数!");
				obj.value=${pageBean.currentPage}
				return;
			}else if(obj.value<=0 || (obj.value>(${pageBean.totalPage})) ){
				layer.msg("页码必须在有效范围内!");
				obj.value=${pageBean.currentPage}
				return;
			}else{
				window.location.href="/qkmls/Manager/queryCustomerNumber.do?currentPage="+obj.value+"&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}";
			}
		}
	</script>
<script>
	$(function(){
		$("#execl").click(function(){
			var month = $(".month").val(),
				month1 = $(".month1").val();
			if(month != "" && month1 != ""){
				document.getElementById("search-tj").action="/qkmls/Manager/execlCustomerNumber.do"
				$("#search-tj").submit();
			}else{
				layer.msg("日期不能为空！");
			}
		});
	})
</script>
<style>
		ul.pagination li a{
		color:#000 !important;
		border-color:#d7a372;
	}
	ul.pagination > li.active a{
		background-color:#fff !important;
		border-color:#d7a372 !important;
	}
	.pagination>.disabled>a, .pagination>.disabled>a:focus, .pagination>.disabled>a:hover, .pagination>.disabled>span, .pagination>.disabled>span:focus, .pagination>.disabled>span:hover {
		border-color: #d7a372;
	}
	button, input, select, textarea {
	    font-family: inherit;
	    font-size: inherit;
	    line-height: 0;
	}
	tr th,tr td{text-align:center;}
	tr th{letter-spacing:4px;}
</style>
</head>
<body>
	<div class="place">
			<a href="#"><span>位置：</span></a>
			<a href="#">首页</a>
			<a href="#"><span> > </span>财务统计</a>
			<a href="#"><span> > </span>客户报表</a>
	</div>
<!-- 查询统计 -->
    <div>
        <h1>商品采购进货</h1>  
        <form id="search-tj" action="/qkmls/Manager/queryCustomerNumber.do">
	        <div id="d-head" class="print-clone">
	            <div>
	              <span>入库日期:</span>
	                <input type="text" class="month date scinput1" name="kssj" id="test" value="${condition.kssj}" placeholder="请选择日期"> 
				  	<input type="button"  class="btn color btn-small yearMouth" value="今天"/>
				  	<input type="button"  class="btn color btn-small yearMouth" value="昨天"/>
				  	<input type="button"  class="btn color btn-small yearMouth" value="本月"/>
				  	<input type="button"  class="btn color btn-small yearMouth" value="上月"/>
				  	<input type="button"  class="btn color btn-small yearMouth" value="当年"/>
				
			
	            </div>
	            <div>
	                <span>客户名称:</span>
	                <%-- <input class="reset1" type="hidden" name="id" value="${condition.id }"> --%>
	                <input class="reset1" type="hidden" name="id" value="${condition.id }" id="customerID">
	                <input class="reset1  scinput1" type="text" name="" value="${condition.name}" id="customerName" disabled="disabled">
	                <input class="reset1" type="hidden" name="name" value="${condition.name}"  id="customerName1" >
	                <button type="button" id="name-select" class="btn color btn-small" data-toggle="modal" data-target="#myModal">
					  选择
					</button>
	            </div>
	             <div style="display:inline-block;margin-left:20px;">
                <span>区域:</span>
                <select name="Area" class="select1 scinput1" style="margin-left:0px;">
                <option></option>
                 <c:forEach items="${arealist}" var="item">
                    <option value="${item}"
                     <c:if test="${condition.area==item }">
               selected='selected'
         </c:if>
                    >${item}</option>
                    </c:forEach>
                </select>
            </div>
	        </div>
		 	<!-- 条件 -->
		        <div class="print-clone">
		        	<label>容器筛选:</label>
		            <label for="r1"><input type="radio" name="tcode"  id="r1" value="1"  class="tcode" 
		            <c:if test="${condition.tcode=='1' }">
				    checked= 'checked'
				    </c:if>
			            > 小桶</label>
			            <label for="r2"><input type="radio" name="tcode" id="r2" value="2" class="tcode"
			               <c:if test="${condition.tcode=='2' }">
			          checked= 'checked'
			    </c:if>
			             > 大桶</label>
			         
			         
			            <label for="r5"><input type="radio" name="tcode" id="r5" value="4" class="tcode" 
			            <c:if test="${condition.tcode=='4'||empty condition.tcode}">
			          checked= 'checked'
			    </c:if>> 全部</label>
			    		<select name="isShow" class="scinput1">
			    		    <option value="1"
			                     <c:if test="${condition.isShow=='1'}">
			       selected='selected'
			         </c:if>
			                    >显示全部值</option>
			                    	    <option value="0"
			                     <c:if test="${condition.isShow=='0'}">
			       selected='selected'
			         </c:if>
		                    >显示某一项不为0的值</option>
		    		</select>
		    		<button type="button" id="print" class="btn colors" style="float:right; margin-left:20px;" data-toggle="modal" data-target="#myModal1">
					 	<img src="/qkmls/images/t01.png"></img> 打印
					 </button>
		    		<div class="total">
	                <button type="button" id="chaxun" onclick="chaxun12()" class="btn color tabclick">开始查询</button>
						<script>
								function chaxun12(){
									var month = $(".month").val(),
										month1 = $(".month1").val();
									if(month != "" && month1 != ""){
										document.getElementById("search-tj").action="/qkmls/Manager/queryCustomerNumber.do"
										$("#search-tj").submit();
										
									}else{
										layer.msg("月份不能为空！");
									}
								};
						</script>
	            </div>
	            <div class="total">
	                <button type="button" id="execl" class="btn color tabclick">导出EXECL</button>
	            </div>
	            
        	
		        </div> 
    	</form> 
    </div>
        <table  class="table table-hover table-bordered table-striped"style="width:100%; height:100%; margin-top:10px;">
            <thead>
                <tr>
                   <th>日期</th>
                   <th>区域</th>
                   <!-- <th>客户ID</th>  -->
                   <th >客户名称</th>
                   <th >容器</th>
                   <th>期初</th>
                   <th>卸货总数量</th>
                   <th>需回收量</th>
                   <th>无需回收量</th>
                   <th>回收量</th>
                   <th>回收冗余量</th>
                   <th>结存</th>
                </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.pageBean.queryNumberList}" var="item">
                     <tr>
                    <td>${item.date}</td>
                     <td>${item.area}</td>
                    <%-- <td>${item.id}</td>  --%>
                    <td style="text-align:left;"><a href="/qkmls/Order/customerqueryname.do?tcode=${condition.tcode}&id=${item.id}&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${item.name}&date=${item.date}&qc=${item.qc}">${item.name}</a></td>
                     
                          <c:choose>
                     <c:when test="${item.p_id=='1'}"><td>小桶</td></c:when>
                      <c:when test="${item.p_id=='2'}"><td>大桶</td></c:when>
                       <c:when test="${item.p_id=='3'}"><td>油罐</td></c:when>
                        <c:when test="${item.p_id=='4'}"><td>大小桶</td></c:when>
                         <c:when test="${item.p_id=='5'}"><td>全部</td></c:when>
                     </c:choose>
                            
                    <td>${item.qc}</td>
                      <c:choose>
                        <c:when test="${item.xhzsl==0}">
	                    <td>${item.xhzsl}</td>
                        </c:when>
	                    <c:otherwise>
                    <td><a href="/qkmls/Order/single.do?currentPage=${pageBean.currentPage}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}&ordercustomer=${item.name}&code=${2}&type=customer ">${item.xhzsl}</a></td>
                        </c:otherwise>
                    </c:choose>
                       <c:choose>
                        <c:when test="${item.xhhssl==0}">
	                    <td>${item.xhhssl}</td>
                        </c:when>
	                    <c:otherwise>
                    <td><a href="/qkmls/Order/single.do?currentPage=${pageBean.currentPage}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}&ordercustomer=${item.name}&code=${3}&type=customer ">${item.xhhssl}</a></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.xhzsl-item.xhhssl}</td>
                        <c:choose>
                        <c:when test="${item.hssl==0}">
	                    <td>${item.hssl}</td>
                        </c:when>
	                    <c:otherwise>
                    <td><a href="/qkmls/Order/single.do?currentPage=${pageBean.currentPage}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}&ordercustomer=${item.name}&code=${7}&type=customer ">${item.hssl}</a></td>
                        </c:otherwise>
                    </c:choose>
                         <c:choose>
                        <c:when test="${item.hsrysl==0}">
	                    <td>${item.hsrysl}</td>
                        </c:when>
	                    <c:otherwise>
                    <td><a href="/qkmls/Order/single.do?currentPage=${pageBean.currentPage}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}&ordercustomer=${item.name}&code=${8}&type=customer ">${item.hsrysl}</a></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.qm}</td>
                </tr>
             </c:forEach>
			<c:if test="${not empty requestScope.pageBean.queryNumberList}">
			   <tr>
             	<td>当前页合计</td>
             	<td></td>
             	<td></td>
             	  <c:choose>
                     <c:when test="${condition.tcode=='1'}"><td>小桶</td></c:when>
                      <c:when test="${condition.tcode=='2'}"><td>大桶</td></c:when>
                        <c:when test="${condition.tcode=='4'}"><td>大小桶</td></c:when>
                     </c:choose>
             	<td>${qcCurrentsum }</td>
             	<td>${xhzslCurrentsum}</td>
             <td>${xhhsslCurrentsum }</td>
             <td>${xhzslCurrentsum-xhhsslCurrentsum}</td>
             <td>${hsslCurrentsum }</td>
             	<td>${ hsryslCurrentsum}</td>
             	   <td>${qmCurrentsum }</td>
             </tr> 
             <tr>
             	<td>总合计</td>
             	<td></td>
             	<td></td>
             	  <c:choose>
                     <c:when test="${condition.tcode=='1'}"><td>小桶</td></c:when>
                      <c:when test="${condition.tcode=='2'}"><td>大桶</td></c:when>
                        <c:when test="${condition.tcode=='4'}"><td>大小桶</td></c:when>
                         
                     </c:choose>
                    
             	<td>${qcsum }</td>
             	<td>${xhzslsum}</td>
             <td>${xhhsslsum }</td>
             <td>${xhzslsum-xhhsslsum }</td>
             <td>${ hsslsum}</td>
             <td>${hsryslsum }</td>
             <td>${qmsum}</td>
             </tr>
             </c:if>
            </tbody>
        </table>   
    <!-- 上一页 -->
<!-- 判断当前页是否是第一页 -->
	<div style="margin-top: 20px; text-align:center;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
	  <li>    
	         <a>共${pageBean.totalCount }条记录</a>
     <a>共${pageBean.totalPage} 页</a>
 <c:if test="${pageBean.currentPage!=1&&not empty pageBean.totalCount }">
<a href="/qkmls/Manager/queryCustomerNumber.do?currentPage=1&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow} ">首页</a>
	</c:if>
		</li>
       <c:if test="${pageBean.currentPage==1|| empty pageBean.totalCount}">
       <li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
       </c:if>
         <c:if test="${pageBean.currentPage!=1 &&not empty pageBean.totalCount}">
       <li >
					<a href="/qkmls/Manager/queryCustomerNumber.do?currentPage=${pageBean.currentPage-1}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}"  aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
       </c:if>
     <c:if test="${pageBean.totalPage<=5}">
  		<c:set var="begin" value="1" scope="page"></c:set>
  		<c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
  	</c:if>
  	<c:if test="${pageBean.totalPage>5}">
		<c:choose>
			<c:when test="${pageBean.currentPage<=3}">
				<c:set var="begin" value="1" scope="page"></c:set>
  				<c:set var="end" value="5" scope="page"></c:set>
			</c:when>
			<c:when test="${pageBean.currentPage>=pageBean.totalPage-2}">
				<c:set var="begin" value="${pageBean.totalPage-4}" scope="page"></c:set>
  				<c:set var="end" value="${pageBean.totalPage}" scope="page"></c:set>
  			</c:when>
  			<c:otherwise>
  				<c:set var="begin" value="${pageBean.currentPage-2}" scope="page"></c:set>
  				<c:set var="end" value="${pageBean.currentPage+2}" scope="page"></c:set>
  			</c:otherwise>
		</c:choose>
  	</c:if>
  	
  	<c:forEach begin="${begin}" end="${end}" step="1" var="i">
  		<c:if test="${i == pageBean.currentPage}">
  		 <li class="active"><a href="javascript:void(0);">${i}</a></li>
  		</c:if>
  		<c:if test="${i != pageBean.currentPage&&not empty pageBean.totalCount&& pageBean.totalCount!=0}">
     <li ><a href="/qkmls/Manager/queryCustomerNumber.do?currentPage=${i}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}">${i}</a></li>
  		</c:if>
  	</c:forEach>  
   <!-- 判断当前页是否是最后一页 -->
   <c:if test="${pageBean.currentPage==pageBean.totalPage }">
   <li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				
	</c:if>	
	    <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
				
				<li>
					<a href="/qkmls/Manager/queryCustomerNumber.do?currentPage=${pageBean.currentPage+1}&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow}" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
       </c:if>	
 <c:if test="${pageBean.currentPage!=pageBean.totalPage&&not empty pageBean.totalPage }"> 
<li><a href="/qkmls/Manager/queryCustomerNumber.do?currentPage=${pageBean.totalPage }&tcode=${condition.tcode}&id=${condition.id }&area=${condition.area }&kssj=${condition.kssj}&jssj=${condition.jssj}&name=${condition.name}&isShow=${condition.isShow} ">尾页</a></li>
</c:if>
  <li> <a>跳到<input type="text"
value="${pageBean.currentPage }" style="width: 50px; height:17px; outline:none;" onchange="changePage(this)"/>页 
  </a></li>
   <li><a href="javascript:void(0);"style="">跳转</a></li>
   </ul>
</div>
<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		  <div class="modal-dialog" role="document" style="width:60% !important;">
		    <div class="modal-content">
		      <div class="modal-header" style="background:#ffdca9">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加客户</h4>
		      </div>
		      	<div style="overflow:hidden; padding-top:10px;padding-left:10px;">
			      	<lable>客户名称: <input type="text" id="value" class="reset1  scinput1"/>
			      		<button class="btn colors" id="queryCom"> 查询</button>
			      	</lable>
		      	</div>
		      <div class="modal-body" style="height:60vh;overflow:scroll;">
		        <table class="table table-hover table-striped" id="name-table">
		        <tr>
		        	<th>添加</th>
		        	<th>客户ID</th>
		        	<th>客户全称</th>
		        </tr>
		        <tr>
	  				<td>
	  					<button type="button" class="cusromerData btn btn-default btn-lg">
						  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						</button>
	  				</td>
	  				
	  				 <td class="customerID"></td>
	  				 <td class="customerName">全部</td>
	  			</tr>
  			 <c:forEach items="${requestScope.customerlist}" var="item">
  			<tr>
  				<td>
  					<button type="button" class="cusromerData btn btn-default btn-lg">
					  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button>
  				</td>
  				 <td class="customerID">${item.customerID}</td>
  				 <td class="customerName">${item.customerName }</td>
  			</tr>
  			</c:forEach>
		</table>
      		  </div>
		      <div class="modal-footer">
		        <button type="button" id="btn-close" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript"> 
window.parent.document.getElementById("index").rows="*,0";
		    $(function(){
				$(".cusromerData").click(function(){
					var customerID =$(this).parent().siblings(".customerID").text();
					var customerName =$(this).parent().siblings(".customerName").text();
				$("#customerID").val(customerID);
				$("#customerName").val(customerName);
				$("#customerName1").val(customerName);
				$("#btn-close").click();
				});
			})
			//弹出框客户查询
			$("#queryCom").click(function(){
				var val=$("#value").val();
				if(val!=""){
					$(".customerName").each(function(i){
						var html=$(this).html();
						if(!html.match(val)){
							$(this).parent().css("display","none")
						}
					})
				}else{
					layer.msg("客户名称不能为空！");
				}
			})
			$("#name-select").click(function(){
				$("#name-table tbody tr").each(function(){
					$(this).css("display","")
				})
			})
	    </script> 
<!-- Modal -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document"  style="width: 60vw;">
    <div class="modal-content" style="height: 60vh;">
    	<iframe id="print-page2" src="/qkmls/Manager/queryCustomerprint.do?" style="width: 100%;height: 100%;"> 
    		
        </iframe> 
    </div>
  </div>
</div>
<script type="text/javascript">
			$(function(){
				$("#print").click(function(){
					$( "#print-page2" ).contents().find( "#print-form1" ).html("").append($(".print-clone").clone());
					$( "#print-page2" ).contents().find( "#print-form1" ).submit();
				});
			})
			laydate.render({
			  elem: '#test',
			  format:'yyyy-MM-dd'
			});
			laydate.render({
			  elem: '#test1', //指定元素
				format:'yyyy-MM-dd'
			});
			//今天
			$(".yearMouth").eq(0).click(function(){
				var date=new Date(),
					year=date.getFullYear(),
					month=date.getMonth()+1,
					data=date.getDate();
				var D=data+1;
				if(month<=9){
					if(D<=9){
						$("#test1").val(year+"-0"+month+"-0"+D);
					}else{
						$("#test1").val(year+"-0"+month+"-"+D);
					}
					if(data<=9){
						$("#test").val(year+"-0"+month+"-0"+data);
					}else{
						$("#test").val(year+"-0"+month+"-"+data);
					}
					
				}else{
					if(data<=9){
						$("#test").val(year+"-"+month+"-0"+data);
					}else{
						$("#test").val(year+"-"+month+"-"+data);
					}
					if(D<=9){
						$("#test1").val(year+"-"+month+"-0"+D);
					}else{
						$("#test1").val(year+"-"+month+"-"+D);
					}
				}
			})
			//昨天
			$(".yearMouth").eq(1).click(function(){
				var date=new Date(),
					year=date.getFullYear(),
					month=date.getMonth()+1,
					data=date.getDate();
				var D=data-1;
				if(month<=9){
					if(D<=9){
						$("#test").val(year+"-0"+month+"-0"+D);
					}else{
						$("#test").val(year+"-0"+month+"-"+D);
					}
					if(data<=9){
						$("#test1").val(year+"-0"+month+"-0"+data);
					}else{
						$("#test1").val(year+"-0"+month+"-"+data);
					}
					
				}else{
					if(D<=9){
						$("#test").val(year+"-"+month+"-0"+D);
					}else{
						$("#test").val(year+"-"+month+"-"+D);
					}
					if(data<=9){
						$("#test1").val(year+"-"+month+"-0"+data);
					}else{
						$("#test1").val(year+"-"+month+"-"+data);
					}
				}
			})
			//本月
			$(".yearMouth").eq(2).click(function(){
				var date=new Date(),
					year=date.getFullYear(),
					month=date.getMonth()+1,
					data=date.getDate();
				var mon=month-1;
				if(mon<=9){
					$("#test").val(year+"-0"+mon+"-"+23);
				}else{
					$("#test").val(year+"-"+mon+"-"+23);
				}
				
				if(month<=9){
					$("#test1").val(year+"-0"+month+"-"+23);
				}else{
					$("#test1").val(year+"-"+month+"-"+23);
				}
			})
			//上月
			$(".yearMouth").eq(3).click(function(){
				var date=new Date(),
					year=date.getFullYear(),
					month=date.getMonth()+1,
					data=date.getDate();
				var mon=month-2;
				if(mon<=9){
					$("#test").val(year+"-0"+mon+"-"+23);
				}else{
					$("#test").val(year+"-"+mon+"-"+23);
				}
				var mon=month-1;
				if(mon<=9){
					$("#test1").val(year+"-0"+mon+"-"+23);
				}else{
					$("#test1").val(year+"-"+mon+"-"+23);
				}
				
			})
			//当年
			$(".yearMouth").eq(4).click(function(){
				var date=new Date(),
					year=date.getFullYear();
				$("#test").val((year-1)+"-"+12+"-"+23);
				$("#test1").val(year+"-"+12+"-"+23);
			})
			$(function(){

				var date=new Date(),
					year=date.getFullYear(),
					month=date.getMonth()+1,
					data=date.getDate();
				var mon=month-1;
				if(mon<=9){
					$("#test").val(year+"-0"+mon+"-"+23);
				}else{
					$("#test").val(year+"-"+mon+"-"+23);
				}
				if(month<=9){
					$("#test1").val(year+"-0"+month+"-"+23);
				}else{
					$("#test1").val(year+"-"+month+"-"+23);
				}
			})
</script>
</html>