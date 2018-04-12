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
<style>
#serverI tr td {border:1px solid #9E9E9E;}
</style>
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
               {field:"carNum",title:"车牌号"},
               {field:"qxName",title:"车医生姓名"},
               {field:"czName",title:"车主姓名"},
               {field:"mobile",title:"车主手机"},
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



/**
 * 新增行
 */
 var ios =0;
function addTr(){
	//serverI
	ios = ios+1;
	var tr = '<tr id="tr'+ios+'">'
		+'<td><input type="text" name="project" style="width:100px" /></td>'
		+'<td><input type="text" name="price" style="width:100px" id="price'+ios+'" onchange="onchangeMoney('+ios+')" /></td>'
		+'<td><input type="text" name="workTimeCost" style="width:100px" /></td>'
		+'<td><input type="text" name="commission" id="commission'+ios+'" style="width:100px" /></td>'
		+'<td><input type="text" name="productName" style="width:100px" /></td>'
		+'<td><input type="button" value="删除" onclick="deleteTr('+ios+')" /></td>'
		+'</tr>';
	$("#serverI").append(tr);	
		
}

function deleteTr(is){
	
	var removeObj = document.getElementById("tr"+is);
	$("#tr"+is).remove();
	onchangeMoney(111);
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

<div id="addcys" style="position:fixed;z-index:1050;width:720px;height:550px;top:30px;left:20%;background-color:#F8F8FF;border-style:solid;

border-width:2px;border-color:4169E1;display:none">
<div style="padding-top:10px;text-align:center">
	<h3 id="usertitle" onclick="showAdd()"> 确认完成</h3>
</div>
<form action="" id="addfrom">
<input type="text" name="id"  id="id" style="display:none" placeholder="">
 <table style="width:700px;text-align:center;border-collapse:separate; border-spacing:0px 10px;">
	<tr>
		<td style="width:150px">车医生姓名</td>
		<td style="width:200px;text-align:left"><span id="cysName"></span></td>
		<td style="width:150px">服务类型</td>
		<td style="width:200px;text-align:left"><span id="serviceType"></span></td>
	</tr>
	<tr>
		<td >车主姓名</td>
		<td style="text-align:left"><span id="czName"></span></td>
		<td style="">联系号码</td>
		<td style="text-align:left"><span id="czMobile"></span></td>
	</tr>
	
	<tr>
		<td style="">车型</td>
		<td style="text-align:left"><span id="carType"></span></td>
		<td style="">车牌号</td>
		<td style="text-align:left"><span id="carNum"></span></td>
	</tr>
	
	<tr>
		<td style="">进场里程(公里)</td>
		<td style="text-align:left"><input type="text" class="form-control" id="yl1" name="yl1" placeholder="进场里程" style="width:100px" ></td>
		<td style="">下次保养里程(公里)</td>
		<td style="text-align:left"><input type="text" class="form-control" id="yl2" name="yl2" placeholder="下次保养里程" style="width:100px" ></td>
	</tr>
	
	<tr>
		<td>配件类型</td>
		<td style="text-align:left">
			<select name="yl3" id="yl3">
				<option value="原厂件">原厂件</option>
				<option value="品牌件">品牌件</option>
				<option value="副厂件">副厂件</option>
				<option value="拆车件">拆车件</option>
				<option value="其他">其他</option>
			</select>
			
		</td>
		<td > 提成比例 </td>
		<td ><input type="text" class="form-control" id="tcrate" name="tcrate"  style="width:100px" value=0.03></td>
	</tr>
	
	<tr>
		
		<td colspan="4" style="text-align:center">
			 <table id="serverI" style="width:690px;">
				<tr>
				<td>项目(必输)</td>
				<td>单价(元)</td>
				<td>工时费(元)</td>
				<td>提成(元)</td>
				<td>具体产品</td>
				<td><input type="button" value="新增"  onclick="addTr()" /></td>
				</tr>
				<tr>
				<td><input type="text" name="project"  style="width:100px" /></td>
				<td><input type="text" name="price"  id="price0" onchange="onchangeMoney(0)" style="width:100px" /></td>
				<td><input type="text" name="workTimeCost"  style="width:100px" /></td>
				<td><input type="text" name="commission" id="commission0"  style="width:100px" /></td>
				<td><input type="text" name="productName"  style="width:100px" /></td>
				<td></td>
				</tr>
			</table> 
		</td>
	</tr>  
	
	<tr>
		<td style="">折扣</td>
		<td style="text-align:left"><input type="text" class="form-control" id="zhekou" name="zhekou" onchange="onchangeMoney(111)" placeholder="折扣" style="width:100px" value=1></td>
		<td style="">总金额</td>
		<td style="text-align:left"><input type="text" class="form-control" id="shouldPayMoney" name="shouldPayMoney" placeholder="总金额" style="width:100px" ></td>
	</tr>
	<!-- <tr>
		<td>服务总费用</td>
		<td style="text-align:left"><input id="serviceMoney"  type="text" name="serviceMoney" class="input"  /></td>
	</tr> -->
	
	
	
<tr>
		<td colspan="4" >
			<input class="btn" style="width:100px" value="提     交" id="tijiao" onclick="submitP()"/>
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
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;	
	var cysName = $("#cusTable").bootstrapTable('getSelections')[0].qxName;	
	var czName = $("#cusTable").bootstrapTable('getSelections')[0].czName;	
	var mobile = $("#cusTable").bootstrapTable('getSelections')[0].mobile;	
	var type = $("#cusTable").bootstrapTable('getSelections')[0].type;	
	var carNum =$("#cusTable").bootstrapTable('getSelections')[0].carNum;
	$("#cysName").html(cysName);
	$("#czName").html(czName);
	$("#czMobile").html(mobile);
	$("#serviceType").html(type);
	$("#carNum").html(carNum);
	$("#addcys").show();
	getCarByByNum(carNum);
	
}
function hideAdd(){
	$("#addcys").hide();
	$("#tijiao").show();
	//$("#addfrom").resetForm();
	/* $("#serviceDesc").val("");  
	$("#serviceMoney").val("");   */
	clearf();
}

