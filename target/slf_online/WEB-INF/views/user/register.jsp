<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/order.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/font-awesome.min.css">
<title>注册页面</title>
</head>
<body
	style="font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；; font-size: 14px; line-height: 1.42857143;">
	<!-- container start -->
	<div class="container-fluid">
		<div class="row">
			<!-- <div class="col-xs-12"> -->
			 <h3 class="text-center text-blue">注册信息填写</h3>
			<!-- </div> -->
			<hr>
		</div>
		<div class="row">
			<!-- <div class="col-xs-12"> -->
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" id="signupForm" method="post"
						action="/slf_online/user/register">
						<div class="form-group">
							<div class="col-xs-4">
								<select class="form-control" id="provinces" name="province">
									<option>---省---</option>

								</select>
							</div>
							<div class="col-xs-4">
								<select class="form-control" id="cities" name="city">
									<option>---市---</option>
								</select>
							</div>
							<div class="col-xs-4">
								<select class="form-control" id="areas" name="area">
									<option>---区---</option>
								</select>
							</div>
						</div>
						<div class="form-group ">
							<div class="col-xs-12">
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-home"></span>
									</span> <input type="text" class="form-control" id="address"
										name="address" placeholder="xx/栋/xxx 或 xxx号(具体家庭地址)">
								</div>
							</div>
						</div>
						<div class="form-group ">
							<div class="col-xs-12">
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-user"></span>
									</span> <input type="text" class="form-control" id="name" name="name"
										placeholder="用户姓名">
								</div>
							</div>
						</div>
						<div class="form-group ">
							<div class="col-xs-12">
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-phone"></span>
									</span> <input type="text" class="form-control" id="phone"
										name="phone" placeholder="用户手机号码">
								</div>
							</div>
						</div>
						
						<input type="hidden" name="other_resident" name="other_resident" value="/"/>
						
						<div class="form-group ">
							<div class="col-xs-12">
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span> <input type="text" name="birthday" name="birthday"
										placeholder="生日 格式如: 2016-07-01" class="form-control">
								</div>
							</div>
						</div>
						
				</div>
			</div>
			<!-- </div> -->
		</div>
		<!-- button to submit -->
		 <div class="row">
            <div class="pb20 pt10 ph10">
                <button class="btn btn-bg-blue btn-lg" >确认注册</button>               
            </div>
        </div>
	</div>
	<!-- </div> -->
	</form>
	<!-- container end -->
	<script src="<%=basePath%>resources/js/jquery-1.12.4.min.js"></script>
	<script src="<%=basePath%>resources/js/jquery.validate.min.js"></script>
	<script src="<%=basePath%>resources/js/common/district.js"></script>
	<script src="<%=basePath%>resources/js/common/register.js"></script>
</body>
</html>