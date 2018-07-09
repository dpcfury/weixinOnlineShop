<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="pixel-ratio-2 retina ios ios-9 ios-9-1 ios-gt-8 ios-gt-7 ios-gt-6">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
<title>用户个人中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/usercenter.css">>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.css">
</head>
<body>
	<div class="page page-current" id="page-1473683649934">
    <header class="bar bar-nav" style="height:4rem;">
        <a><h3 class="usrePhoto">当前用户名:${currentUser.name}</h3></a>
    </header>
	<div class="content" style="margin-top:3rem;">

	  <div class="card">
	    <div class="card-content">
	      <div class="list-block">
	        <ul>
	          <li>
	            <a href="<%=request.getContextPath()%>/order/toQuery" class="item-link item-content external">
	              <div class="item-media" style="color:#ff1744 "><i class="fa fa-reorder"></i></div>
	              <div class="item-inner">
	                <div class="item-title">历史订单查看</div>
	              </div>
	            </a>
	          </li>
	        </ul>
	      </div>
	    </div>
	  </div>

	
		<div class="card">
			<div class="card-content">
				<div class="list-block">
					<ul>
						<li>
							<a href="<%=request.getContextPath()%>/user/info" class="item-link item-content external">
								<div class="item-media" style="color:#4caf50"><i class="fa fa-money"></i></div>
								<div class="item-inner">
									<div class="item-title">个人基本信息</div>
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		

	</div>
</body>
</html>