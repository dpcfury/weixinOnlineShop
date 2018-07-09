/**
 * 负责省市区三级联动的javascript文件
 */

$(function() {
	var currentCityId = "";
	var currentProvinceId;
	initProvinces();
	console.log("当前的省代码为:" + currentProvinceId);
	initCities("110000");
	initAreas("110100");
	$('#provinces').on(
			"change",
			function() {
				var provinceId = $('#provinces option:selected').val();
				$('#cities').empty();
				$.ajax({
					type : "GET",
					url : "/slf_online/district/getCities?provinceId="
							+ provinceId,
					success : function(data) {
						$.each(data, function(i, it) {
							console.log("我是在执行增加城市节点");
							$(
									"<option value='" + it.cityId + "' >"
											+ it.cityName + "</option>")
									.appendTo($('#cities'));
						});
					}
				});

				// $('#areas').empty();
			});

	/**
	 * 设置市选择的联动
	 */
	$('#cities').on(
			"change",
			function() {
				var cityId = $('#cities option:selected').val();
				console.log("联动中cityId:" + cityId);
				$('#areas').empty();
				$.ajax({
					type : "GET",
					url : "/slf_online/district/getAreas?cityId=" + cityId,
					success : function(data) {
						$.each(data, function(i, it) {
							console.log("我是在执行增加区节点");
							$(
									"<option value='" + it.areaId + "' >"
											+ it.areaName + "</option>")
									.appendTo($('#areas'));
						});
					}
				});
			});

/**
 * 获取省列表
 */
function initProvinces() {
	$('#provinces').empty();
	$.ajax({
		type : "GET",
		url : "/slf_online/district/getProvinces",
		success : function(data) {
			var flag = 0;
			$.each(data, function(i, it) {
				if (flag == 0) {
					$(
							"<option value='" + it.provinceId
									+ " select='selected' >" + it.provinceName
									+ "</option>").appendTo($('#provinces'));
					flag++;
					currentProvinceId = it.provinceId;
					console.log("在遍历的时候当前省代码为:" + currentProvinceId);
				} else {
					$(
							"<option value='" + it.provinceId + "' >"
									+ it.provinceName + "</option>").appendTo(
							$('#provinces'));
				}

			});
		}
	});
	console.log("在遍历的时候zuihou:" + currentProvinceId);
}

/**
 * 获取市列表
 */
function initCities(provinceId) {
	console.log("provinceId:" + provinceId);
	$('#cities').empty();
	$.ajax({
		type : "GET",
		url : "/slf_online/district/getCities?provinceId=" + provinceId,
		success : function(data) {
			$.each(data, function(i, it) {
				$(
						"<option value='" + it.cityId + "' >" + it.cityName
								+ "</option>").appendTo($('#cities'));
			});
		}
	});
}
/**
 * 获取区县列表
 */
function initAreas(cityId) {
	console.log("cityId:" + cityId);
	$('#areas').empty();
	$.ajax({
		type : "GET",
		url : "/slf_online/district/getAreas?cityId=" + cityId,
		success : function(data) {
			$.each(data, function(i, it) {
				$(
						"<option value='" + it.areaId + "'>" + it.areaName
								+ "</option>").appendTo($('#areas'));
			});
		}
	});
}

});



