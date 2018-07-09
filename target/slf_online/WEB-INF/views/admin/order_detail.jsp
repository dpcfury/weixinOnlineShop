<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/admin_index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.min.css">

<title>订单详情</title>
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
			<li class="open">
				<div class="link"> <i class="fa fa-paint-brush"></i>
					订单管理 <i class="fa fa-chevron-down"></i>
				</div>
				<ul class="submenu">
					<li>
						<a href="<%=request.getContextPath()%>/manage/order/0/15">订单列表</a>
					</li>

				</ul>
			</li>
			<li class="">
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
							下单用户信息
						</div>
						<div class="panel-body">
							<table class="table">
								<thead>
									<tr>
										<th>用户id</th>
										<th>姓名</th>
										<th>联系电话</th>
										<th>生日</th>
										<th>累计购买水票</th>
										<th>剩余水票</th>
										<th>注册时间</th>
										<th>地址</th>
										<th>家中其他人口</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${currentOrder.user.id }</td>
										<td>${currentOrder.user.name }</td>
										<td>${currentOrder.user.phone }</td>
										<td>${currentOrder.user.birthday }</td>
										<td>${currentOrder.user.tickets_total }</td>
										<td>${currentOrder.user.tickets_left }</td>
										<td>${currentOrder.user.register_time }</td>
										<td>${currentOrder.user.register_time }</td>
										<td>${currentOrder.user.province}.${currentOrder.user.city}.${currentOrder.user.area}${currentOrder.user.address}</td>
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
						<div class="panel-heading ">
							订单条目
						</div>
						<div class="panel-body">
							<table class="table">
								<thead>
									<th>条目编号</th>
									<th>产品名称</th>
									<th>产品单价</th>
									<th>购买数量</th>
									<th>合计</th>
								</thead>
								<tbody>
									<c:forEach items="${currentOrder.items}" var="item">
										<tr>
											<td>${item.itemId}</td>
											<td>${item.proName}</td>
											<td>${item.proPrice}</td>
											<td>${item.num}</td>
											<td>${item.itemSummary}</td>
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
						<div class="panel-heading ">
							收货地址信息
						</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<th>收货地址编号</th>
								<th>收货人姓名</th>
								<th>联系方式</th>
								<th>收货地址</th>
							</thead>
							<tbody>
								<tr>
									<td>${currentOrder.address.id}</td>
									<td>${currentOrder.address.name}</td>
									<td>${currentOrder.address.phone}</td>
									<td>${currentOrder.address.province}${currentOrder.address.city}${currentOrder.address.area}${currentOrder.address.address}</td>
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
							订单相关属性
						</div>
						<div class="panel-body">
							<table class="table">
								<thead>
									<tr>
										<th>订单编号</th>
										<th>下单时间</th>
										<th>备注</th>
										<th>支付方式</th>
										<th>支付状态</th>
										<th>邮件提醒通知</th>
										<th>订单确认状态</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${currentOrder.oId}</td>
										<td>${currentOrder.createTime}</td>
										<c:if test="${currentOrder.msg==''}">
											<td>/</td>
										</c:if>
										<c:if test="${currentOrder.msg!=''}">
											<td>${currentOrder.msg}</td>
										</c:if>
										
										<c:if test="${currentOrder.payWay=='1'}">
											<td>微信支付</td>
										</c:if>
										<c:if test="${currentOrder.payWay=='2'}">
											<td>线下水票</td>
										</c:if>
										<c:if test="${currentOrder.payWay=='3'}">
											<td>电子水票</td>
										</c:if>
										
										<c:if test="${currentOrder.status=='1'}">
												<c:if test="${currentOrder.payWay=='1'}">
													<td>已在线支付</td>
												</c:if>
												<c:if test="${currentOrder.payWay=='2'}">
													<td>线下水票已扣除</td>
												</c:if>
												
												<c:if test="${currentOrder.payWay=='3'}">
													<td>电子票已扣除</td>
												</c:if>
										</c:if>
										
										<c:if test="${currentOrder.status=='0'}">
												<c:if test="${currentOrder.payWay=='1'}">
													<td>在线支付取消</td>
												</c:if>
												<c:if test="${currentOrder.payWay=='2'}">
													<td><button id="orderStatus" class="btn btn-sm btn-warning">确认已收取水票</button></td>
												</c:if>
												<c:if test="${currentOrder.payWay=='3'}">
													<td>电子票未成功扣除</td>
												</c:if>
										</c:if>
										
										<c:if test="${currentOrder.notifyFlag=='0'}">
											<td>通知失败</td>
										</c:if>
										<c:if test="${currentOrder.notifyFlag=='1'}">
											<td>邮件通知成功</td>
										</c:if>
										<c:if test="${currentOrder.confirmFlag=='0'}">
											<td><button class="btn btn-sm btn-warning" id="confirm">点击确认</button></td>
										</c:if>
										<c:if test="${currentOrder.confirmFlag=='1'}">
											<td><a disabled>已确认</a></td>
										</c:if>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			
		</div>
	</section>

	<!-- js控制代码 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<!-- slider  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/slider.js"></script>
	<script type="text/javascript">
		$(function (){
			$("#confirm").click(function (){
				$.ajax({
	    	        type: "POST",
	    	        url: "/slf_online/manage/order/confirm/${currentOrder.oId}",
	    	        success: function(data) {
	    	        	if(data=="OK"){
	    	        		alert("订单确认接收,用户将会收到短信通知");
	    	        		//更新超链接
	    	        		location.reload(true);
	    	        	}
	    	        	
	    	        }
				});
	    	        
			});
			
			$("#orderStatus").click(function (){
				$.ajax({
	    	        type: "POST",
	    	        url: "/slf_online/manage/order/status/${currentOrder.oId}",
	    	        success: function(data) {
	    	        	if(data=="OK"){
	    	        		alert("订单已确认支付完成!");
	    	        		//更新超链接
	    	        		location.reload(true);
	    	        	}
	    	        	
	    	        }
				});
			});
		});
		
	</script>
</body>
</html>