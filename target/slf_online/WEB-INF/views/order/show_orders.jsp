<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>订单查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/show_orders.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.min.css">
    <style type="text/css">
    body {
        background-image: url("<%=request.getContextPath()%>/resources/images/dust.png");
        font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
    font-size: 14px;
    line-height: 1.42857143;"
    }
    
    .col-xs-6 {
        border-right: 1px solid #e6e6e6;
    }
    </style>
</head>
<body>
	<div class="container-fluid">
        <div class="row">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a data-toggle="tab" href="#success" id="a_success">已支付</a>
                </li>
                 <li class="">
                    <a data-toggle="tab" href="#unPay" id="a_unPay">未支付</a>
                </li>
                <li class="">
                    <a data-toggle="tab" href="#all" id="a_all">全部</a>
                </li>
               
            </ul>
        </div>
        <div class="tab-content">
            <div class="tab-pane active" id="success">
                
            </div>
            <div class="tab-pane" id="all">

            </div>
            <div class="tab-pane" id="unPay">
            </div>
        </div>
    </div>
    
    <input id="userId" type="hidden" value="${currentUser.id}"/>  
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
   <script src="<%=request.getContextPath()%>/resources/js/common/order_query.js"></script>
</body>
</html>