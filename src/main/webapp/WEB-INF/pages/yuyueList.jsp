<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"> <!--初始化文件--><!--初始化文件-->
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>  
<!--rem适配js-->  
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>  --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script> 


<script type="text/javascript">      


function initTable() {  
  //先销毁表格  
  $('#cusTable').bootstrapTable('destroy');  
  //初始化表格,动态从服务器加载数据  
 
  $("#cusTable").bootstrapTable({  
      method: "get",  //使用get请求到服务器获取数据  
      url: "user", //获取数据的Servlet地址  
      striped: true,  //表格显示条纹  
      pagination: true, //启动分页  
      pageSize: 10,  //每页显示的记录数  
      pageNumber:1, //当前第几页  
      showToggle:false,
      sidePagination:'server',
      pageList: [5, 10, 15, 20, 25],  //记录数可选列表  
      search: false,  //是否启用查询  
      showColumns: false,  //显示下拉框勾选要显示的列  
      showRefresh: false,  //显示刷新按钮  
      sidePagination: "server", //表示服务端请求  
      //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
      //设置为limit可以获取limit, offset, search, sort, order  
      queryParamsType : "undefined",   
      queryParams: function queryParams(params) {   //设置查询参数  
        var param = {    
            page: params.pageNumber-1,    
            limit: params.pageSize,  
           
            
        };    
        return param;                   
      },  
      columns:[
               {checkbox:true, checked : false},
               {field:"id", title:"id",visible:false},
               {field:"type",title:"预约类型"},
               {field:"qxName",title:"车医生姓名"},
               {field:"czName",title:"车主姓名"},
               {field:"yyTime",title:"预约时间"},
               {field:"status",title:"状态"}	
               ],
      onLoadSuccess: function(){  //加载成功时执行  
        //alert("加载成功");  
      },  
      onLoadError: function(){  //加载失败时执行  
    	  alert("加载数据失败");  
      }  
    });  
}  

$(document).ready(function () {          
    //调用函数，初始化表格  
    initTable();  

    //当点击查询按钮的时候执行  
    $("#search").bind("click", initTable);  
});  


