var tickNum =$("#tickNum").val();

var script =function(){
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope) {
    $scope.product_num= 5;
    
    if(tickNum< $scope.product_num ){
    	$("#rd3").attr("disabled",true);
    }
    
    $scope.decreaseNum=function (){
    	if( $scope.product_num>5)
    		 $scope.product_num= $scope.product_num-1;
    	console.log($scope.product_num);
    	if($scope.product_num <= tickNum)
    		$("#rd3").attr("disabled",false);
    	return false;
    };
    $scope.addNum = function () {
     	$scope.product_num= $scope.product_num + 1;
    	console.log($scope.product_num);
    	var flag =$('input:radio:checked').val();
    	if(flag==3){
    		if($scope.product_num > tickNum){
         		$("#rd3").attr("disabled",true);
         		$('#rd3').iCheck('uncheck');
         		$('#rd1').iCheck('check');
         	}
    	}
     	
    		
     	return false;
    };
    
    console.log(tickNum);
    
    $("#num").on('blur',function(){
    	console.log($scope.product_num);
    	
    	var input =$scope.product_num+"";
		console.log("input:"+input);
		var regu = /^[1-9][0-9]{0,3}$/; 
		if(!input.trim()==" "){
			if(regu.test(input)){
				var num = input-0;
				if(num<5){
					$scope.product_num=5;
					$("#num").val(5);
					$scope.$apply();
				}else{
					console.log("输入合法");
					
					var flag =$('input:radio:checked').val();
					if(flag==3){
						if($scope.product_num >tickNum){
							$("#rd3").attr("disabled",true);
							$('#rd3').iCheck('uncheck');
				     		$('#rd1').iCheck('check');
						}else{
							$("#rd3").attr("disabled",false);
						}
					}
					
					$scope.$apply();
					return true;
				}
				
			}else{
				console.log("输入不合法");
				$scope.product_num=5;
				$("#num").val(5);
				$scope.$apply();
				console.log("输入的值为:"+$scope.product_num);
				return false;
			}
		}else{
			console.log("现在数量为空");
			$scope.product_num=5;
			$("#num").val(5);
			$scope.$apply();
			console.log("输入的值为:"+$scope.product_num);
			return false;
		}
    	
    });
    $scope.checkInput = function(){
		var input =$scope.product_num+"";
		console.log("input:"+input);
		var regu = /^[1-9][0-9]{0,3}$/; 
		if(!input.trim()==" "){
			if(regu.test(input)){
				var num = input-0;
				if(num<5){
					$scope.product_num=5;
					$("#num").val(5);
				}else{
					console.log("输入合法");
					return true;
				}
				
			}else{
				console.log("输入不合法");
				$scope.product_num=5;
				$("#num").val(5);
				console.log("输入的值为:"+$scope.product_num);
				return false;
			}
		}else{
			console.log("现在数量为空");
			$scope.product_num=5;
			$("#num").val(5);
			console.log("输入的值为:"+$scope.product_num);
			return false;
		}
	}

	 $scope.checkWhenLeave = function(){
	 	console.log("失去焦点事件被执行");
	 	var input = $scope.product_num+"";
	 	if(input.trim()==" "){
	 		console.log("目前框内为空");
	 		$scope.product_num=5;
			$("#num").val(5);
	 	}
	 	return false;

	 }
	 
	});
	
}();

function checkOnBlur(){
	console.log("失去焦点事件被执行:Onblur");
	var input = $("#num").val();
	
	//先判断是否是纯的数字
	
	if(input.trim().length==0){
		console.log("数量目前为空设置为最低的5");
		$("#num").val(5);
	}
	else{
		console.log(input);
	}
	return false;
}