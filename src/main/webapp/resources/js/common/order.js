$(function(){
	$('input').iCheck({
    	checkboxClass: 'icheckbox_square-blue',
    	radioClass: 'iradio_square-blue',
    	increaseArea: '20%' // optional
  	});

	$('input').on('ifChecked', function(event){
 		var flag =$('input:radio:checked').val();
 		if(flag==1){//实际是微信支付被选中
 			console.log("微信支付被选中"+flag);
 			$("#payWay").val(1);
 			$("#ticketPay").removeClass("payColor");
 			$("#eTicketPay").removeClass("payColor");
 			$("#wePay").addClass("payColor");
 		}else if(flag==2){//水票支付被选中
 			console.log("水票支付被选中"+flag);
 			$("#payWay").val(2);
 			$("#wePay").removeClass("payColor");
 			$("#ticketPay").addClass("payColor");
 			$("#eTicketPay").removeClass("payColor");
 		}else{
 			console.log("电子票支付被选中"+flag);
 			$("#payWay").val(3);
 			$("#eTicketPay").addClass("payColor");
 			$("#ticketPay").removeClass("payColor");
 			$("#wePay").removeClass("payColor");
 		}
	});
	
	$.ajax({
		 type: "GET",
	     url: "/slf_online/user/getDeliveryAddress",
	     success: function(data) {
	        $.each(data, function(i, it){
	        	console.log(it.address);
	        	$("<option value='"+it.id+"'>"+it.name+" "+it.address+"</option>").appendTo($("#address"));
	        });
	     }
	});
	$("#orderForm").submit(function(){
		console.log("事件确实是处罚过的还");
		$("#btn_sub").text("正在下单....");
		$("#btn_sub").attr("disabled","disabled");
	});
});