function fghh(type){
	
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;		
	$.ajax({
	    url:org_id,
	    type:'GET', //GET
	    async:true,    //或false,是否异步
	    data:{
	        "id":org_id
	    },
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	
	    	var fg = data.sysUser;
	    	
	    	if(fg.name){
	    		loadData(fg);
	    		if(type=="update"){
	    			userUpdate();
	    		}else if(type=="view"){
	    			userVier();
	    		}
	    		
	    	}else {
	    		alert("数据查询失败");
	    	}
	        console.log(data)
	        console.log(textStatus)
	        console.log(jqXHR)
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	        alert("网络异常");
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
}

</script>  
</head>
<body>
<div style="padding:10px">
<h4>预约管理</h2>

<div>
	<form class="form-inline">
  <label>姓名</label><input type="text" name="aname" id="aname" class="input" placeholder="姓名">
  <label>手机号码</label><input type="text" class="input" id="amobile" name ="amobile" placeholder="手机号码">
  
  <input class="btn" value="查找" style="width:50px" onclick="initTable()" />
</form>
</div>
<div>
	<div class="btn-group">
	<a  class="btn" role="button" onclick="showAdd()" >确认完成</a>
  
  <!-- <button class="btn" onclick="deleteData()">删除</button>
  <button class="btn" onclick="fghh('update')">修改</button>
  <button class="btn" onclick="fghh('view')">查看</button> -->
</div>
</div>
<table class="table table-hover" id="cusTable"  
       data-pagination="true"  
       data-show-refresh="true"  
       data-show-toggle="true"  
       data-showColumns="true">  
       
</table>  
<div>

<div id="addcys" style="position:fixed;z-index:1050;width:600px;height:550px;top:30px;left:20%;background-color:#F8F8FF;border-style:solid;

border-width:2px;border-color:4169E1;display:none">
<div style="padding-top:10px;text-align:center">
	<h3 id="usertitle"> 确认完成</h3>
</div>
<form action="" id="addfrom">
<input type="text" name="id"  id="id" style="display:none" placeholder="姓名">
<!-- <table style="width:100%;text-align:center">
	<tr>
		<td style="width:30%">车医生姓名</td>
		<td style="width:70%;text-align:left"><span id="cysName"></span></td>
	</tr>
	<tr>
		<td>服务类型</td>
		<td style="text-align:left"><span id="serviceTpye"></span></td>
	</tr>
	
	<tr>
		<td>服务介绍</td>
		<td style="text-align:left"><input id="serviceDesc" name="serviceDesc" /></td>
	</tr>
	
	<tr>
		<td>服务费用</td>
		<td style="text-align:left"><input id="serviceMoney" name="serviceMoney" /></td>
	</tr>
	
	
	
	<tr>
		<td colspan="2">
			<input class="btn" style="width:100px" value="提     交" id="tijiao" onclick="addOrUpdate()"/>
			<input class="btn" style="width:100px" value="取    消" onclick="hideAdd()"/>
		</td>
		
  

	</tr>
	
</table> -->
</form>
</div>

</body>
</html>


<script>
function showAdd(){
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;	
	var cysName = $("#cusTable").bootstrapTable('getSelections')[0].qxName;	
	var type = $("#cusTable").bootstrapTable('getSelections')[0].type;	
	$("#cysName").html(cysName);
	$("#serviceTpye").html(type);
	
	$("#addcys").show();
}
function hideAdd(){
	$("#addcys").hide();
	$("#tijiao").show();
	//$("#addfrom").resetForm();
	$("#serviceDesc").val("");  
	$("#serviceMoney").val("");  
	
}


function userVier(){
	$("#usertitle").val("车医生信息");
	$("#addcys").show();
	$("#tijiao").hide();
	
}

function userUpdate(){
	$("#usertitle").val("修改车医生信息");
	$("#addcys").show();
	$("#tijiao").show();
}

function deleteData(){
	
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;		
	$.ajax({
	    url:"deleteUser",
	    type:'GET', //GET
	    async:true,    //或false,是否异步
	    data:{"id":org_id},
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    contentType: "application/json",
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	initTable();
	    	
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	        alert("网络异常");
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
}


/** 
 * 设置select控件选中 
 * @param selectId select的id值 
 * @param checkValue 选中option的值 
 * @author 标哥 
*/  
function set_select_checked(selectId, checkValue){  
    var select = document.getElementById(selectId);  

    for (var i = 0; i < select.options.length; i++){  
        if (select.options[i].value == checkValue){  
            select.options[i].selected = true;  
            break;  
        }  
    }  
}

function loadData(user){
	if(user.name){
		$("#name").val(user.name);
		$("#id").val(user.id);
	}
	if(user.mobile){
		$("#mobile").val(user.mobile);
	}
	if(user.description){
		$("#description").val(user.description);
	}
	
	set_select_checked("canYuyue",user.canYuyue);
	set_select_checked("typeDby",user.typeDby);
	set_select_checked("typeXby",user.typeXby);
	set_select_checked("typeMr",user.typeMr);
	set_select_checked("typeJc",user.typeJc);
	set_select_checked("typeWx",user.typeWx);
}

function addOrUpdate(){
	var param1;
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;		
	param1 = {"id":org_id,"serviceDesc":$("#serviceDesc").val(),"serviceMoney":$("#serviceMoney").val()
	    	
	    }
	//param1 = JSON.stringify( param1 );
	$.ajax({
	    url:"save",
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:param1,
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	   
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	initTable();  hideAdd();
	    	/* var fg = data.sysUser;
	    	
	    	if(fg.id){
	    		
	    		initTable();  hideAdd();
	    	}else {
	    		alert("数据查询失败");
	    	} */
	        console.log(data)
	        console.log(textStatus)
	        console.log(jqXHR)
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	        alert("网络异常");
	    },
	    complete:function(){
	        console.log('结束')
	    }
	})
}



</script>