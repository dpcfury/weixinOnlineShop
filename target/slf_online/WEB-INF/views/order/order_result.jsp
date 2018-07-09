<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>水票支付下单结果</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/order.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
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
            <div class="thumbnail">
                <img src="<%=request.getContextPath()%>/resources/images/product_show.jpg">
            </div>
        </div>
        <div class="row">
            <div class=col-xs-8>
                <strong>订单号:${order.oId}</strong>
            </div>
            
        </div>
        <hr />
        	<c:forEach items="${order.items}" var="item">
        <div class="row">
            <div class="col-xs-4">
                <p>商品名称:</p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                    ${item.proName}</div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <p>单价: </p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                  	 ￥${item.proPrice}</div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <p>数量/件</p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                    ${item.num} 件
                </div>
            </div>
        </div>
         <div class="row">
            <div class="col-xs-4">
                <p>收货地址</p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                    ${order.address.province}.${order.address.city}.${order.address.area}.${order.address.address}
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <p>收货人</p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                  ${order.address.name}
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <p>联系电话</p>
            </div>
            <div class="col-xs-8">
                <div class="text-center">
                       ${order.address.phone}
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <p>总价</p>
            </div>
            <div class="col-xs-8 ">
                <div class="text-center text-primary">
                   	￥${item.itemSummary}
                </div>
            </div>
        </div>
         <hr />
        </c:forEach>
       
        <div class="row">
            <div class="col-xs-12">
                <div class="pull-right">
                	 <p class="text-danger">已成功下单!!</p>
                    <p class="text-danger">总计:￥${order.summary}</p>
                </div>
            </div>
        </div>
        
        <c:if test="${order.payWay=='3'}">
        	 <div class="row">
            	<div class="col-xs-12">
               	 <div class="text-center">
                    	<div class="alert  alert-success alert-dismissible" role="alert">
                        	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        	<strong>交易成功!</strong> 电子票已扣除 ,剩余电子票${order.user.tickets_left}张<br />
                    	</div>
                	</div>
            	</div>
      	  </div>
        </c:if>
        
        <c:if test="${order.payWay=='2'}">
        	<div class="row">
          	  <div class="col-xs-12">
                	<div class="text-center">
                    	<div class="alert alert-warning alert-dismissible" role="alert">
                        	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        	<strong>注意!</strong> 水票支付收货时请提供配送员相应的水票.<br />
                   		 </div>
               		 </div>
          	  </div>
        	</div>
        </c:if>
        
        <div class="row">
            <div class="col-xs-12">
                <div class="pb20 pt10 ph10" style="padding-bottom:10px;"> 
                    <button class="btn btn-bg-blue btn-lg" id="continue_order">
                   	     点击继续订水
                    </button>
                </div>
            </div>
        </div>
         <div class="row">
            <div class="col-xs-12">
                <div class="pb20 pt10 ph10">
                    <button class="btn btn-bg-blue btn-lg" id="query_order">
                        查看以往订单
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$(function (){
    		$("#continue_order").click(function(){
    			location.replace("<%=request.getContextPath()%>/order/begin");
    		});
    		$("#query_order").click(function(){
    			location.replace("<%=request.getContextPath()%>/order/toQuery");
    		});
    	});
    </script>
</body>
</html>