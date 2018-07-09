<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
<title>下单成功</title>
</head>

<style type="text/css">
    body{
         background-image: url("<%=request.getContextPath()%>/resources/images/dust.png");
         font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif；;
    	font-size: 14px;
    	line-height: 1.42857143;"
    }
</style>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="thumbnail">
				<img
					src="<%=request.getContextPath()%>/resources/images/product_show.jpg">
			</div>
			<div class="caption">
				<div style="padding-left: 10px">
					<h5>订单号:${order.oId}</h5>
				</div>
				<hr />
				<div style="padding-left: 10px">
					<p>订单详情:</p>
				</div>
				<div class="ph10">
					<table class="table table-bordered" >
						<thead>
							<tr>
								<th>商品名称</th>
								<th>单价/元</th>
								<th>数量/件</th>
								<th>总价/元</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${order.items}" var="item">
								<tr>
									<td>${item.proName}</td>
									<td>${item.proPrice}</td>
									<td>${item.num}</td>
									<td style="color:red">${item.itemSummary}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<hr />
				<div class="pb20 pt10 ph10">
                    <button class="btn btn-bg-blue btn-lg" id="confirm">点击支付</button>
                </div> 
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
    	$(function(){
    		$("#confirm").click(function(){
    			//检查微信是否准备好
        		if (typeof WeixinJSBridge == "undefined"){
    	    		   if( document.addEventListener ){
    	    		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    	    		   }else if (document.attachEvent){
    	    		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
    	    		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    	    		   }
    	    	}else{
    	    		onBridgeReady();
    	    	}
    			
			});
    		
    		
		});
    	
    	
    	function onBridgeReady(){
    		 $("#confirm").attr("disabled","disabled");
    		 $("#confirm").text("支付中...");
    		  WeixinJSBridge.invoke('getBrandWCPayRequest',{  
                  "appId" :"${payparameter['appId']}",                //公众号名称，由商户传入  
                  "timeStamp":"${payparameter['timeStamp']}",         //时间戳，自 1970 年以来的秒数  
                  "nonceStr" : "${payparameter['nonceStr']}",         //随机串  
                  "package" : "${payparameter['package']}",      //商品包信息 
                  "signType" : "${payparameter['signType']}",        //微信签名方式:  
                  "paySign" : "${payparameter['paySign']}"           //微信签名  
                  },function(res){
                  if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
                	  $("#confirm").text("完成支付");
                      $("#confirm").attr("disabled","disabled");
                      location.replace("<%=request.getContextPath()%>/wxPay/showPayResult");
				} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
						alert("支付取消");
						 $("#confirm").attr("disabled",false);
						 $("#confirm").text("继续支付");
				} else {
						alert("支付失败"+ JSON.stringify(res));
						$("#confirm").attr("disabled",false);
				}
			});
		}	
    	
	</script>
</body>
</html>