$(function() {
    initProvinces();
    $('#provinces').on("change", function() {
        var provinceId = $('#provinces option:selected').val();
        getCities(provinceId);
    });
    $('#cities').on("change", function() {
        var cityId = $('#cities option:selected').val();
        getAreas(cityId);
    });
});

/**
 * 获取省列表
 */
function initProvinces() {
    $('#provinces').empty();
    $.ajax({
        type: "GET",
        url: "/slf_online/district/getProvinces",
        success: function(data) {
            var flag = 0;
            $.each(data, function(i, it) {
                if (flag == 0) {
                    $("<option value='" + it.provinceId + "' select='selected' >" + it.provinceName + "</option>").appendTo($('#provinces'));
                    flag++;
                    getCities(it.provinceId);
                } else {
                    $("<option value='" + it.provinceId + "' >" + it.provinceName + "</option>").appendTo($('#provinces'));
                }

            });
        }
    });
}

/*
		初始化城市列表
    */
function getCities(provinceId) {
    $('#cities').empty();
    $.ajax({
        type: "GET",
        url: "/slf_online/district/getCities?provinceId=" + provinceId,
        success: function(data) {
            var flag = 0;
            $.each(data, function(i, it) {
                if (flag == 0) {
                    $("<option value='" + it.cityId + "' >" + it.cityName + "</option>")
                        .appendTo($('#cities'));
                    flag++;
                    getAreas(it.cityId);
                } else {
                    $("<option value='" + it.cityId + "' >" + it.cityName + "</option>")
                        .appendTo($('#cities'));
                }
            });
        }
    });
}

function getAreas(cityId) {
    $('#areas').empty();
    $.ajax({
        type: "GET",
        url: "/slf_online/district/getAreas?cityId=" + cityId,
        success: function(data) {
            var flag = 0;
            $.each(data, function(i, it) {
                if (flag == 0) {
                    $("<option value='" + it.areaId + "'>" + it.areaName + "</option>")
                        .appendTo($('#areas'));
                    flag++;
                } else {
                	$("<option value='" + it.areaId + "'>" + it.areaName + "</option>")
                    .appendTo($('#areas'));
                }
                
            });
        }
    });
}
