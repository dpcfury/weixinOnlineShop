<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>水立方后台登录页</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/admin_login.css">
</head>
</head>
<body class="login-body">
<div class="container">

      <div class="form-signin" >
        <h2 class="form-signin-heading">水立方水业后台</h2>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="用户名" autofocus="" id="username">
            <input type="password" class="form-control" placeholder="口令" id="password">
          	
            <button class="btn btn-lg btn-login btn-block" id="btn_login">登入</button>
           
        </div>
      </div>

    </div>

    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
    <script>

    $(function (){
    	$("#btn_login").click(function(){
    		var username=$("#username").val();
    		var password=$("#password").val();
    		console.log("username:"+username);
    		$.ajax({
    	        type: "POST",
    	        url: "/slf_online/manage/doLogin",
    	        data:{"userName":username,"password":password},
    	        success: function(data) {
    	        	if(data=="login_ok"){
    	        		alert("登录成功");
        	        	location.replace("<%=request.getContextPath()%>/manage/order/0/15");
    	        	}else{
    	        		alert("用户名或密码不正确");
    	        		$("#username").val("");
    	        		$("#password").val("");
    	        	}
    	        
    	        }
    		});
    	});
    });
    </script>

</body>
</html>