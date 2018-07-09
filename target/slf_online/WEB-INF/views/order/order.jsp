<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html lang="zh">
<head>
<title>订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/order.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/font-awesome.min.css">
<link href="<%=basePath%>resources/skins/square/blue.css" rel="stylesheet">
<style type="text/css">
	body{
		 background-image: url("<%=basePath%>resources/images/dust.png");
	}
</style>
<body style="font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
    font-size: 14px;
    line-height: 1.42857143;">
	<div class="container-fluid" ng-app="myApp" ng-controller="myCtrl">
		<div class="row">
			<div class="thumbnail">
				<img src="<%=basePath%>resources/images/product_show.jpg">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12"></div>
			<table class="table table-bordered">
				<thead>
					<tr >
						<th class="text-center">商品名称</th>
						<th class="text-center">单价</th>
						<th class="text-center">数量</th>
						<th class="text-center">合计</th>
					</tr>
				</thead>
				<tbody>
					<form id="orderForm" name="orderForm" action="/slf_online/wxPay/unifiedorder" method="POST">
					<tr class="text-center">
						<td class="text-normal" style="vertical-align:middle"><span><strong>吉氧山泉</strong></span>
						<input type="hidden" name="items[0].proId" value="402881e755c940be0155c940c2ff0000">
						</td>
						<td  style="vertical-align:middle"><span><i>￥ </i><span class="price">25</span></span>
						<input type="hidden" name="items[0].proName" value="吉氧山泉">
						<input type="hidden" name="items[0].proPrice" value="25">
						</td>
						<td style="vertical-align:middle">
							<span class="amount">
								<a id="num_minus"  ng-click="decreaseNum()">
									<span class="glyphicon glyphicon-minus text-blue"></span>
								</a>
								<input type="number" name="items[0].num"  id="num"  ng-model="product_num" />
								<a id="num_plus"  ng-click="addNum()">
									<span class="glyphicon glyphicon-plus text-blue"></span>
								</a>
							</span>
							<input type="hidden" name="items[0].itemSummary" value="{{product_num * 25}}">
						</td>
						<td  style="vertical-align:middle"><span><i>￥ </i><span class="total">{{product_num * 25}}</span></span></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="col-xs-10">
				<div class="form-group row-flex">
					<div class="col-flex-1 mt6 font-bold">地址</div>
					<div class="col-flex-4 ">
						<select id="address" class="form-control" name="addressId">
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-2">
				<a href="/slf_online/user/address/add" class="btn">
					<p><i class="icon-plus-sign-alt icon-large text-blue text-size-18 address-icon-p"></i></p>
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-10">
				<div class="form-group row-flex ">
					<div class="col-flex-1 mt6 font-bold">备注</div>
					<div class="col-flex-4">
						<textarea placeholder="填写一些需要注意的事项"  class="form-input office-area" rows="3" id="message" name="msg" ></textarea>
					</div>
				</div>
			</div>
			<div class="col-xs-2">
				<!-- <a href="添加新地址">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
			-->
			</div>
		</div>

		<input type="hidden" value="${currentUser.tickets_left}" id="tickNum"/>

		<div class="row">
			<div class="col-xs-10">
				<div class="form-group row-flex">
					<div class="col-flex-1 mt6 font-bold payLable">支付</div>
					<div class="col-flex-4">
						<input type="hidden" name="payWay" id="payWay" value="2"/> 	
						
						<div class="payitem">
							<div class="pull-left">
								<input type="radio"  name="payWayRd" value="2"  id="rd2" checked/><span id="ticketPay" class="pl5 payColor" style="position:relative;top:1px;font-size:16px">   线下水票</span>
							</div>
						</div>
						
						<div class="payitem">
							<div class="pull-left">
								<input type="radio"  name="payWayRd" value="1"  id="rd1" /><span id="wePay" class="pl5" style="position:relative;top:1px;font-size:16px">   微信支付</span>
							</div>
						</div>
						
						<div class="payitem">
							<div class="pull-left">
								<input type="radio"  name="payWayRd" value="3"  id="rd3" /><span id="eTicketPay" class="pl5 " style="position:relative;top:1px;font-size:16px">   电子水票(剩余${currentUser.tickets_left}张)</span>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-xs-2">
				<!-- <a href="添加新地址">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
			-->
			</div>
		</div>
		<hr style="margin-top:10px;margin-bottom:10px;">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel ">

					<div class="panel-body">
						<div class="col-xs-3 text-center"> <strong>合计:</strong>
						</div>
						<div class="col-xs-3 text-center"> <strong style="font-size:16px;color:#2897CE">￥<del><span class="total">{{product_num * 25}}</span></del></strong>
						</div>
						<div class="col-xs-3 text-center">
							<strong>应付:</strong>
						</div>
						<div class="col-xs-3 text-center">
							<strong style="color:red">￥<span class="actually" style="font-size:16px">{{product_num * 25}}</span></strong>
							<input type="hidden" name="summary" value="{{product_num * 25}}">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="pb20 pt10 ph10 ">
				<button class="btn btn-bg-blue btn-lg" type="submit" id="btn_sub">确认下单</button>
			</div>
		</div>
	</form>
</div>


<script src="<%=basePath%>resources/js/jquery-1.12.4.min.js"></script>
<script src="<%=basePath%>resources/js/common/order.js"></script>
<script src="<%=basePath%>resources/js/angular.js"></script>
<script src="<%=basePath%>resources/js/common/myModule.js"></script>
<script src="<%=basePath%>resources/js/icheck.min.js"></script>
</body>
</html>