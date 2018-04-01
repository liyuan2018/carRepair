<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css"> <!--初始化文件-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css"> <!--初始化文件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>  
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script> <!--rem适配js-->



</head>
<body>
<div style="padding-top:300px;text-align:center;width:400px;padding-left:30%">
<div style="text-align:center;width:400px;padding-left:50px"">
	<h2>用户登录</h2>
</div>

<form class="form-horizontal" style="padding-top:50px">
  <div class="control-group">
    <label class="control-label" for="text">账号</label>
    <div class="controls">
      <input type="text" id="account" placeholder="请输入账号">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">密码</label>
    <div class="controls">
      <input type="password" id="inputPassword" placeholder="请输入密码">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <!-- <label class="checkbox">
        <input type="checkbox"> Remember me
      </label> -->
      <input onclick="login()" class="btn" style="width:100px" value="登录" />
    </div>
  </div>
</form>
</div>
</body>
</html>


<script>
	function login(){
		var account = $("#account").val();
		if(account == ""){
			alert("账号不能为空");return;
		}
		var inputPassword = $("#inputPassword").val();
		if(inputPassword == ""){
			alert("密码不能为空");return;
		}
		
		$.ajax({
		    url:'login/checkuser',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		        "account":account,"password":inputPassword
		    },
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		    	
		    	
	
		    	fg = data.message;
		    	
		    	if(fg.status=="success"){
		    		location.href="admin/index"
		    	}else {
		    		alert(fg.msg);
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