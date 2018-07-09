var userId="";
var successFlag=0;
var allFlag=0;
var unPayFlag=0;

$(function() {
     //首先加载最近成功下单的订单
	 userId=$("#userId").val();
	 if(userId==""){
		 console.log("当前会话不合法");
	 }else{
		 console.log("userId:"+userId);
		 getSuccess();
	 }
	 //绑定事件
	 $("#a_success").click(function (){
		 getSuccess();
	 });
	 $("#a_all").click(function (){
		 getAll();
	 });
	 $("#a_unPay").click(function (){
		 getUnpay();
	 });
    
 });

 function refreshOrder(kind,target){
	 $.ajax({
         type: "GET",
         url: "/slf_online/order/query?kind="+kind+"&userID="+userId,
         success: function(data) {
             var flag = 0;
             var ohtml="";
             $.each(data, function(i, order) {

                 if(order.status==0){
                     ohtml+="<div class='row order-header bg-danger'> <div class=col-xs-8> <strong>订单号:";
                 }else{
                     ohtml+="<div class='row order-header bg-success'> <div class=col-xs-8> <strong>订单号:";
                 }
                 ohtml+=order.oId+"</strong></div><div class='col-xs-4'><strong>";
                 if(order.status==0){
                    ohtml+="未支付</strong></div></div>";
                 }else{
                     ohtml+="支付完成</strong></div></div>";
                 }
                 ohtml+="<div class='row lh30'><div class='col-xs-4'><p >下单时间:</p></div><div class='col-xs-8'><div class='text-center'>";
                 ohtml+=order.createTime+"</div></div></div>";
                 ohtml+="<div class='row lh30'><div class='col-xs-4'><p >商品名称:</p></div><div class='col-xs-8'><div class='text-center'>"+order.items[0].proName;
                 ohtml+="</div></div></div> <div class='row lh30'> <div class='col-xs-4'><p>单价: </p></div>";
                 ohtml+=" <div class='col-xs-8'><div class='text-center'>";
                 ohtml+=order.items[0].proPrice+"</div></div></div>";
                 ohtml+="<div class='row lh30'> <div class='col-xs-4'><p>数量/件:</p></div> <div class='col-xs-8'><div class='text-center'>";
                 ohtml+=order.items[0].num+" </div></div></div><div class='row lh30'> <div class='col-xs-4'>";
                 ohtml+="<p>总价:</p></div><div class='col-xs-8'><div class='text-center'>";
                 ohtml+=order.items[0].itemSummary+"</div></div></div>";
                 ohtml+="<div class='row lh30'><div class='col-xs-4'> <p>备注:</p> </div> <div class='col-xs-8'><div class='text-center'>";
                 if(order.msg==""){
                    ohtml+="无</div></div></div>";
                 }else{
                    ohtml+=order.msg+"</div></div></div>";
                 }
                
                 ohtml+="<div class='row lh30'><div class='col-xs-4'><p>收货地址:</p></div><div class='col-xs-8'><div class='text-center'>";
                 ohtml+=order.address.province+order.address.city+order.address.area+order.address.address;
                 ohtml+=" </div></div></div>";
                 ohtml+="<div class='row lh30'><div class='col-xs-4'><p>支付方式:</p></div><div class='col-xs-8'>";
                 if(order.payWay==1){
                     ohtml+="<div class='text-center wePay'>微信支付"+"</div></div></div>";
                 }else if(order.payWay==2){
                    ohtml+="<div class='text-center ticPay'>水票支付"+"</div></div></div>";
                 }else{
                	 ohtml+="<div class='text-center ticPay'>电子票支付"+"</div></div></div>";
                 }
                 ohtml+="<div class='row lh30'><div class='col-xs-4'><p>支付状态</p></div><div class='col-xs-8'><div class='text-center'>";
                if(order.status==1){
                    ohtml+="支付已完成   <i class='fa fa-check ' ></i></div></div></div><hr />";
                }else{
                    ohtml+="待支付<i class='fa fa-spinner fa-spin fa-1x fa-fw'></i><span class='sr-only'>Loading...</span></div> </div></div><hr />";
                }
             });
//             var tt="#"+target;
//             console.log("target:"+tt);
            $(ohtml).appendTo($("#"+target));
           
         },
         error:function(){
            console.log("请求查看订单出错");
         }

     }); 
 }
 
 function getSuccess(){
	 console.log("successFlag:"+successFlag);
	 if(successFlag!=1){
		 refreshOrder("success","success");
		 successFlag=1;
	 }
	 
 }
 
 function getAll(){
	 console.log("allFlag:"+allFlag);
	 if(allFlag==0){
		 refreshOrder("all","all");
		 allFlag=1;
		 
	 }
 }
 
 function getUnpay(){
	 console.log("unPayFlag:"+unPayFlag);
	 if(unPayFlag==0){
		 refreshOrder("unPay","unPay");
		 unPayFlag=1;
	 }
 }
 