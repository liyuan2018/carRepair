<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css"> <!--初始化文件-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css"> <!--初始化文件-->
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
      <input type="text" id="inputEmail" placeholder="请输入账号">
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
      <button type="submit" class="btn">登录</button>
    </div>
  </div>
</form>
</div>
</body>
</html>