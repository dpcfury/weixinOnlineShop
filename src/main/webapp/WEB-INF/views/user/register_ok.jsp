<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
<title>注册成功提示页面</title>
</head>
<body style="font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
    font-size: 14px;
    line-height: 1.42857143;">
	<div class="container-fluid" style="position:fixed;width:100%;top:50%;margin-top:-25px;">        
        <div class="text-center ">
            <h3 style="margin:0;line-height:50px;">注册成功！</h3>
        </div>
        <div class="text-center">
            <span id="show" class=""></span> 
        </div>   
    </div>
    <script type="text/javascript">
    var t = 2; //设定跳转的时间 
    setInterval("refer()", 1000); //启动1秒定时 
    function refer() {
        if (t == 0) {
            location = "/slf_online/order/begin"; //#设定跳转的链接地址 
        }
        document.getElementById('show').innerHTML = "" + t + "秒后跳转到选购界面"; // 显示倒计时 
        t--; // 计数器递减 
        //本文转自： 
    }
    </script>
</body>
</html>