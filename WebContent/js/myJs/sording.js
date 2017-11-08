$(function(){ 
$('th').click(function(){ 
var date1=(new Date()).getTime() 
var dataType=$(this).attr('dataType'); 
var index=$('th').index(this)+1; 
var arr=[]; 
var row=$('tbody tr'); 
$.each(row,function(i){ arr[i]=row[i] }) 
if($(this).hasClass('select')){ 
arr.reverse() 
} 
else { 
arr.sort(sortStr(index,dataType)) 
$('.select').removeClass(); 
$('td:nth-child('+index+')').addClass('select'); 
$(this).addClass('select') 
} 
var fragment=document.createDocumentFragment() 
$.each(arr,function(i){ 
fragment.appendChild(arr[i]) 
}) 
// $('tbody').remove(); 
//$('table').append('<tbody></tbody>') 
//Each(arr,function(o){fragment.appendChild(o)}) 
$('tbody').append(fragment) 
var date2=(new Date()).getTime() 
$('th span').text(date2-date1) 
}) 
})
function sortStr(index,dataType){ 
return function(a,b){ 
var aText=$(a).find('td:nth-child('+index+')').text(); 
var bText=$(b).find('td:nth-child('+index+')').text();
if(dataType!='roomType'){ 
aText=Parse(aText,dataType) 
bText=Parse(bText,dataType) 
return aText>bText?-1:bText>aText?1:0; 
} 
else return aText.localeCompare(bText) 
} 
} 
function Parse(data,dataType){ 
switch(dataType){ 
case 'num': 
return parseFloat(data)||0 
case 'date': 
return Date.parse(data)||0 
default : 
return splitStr(data) 
} 
} 
function splitStr(data){ 
var re=/^[\$a-zA-z\u4e00-\u9fa5 ]*(.*?)[a-zA-z\u4e00-\u9fa5 ]*$/ 
data=data.replace(re,'$1') 
return parseFloat(data) 
}