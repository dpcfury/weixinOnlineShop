<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/order.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
<title>支付成功页面</title>
<style type="text/css">
    body{
         background-image: url("<%=request.getContextPath()%>/resources/images/dust.png");
          font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
    font-size: 14px;
    line-height: 1.42857143;"
    }
</style>
</head>
<body
	style="font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；; font-size: 14px; line-height: 1.42857143;">
	<div class="container-fluid">
		<div class="row" style="margin-top: 20px;">
			<div class="col-xs-12">
				<div class="text-center">
					<img src="<%=request.getContextPath()%>/resources/images/accept.png" width="130">
				</div>
				<div class="text-center">
					<h2>支付成功</h2>
				</div>
			</div>
		</div>
		<hr>
	
		  <div class="row">
            <div class="col-xs-12">
                <div class="text-center">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>交易成功!</strong> 我们讲尽快安排相应的配送.<br/>
             				           感谢您对水立方水业的支持
                    </div>
                </div>
            </div>
        </div>
	
		<div class="row">
			<div class="col-xs-12">
				<div class="text-center">
					<div class="pb20 pt10 ph10" style="padding-bottom:10px;">
                        <button class="btn btn-bg-blue btn-lg" id="continue_order">点击继续订水</button>
                    </div>
				</div>
				
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="text-center">
				<div class="pb20 pt10 ph10">
                        <button class="btn btn-bg-blue btn-lg" id="query_order">查询以往订单</button>
                    </div>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
	<script>
		$(function (){
			$("#continue_order").click(function (){
				location.replace("<%=request.getContextPath()%>/order/begin");
			});
			$("#query_order").click(function (){
				location.replace("<%=request.getContextPath()%>/order/toQuery");
			});
		});
	</script>
	
</body>
</html>