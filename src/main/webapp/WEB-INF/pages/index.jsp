<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>系统管理</title>

<script src="<%=request.getContextPath()%>/js/adapter.js"></script> <!--rem适配js-->
<link rel="<%=request.getContextPath()%>/stylesheet" href="/bootstrap/css/bootstrap.css"> <!--初始化文件-->
<link rel="<%=request.getContextPath()%>/stylesheet" href="/bootstrap/css/bootstrap-responsive.css"> <!--初始化文件-->
<%-- <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>  --%><!--rem适配js-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css"> <!--初始化文件-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css"> <!--主样式-->

</head>
<body>
<div style="width:1260px">
<div style="background-color:#D6D6D6;width:1260px;margin-right:50px;height:70px;text-align:right;padding-top:30px">
   欢迎<%=session.getAttribute("user_name") %>使用系统&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">退出系统</a>
</div>
<div id="menu">
    <!--隐藏菜单-->
    <div id="ensconce">
        <h2>
            <img src="images/show.png" alt="">
            系统管理
        </h2>
    </div>

    <!--显示菜单-->
    <div id="open">
        <div class="navH">
            系统管理
            <span><img class="obscure" src="images/obscure.png" alt=""></span>
        </div>
        <div class="navBox">
            <ul>
                <li>
                    <h2 class="obtain">车医生管理<i></i></h2>
                    <div class="secondary" onclick="chooseM('table')">
                        <h3 >车医生管理</h3>
                        
                    </div>
                </li>
                <li>
                    <h2 class="obtain">预约管理<i></i></h2>
                    <div class="secondary" onclick="chooseM('yuyue/table')">
                        <h3 >预约单管理</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">系统管理<i></i></h2>
                    <div class="secondary">
                        <h3>个人信息修改</h3>
                       
                    </div>
                </li>
               
            </ul>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/menu.js"></script> <!--控制js-->
<div style="text-align:center;">



<iframe id="iFrame1" name="iFrame1" width="950px" height="735px" frameborder="0" src="table"></iframe>  
</div>
</body>
</html>
<<script type="text/javascript">
<!--

//-->

function chooseM(url){
	
	var ifm= document.getElementById("iFrame1");
	
	ifm.src=url;
}
</script>


<script>
/* var ifm= document.getElementById("iFrame1");
alert(document.documentElement.clientHeight);
ifm.height=document.documentElement.clientHeight+"px";
alert(ifm.height); */
</script>