function clearf(){
	$("#cysName").html("");
	$("#czName").html("");
	$("#czMobile").html("");
	$("#serviceType").html("");
	$("#carType").html("");
	$("#carNum").html("");
	$("input[name='project']").val("");  
	$("input[name='price']").val("") ;
	$("input[name='workTimeCost']").val("") ;
	$("input[name='commission']").val("") ;
	$("input[name='productName']").val("") ;
}


function getCarByByNum(num){
	
	$.ajax({
	    url:"getCarByByNum",
	    type:"GET", //GET
	    //contentType: "application/json",
	    async:true,    //或false,是否异步
	    data:{"carNum":num},
	    dataType:'json',   //返回的数据格式：json/xml/html/script/jsonp/text
	    
	    beforeSend:function(xhr){
	        console.log(xhr);
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	//alert(data.carInfo.id);
	    	if(data.carInfo.id !== null&& data.carInfo.id !== undefined){
	    		$("#carType").html(data.carInfo.carType);
	    	}
	    	
	        console.log(data);
	        console.log(textStatus);
	        console.log(jqXHR);
	    },
	    error:function(xhr,textStatus){
	        console.log('错误')
	        console.log(xhr)
	        console.log(textStatus)
	        alert("网络异常");
	    },
	    complete:function(){
	        console.log('结束');
	    }
	})
}


function submitP(){
	
	var t01 = $("#serverI tr").length;
	var project = document.getElementsByName("project");
	var price = document.getElementsByName("price");
	var workTimeCost = document.getElementsByName("workTimeCost");
	var commission = document.getElementsByName("commission");
	var productName = document.getElementsByName("productName");
	var org_id = $("#cusTable").bootstrapTable('getSelections')[0].id;	
	var lk;
	var mycars=new Array(t01-1)
	for(var i=1;i<t01;i++){
		if(project[i-1]!=""){
			var obj = new ServiceP(project[i-1].value,price[i-1].value,workTimeCost[i-1].value,commission[i-1].value,productName[i-1].value);
			mycars[i-1]=obj;
			//lk[i-1]=obj;
		}
	}
	//alert(mycars[0].project);
	var yl1 = $("#yl1").val();
	var yl2 = $("#yl2").val();
	var yl3 = $("#yl3").val();
	var param = {"yl1":yl1,"yl2":yl2,"yl3":yl3,"yl4":$("#zhekou").val(),"serverProject":mycars,"shouldPayMoney":$("#shouldPayMoney").val(),"sysYuyue":{"id":org_id}};
	param1 = JSON.stringify( param );
	$.ajax({
	    url:"save",
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:param1,
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    contentType: "application/json",
	    beforeSend:function(xhr){
	        console.log(xhr)
	        console.log('发送前')
	    },
	    success:function(data,textStatus,jqXHR){
	    	initTable();  hideAdd();
	    	
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


function ServiceP(project,price,workTimeCost,commission,productName){ 
	this.project = project; 
	this.price = price; 
	this.workTimeCost = workTimeCost; 
	this.commission = commission; 
	this.productName = productName; 
} 

function onchangeMoney(count){
	var tmoney =0;
	var rate = $("#zhekou").val();
	var price = document.getElementsByName("price");
	
	
	if(count !=111){
		var mm = $("#price"+count).val();
		var ty= $("#tcrate").val();
		$("#commission"+count).val((mm*ty).toFixed(2));
		
	}
	
	for(var i=0;i<price.length;i++){
		if(price[i]!=""){
			
				tmoney = tmoney+parseFloat(price[i].value);
		    
		    	
		    
		}
	}
	
	tmoney = tmoney*rate;
	$("#shouldPayMoney").val(tmoney.toFixed(2));
}


  
</script>