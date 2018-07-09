<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
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
							所有用户
						</div>
						<div class="panel-body">
							<table class="table">
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
									<th>操作</th>
								</thead>
								<tbody>
									<c:forEach items="${user_cp.items}" var="user">
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td name="bir">${user.birthday}</td>
											<td>${user.tickets_total}</td>
											<td>${user.tickets_left}</td>
											<td>${user.register_time}</td>
											<td>${user.province}${user.city}${user.area}${user.address}</td>
											<td>${user.other_resident}</td>
											<td><a href="<%=request.getContextPath()%>/manage/userdetail/${user.id}" class="btn btn-sm btn-primary">  详情    </a></td>
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
							
								<div >
							
								当前第<c:if test="${user_cp.totalCount=='0'}">
										${user_cp.currentPage}页 
									</c:if>
									<c:if test="${user_cp.totalCount!='0'}">
										${user_cp.currentPage+1}页 
									</c:if> 共计${user_cp.totalPages}页 一页显示${user_cp.pageSize }条
								<nav style="text-align:center;margin:0 auto">
  									<ul class="pagination">
  										<li class="first"><a id="first" href="<%=request.getContextPath()%>/manage/user/0/15" disabled>首页</a></li>
    									<li class="previous"><a id="previous" href="<%=request.getContextPath()%>/manage/user/${order_cp.currentPage-1}/15">前一页</a></li>
    									<li class="next"><a id="next" href="<%=request.getContextPath()%>/manage/user/${order_cp.currentPage+1}/15">后一页</a></li>
    									<li class="last"><a id="last" href="<%=request.getContextPath()%>/manage/user/${order_cp.totalPages-1}/15">尾页</a></li>
 									</ul>
								</nav>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
	
	<!-- scripts start -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<!-- scripts end -->

	<!-- slider  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/slider.js"></script>
	
	<script>
		var currentPage=${user_cp.currentPage+1};
		var totalPage=${user_cp.totalPages};
		var totalCount=${user_cp.totalCount};
		
		$(document).ready(function() {
			//遍历修改所有的生日格式
			$("td[name='bir']").each(function(){
				var birthday =$(this).text();
				var section =birthday.substring(0,10);
				$(this).text(section);
			});
			
			
			
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
			
		});
		
	</script>
</body>
</html>