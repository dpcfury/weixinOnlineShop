<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<title>个人基本信息</title>
<style type="text/css">
	body{
		background-image:url("<%=request.getContextPath()%>/resources/images/dust.png");
		font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
  	 	 font-size: 14px;
    	line-height: 1.42857143;"	
	}
</style>
</head>
<body >
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-3">
				<p>用户姓名:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.name}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-3">
				<p>联系方式:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.phone}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-3">
				<p>生日:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.birthday}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-3">
				<p>历史总购买电子票数:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.tickets_total}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-3">
				<p>剩余电子票数:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.tickets_left}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-3">
				<p>家庭住址:</p>
			</div>
			<div class="col-xs-9">
				<p>${currentUser.province}${currentUser.city}${currentUser.area}${currentUser.address}</p>
			</div>
		</div>
	</div>
</body>
</html>