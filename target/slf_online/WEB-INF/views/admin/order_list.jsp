<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单的主要显示</title>
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
						<header class="panel-heading">
							<h3>所有订单</h3>
						</header>
						<div class="panel-body">
							<table id="example" class="table table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>订单号</th>
										<th>下单用户</th>
										<th>下单时间</th>
										<th>支付方式</th>
										<th>支付状态</th>
										<th>邮件发送状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order_cp.items}" var="order">
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
											<c:if test="${order.payWay=='3'}">电子水票</c:if>
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
							
							<div >
							
								当前第<c:if test="${order_cp.totalCount=='0'}">
										${order_cp.currentPage}页 
									</c:if>
									<c:if test="${order_cp.totalCount!='0'}">
										${order_cp.currentPage+1}页 
									</c:if>共计${order_cp.totalPages}页 一页显示${order_cp.pageSize }条
								<nav style="text-align:center;margin:0 auto">
  									<ul class="pagination">
  										<li class="first"><a id="first" href="<%=request.getContextPath()%>/manage/order/0/15" disabled>首页</a></li>
    									<li class="previous"><a id="previous" href="<%=request.getContextPath()%>/manage/order/${order_cp.currentPage-1}/15">前一页</a></li>
    									<li class="next"><a id="next" href="<%=request.getContextPath()%>/manage/order/${order_cp.currentPage+1}/15">后一页</a></li>
    									<li class="last"><a id="last" href="<%=request.getContextPath()%>/manage/order/${order_cp.totalPages-1}/15">尾页</a></li>
 									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 右侧主体内容 -->  
	
	<!-- scripts start -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<!-- scripts end -->

	<!-- slider  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/slider.js"></script>

	<script>
		var currentPage=${order_cp.currentPage+1};
		var totalPage=${order_cp.totalPages};
		var totalCount=${order_cp.totalCount};
		
		$(document).ready(function() {
			if(totalCount==0){
				$(".first").addClass("active");
				$(".last").addClass("active");
				$(".previous").addClass("disabled");
				$(".next").addClass("disabled");
				$("#previous").removeAttr('href'); 
				$("#next").removeAttr('href'); 
				$("#last").removeAttr('href');  
				$("#first").removeAttr('href');
			}else{
				if(currentPage==1){
					$(".first").addClass("active");
					$(".previous").addClass("disabled");
					$("#first").removeAttr('href');
					$("#previous").removeAttr('href'); 
				}
				if(currentPage==totalPage){
					$(".last").addClass("active");
					$(".next").addClass("disabled");
					$("#last").removeAttr('href');  
					$("#next").removeAttr('href'); 
				}
			}
			
			window.setInterval(startrefresh,80000); 
		});
		
		function startrefresh(){
			location.href="<%=request.getContextPath()%>/manage/order/0/15";
		}
	</script>
	
	
	
	
	<!-- <script type="text/javascript">
		$(document).ready(function() {
    		$('#example').DataTable({
    			 "processing": true,
    		     "ajax": "<%=request.getContextPath()%>/manage/orderlist",
    		     "columns": [{ "data": "oId" },
    		                 { "data": "user.name" },
    		                 { "data": "createTime" },
    		                 { "data": "payWay" },
    		                 { "data": "status" },
    		                 { "data": "notifyFlag" },
    		                 { "data": "msg"}],
    		                 language: {
    		                     "sProcessing": "处理中...",
    		                     "sLengthMenu": "显示 _MENU_ 项结果",
    		                     "sZeroRecords": "没有匹配结果",
    		                     "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    		                     "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    		                     "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    		                     "sInfoPostFix": "",
    		                     "sSearch": "搜索:",
    		                     "sUrl": "",
    		                     "sEmptyTable": "表中数据为空",
    		                     "sLoadingRecords": "载入中...",
    		                     "sInfoThousands": ",",
    		                     "oPaginate": {
    		                         "sFirst": "首页",
    		                         "sPrevious": "上页",
    		                         "sNext": "下页",
    		                         "sLast": "末页"
    		                     },
    		                     "oAria": {
    		                         "sSortAscending": ": 以升序排列此列",
    		                         "sSortDescending": ": 以降序排列此列"
    		                     }
    		                 }
    		});
		} );
	</script> -->
	
</body>
</html>