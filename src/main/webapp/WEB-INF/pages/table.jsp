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
            userType:2,
            shopId:1,
            name:$("#aname").val(),
            mobile:$("#amobile").val()
        };    
        return param;                   
      },  
      columns:[
               {checkbox:true},
               {field:"name",title:"姓名"},
               {field:"mobile",title:"手机"},
               {field:"canYuyue",title:"是否可预约"},
               {field:"createTime",title:"创建时间"},
              
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
</script>  
</head>
<body>
<div style="padding:10px">
<h4>用户管理</h2>

<div>
	<form class="form-inline">
  <label>姓名</label><input type="text" name="aname" id="aname" class="input" placeholder="姓名">
  <label>手机号码</label><input type="text" class="input" id="amobile" name ="amobile" placeholder="手机号码">
  
  <input class="btn" value="查找" style="width:50px" onclick="initTable()" />
</form>
</div>
<div>
	<div class="btn-group">
	<a  class="btn" role="button" onclick="showAdd()" >新增</a>
  
  <button class="btn">删除</button>
  <button class="btn">修改</button>
  <button class="btn">查看</button>
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
	<h3> 新增车医生</h3>
</div>
<form action="" id="addfrom"></form>
<table style="width:100%;text-align:center">
	<tr>
		<td style="width:30%">姓名</td>
		<td style="width:70%;text-align:left"><input type="text" name="name"  id="name" class="input" placeholder="姓名"></td>
	</tr>
	<tr>
		<td>手机号码</td>
		<td style="text-align:left"><input type="text" name="mobile" id="mobile"  class="input" placeholder="手机号码"></td>
	</tr>
	
	<tr>
		<td>是否可预约</td>
		<td style="text-align:left"><select>
  <option value="1">可预约</option>
  <option value="0">不可预约</option>
  
</select></td>
	</tr>
	
	<tr>
		<td>是否可预约</td>
		<td style="text-align:left"><select>
  <option value="1">可预约</option>
  <option value="0">不可预约</option>
  
</select></td>
	</tr>
	<tr>
		<td>大保养</td>
		<td style="text-align:left"><select>
  <option value="1">有</option>
  <option value="0">没有</option>
  
</select></td>
	</tr>
	<tr>
		<td>小保养</td>
		<td style="text-align:left"><select>
  <option value="1">有</option>
  <option value="0">没有</option>
  
</select></td>
	</tr>
	<tr>
		<td>汽车美容</td>
		<td style="text-align:left"><select>
  <option value="1">有</option>
  <option value="0">没有</option>
  
</select></td>
	</tr>
	<tr>
		<td>汽车检查</td>
		<td style="text-align:left"><select>
  <option value="1">有</option>
  <option value="0">没有</option>
  
</select></td>
	</tr>
	<tr>
		<td>修车</td>
		<td style="text-align:left"><select>
  <option value="1">有</option>
  <option value="0">没有</option>
  
</select></td>
	</tr>
	<tr>
		<td>简介</td>
		<td style="text-align:left"><input type="text" name="name"  class="input-big" placeholder="">
  
</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input class="btn" style="width:100px" value="提     交"/>
			<input class="btn" style="width:100px" value="取    消" onclick="hideAdd()"/>
		</td>
		
  

	</tr>
	
</table>
</form>
</div>

</body>
</html>


<script>
function showAdd(){
	$("#addcys").show();
}
function hideAdd(){
	$("#addcys").hide();
	//$("#addfrom").resetForm();
	$("#name").val("");  
	$("#mobile").val("");  
}
</script>