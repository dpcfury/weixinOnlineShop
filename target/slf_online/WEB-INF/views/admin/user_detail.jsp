<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详情页</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/admin_index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.min.css">

</head>
<body>
<!-- container start -->
	<section id="container">
		<!-- 导航条开始 -->
		<nav class="navbar navbar-default  navbar-fixed-top" >
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">
						<span class="glyphicon glyphicon-tint"></span>
					</a>

				</div>

				<!--  标题。。-->
				<ul class="nav navbar-nav">
					<li>
						<a  class="logo">
							水立方
							<span>水业</span>
						</a>
					</li>
					<li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								订单管理
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<%=request.getContextPath()%>/manage/order/0/15">订单列表</a>
								</li>
								
							</ul>
						</li>
					</li>
					<li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								用户管理
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<%=request.getContextPath()%>/manage/user/0/15">用户列表</a>
								</li>
								
							</ul>
						</li>
					</li>
					
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								账户相关
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<%=request.getContextPath()%>/manage/logout">注销</a>
								</li>
								
							</ul>
						</li>
					</li>
					<li>
						<a href="#" disabled>当前用户:${currentStaff.rName }</a>
					</li>
				</ul>

			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- 导航条结束 -->
	</section>
	<!-- container end  -->
	
	<!-- 左侧导航开始 -->
		<div class="slider">

		<ul id="accordion" class="accordion">
			<li class="">
				<div class="link"> <i class="fa fa-paint-brush"></i>
					订单管理 <i class="fa fa-chevron-down"></i>
				</div>
				<ul class="submenu">
					<li>
						<a href="<%=request.getContextPath()%>/manage/order/0/15">订单列表</a>
					</li>

				</ul>
			</li>
			<li class="open">
				<div class="link">
					<i class="fa fa-code"></i>
					用户管理
					<i class="fa fa-chevron-down"></i>
				</div>
				<ul class="submenu">
					<li>
						<a href="<%=request.getContextPath()%>/manage/user/0/15">用户列表</a>
					</li>
				</ul>
			</li>

		</ul>
	</div>

	<!-- 左侧导航结束 -->
	
	
	<!-- 右侧主体内容 -->
	<section id="main-content">
		<div class="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel">
						<div class="panel-heading">
							用户基本信息
						</div>
						<div class="panel">
							<table class="table table-bordered">
								<thead>
									<th>id</th>
									<th>姓名</th>
									<th>电话</th>
									<th>生日</th>
									<th>累计购买水票</th>
									<th>剩余水票</th>
									<th>注册时间</th>
									<th>家庭住址</th>
									<th>家中其他常住人口</th>
								</thead>
								<tbody>
									<tr>
										<td>${user_detail.id}</td>
										<td>${user_detail.name}</td>
										<td>${user_detail.phone}</td>
										<td id="bir">${user_detail.birthday}</td>
										<td>${user_detail.tickets_total}</td>
										<td>${user_detail.tickets_left}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-warning btn-sm" data-toggle="modal" data-target=".num_input">修改</button></td>
										<td>${user_detail.register_time}</td>
										<td>${user_detail.province}${user_detail.city}${user_detail.area}${user_detail.address}</td>
										<td>${user_detail.other_resident}</td>
										</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel">
						<div class="panel-heading">
							用户收货地址
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<th>收货地址id</th>
									<th>收货人姓名</th>
									<th>联系电话</th>
									<th>具体地址</th>
								</thead>
								<tbody>
									<c:forEach items="${user_detail.deliveryAddresses}" var="daddress">
										<tr>
											<td>${daddress.id}</td>
											<td>${daddress.name}</td>
											<td>${daddress.phone}</td>
											<td>${daddress.province}${daddress.city}${daddress.area}${daddress.address}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel">
						<div class="panel-heading">
							用户历史订单
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<th>订单号</th>
									<th>下单用户</th>
									<th>下单时间</th>
									<th>支付方式</th>
									<th>支付状态</th>
									<th>邮件发送状态</th>
									<th>操作</th>
								</thead>
								<tbody>
									<c:forEach items="${user_orders}" var="order">
										<c:if test="${order.confirmFlag=='1'}">
											<tr>
										</c:if>
										<c:if test="${order.confirmFlag=='0'}">
											<tr style="background-color: aliceblue;">
										</c:if>
											<td>${order.oId}</td>
											<td>${order.user.name}</td>
											<td>${order.createTime}</td>
											<td><c:if test="${order.payWay=='1'}">微信支付</c:if>
												<c:if test="${order.payWay=='2'}">线下水票</c:if>
											</td>
											<td>
												<c:if test="${order.status=='1'}">
													<c:if test="${order.payWay=='1'}">
														已在线支付
													</c:if>
													<c:if test="${order.payWay=='2'}">
														线下水票扣除
													</c:if>
												
													<c:if test="${order.payWay=='3'}">
														电子票扣除
													</c:if>
												</c:if>
												<c:if test="${order.status=='0'}">
													<c:if test="${order.payWay=='1'}">
														在线支付取消
													</c:if>
													<c:if test="${order.payWay=='2'}">
													待收取水票
													</c:if>
													<c:if test="${order.payWay=='3'}">
													电子票未成功扣除
													</c:if>
												</c:if>
											</td>
										
											<td>
											<c:if test="${order.notifyFlag=='0'}">
												通知失败
											</c:if>
											<c:if test="${order.notifyFlag=='1'}">
												邮件通知成功
											</c:if>
											</td>
											<td>
												<a href="<%=request.getContextPath()%>/manage/orderdetail/${order.oId}" class="btn btn-primary btn-sm">  详情  </a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</section>
	
	<!-- 摸态框  -->
	<div class="modal fade num_input" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
 		 <div class="modal-dialog modal-sm">
    		<div class="modal-content">
    			<div class="modal-header">
     		 	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      		 	 <h4 class="modal-title">输入相应的数量</h4>
     			</div>
    			
    			<div>
    				<input type="number" id="t_num" class="form-control" min="1" max="1000" placeholder="1"/> 
    			</div>
     	 		<div>
     	 			<button class="btn btn-primary btn-block" data-dismiss="modal" id="num_change">确认修改</button>
     	 		</div>
    		</div>
  		</div>
	</div>
	
	<!-- js控制代码 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<!-- slider  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/slider.js"></script>
	
	<script>
		$(function(){
			//修改生日的格式
			var birthday =$("#bir").text();
			var section =birthday.substring(0,10);
			console.log(birthday);
			$("#bir").text(section);
			
			$("#num_change").click(function(){
				var newNum=$("#t_num").val();
				$.ajax({
	    	        type: "POST",
	    	        data:{"num":newNum},
	    	        url: "/slf_online/manage/user/updateTicket/${user_detail.id}",
	    	        success: function(data) {
	    	        	if(data=="OK"){
	    	        		alert("添加成功");
	    	        		location.reload(true);
	    	        	}else{
	    	        		alert("添加失败");
	    	        	}
	    	        }
				});
			});
		});
	</script>
</body>
</html>