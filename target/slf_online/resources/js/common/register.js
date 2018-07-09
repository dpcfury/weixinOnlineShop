$.validator.setDefaults({
    submitHandler: function(form) {
        console.log("执行提交");
        form.submit();
    }
});
// 手机号码验证
$.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//日期校验
$.validator.addMethod("isDate", function(value, element) {
    var ereg = /^(\d{1,4})(-)(\d{1,2})(-)(\d{1,2})$/;
    var r = value.match(ereg);
    if (r == null) {
        return false;
    }
    var d = new Date(r[1], r[3] - 1, r[5]);
    var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);
    return this.optional(element) || (result);
}, "请输入正确的日期");


console.log("register.js 执行");
$(document).ready(function() {
    $("#signupForm").validate({
        rules: {
            "address": "required",
            "name": "required",
            "phone": {
                required: true,
                isMobile: true
            },
            "other_resident": {
                required: true
            },
            "birthday": {
                required: true,
                isDate: true
            }
        },
        messages: {
            "address": "请输入您的具体家庭地址（不用省市区）",
            "name": "请输入您的姓名",
            "phone": {
                required: "请输入电话号码",
                isMobile: "您输入的号码格式不正确"
            },
            "other_resident": {
                required: "请填写一个家中其他常住人口",
            },
            "birthday": {
                required: "请填写您的生日方便我们提供优惠",
                isDate: "您输入的日期格式不正确日期格式为2016-05-01 "
            }
        },
        errorElement: "em",
        errorPlacement: function(error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("label"));
            } else {
                error.appendTo(element.parent().parent());
            }
        },
        highlight: function(element, errorClass, validClass) {
            $(element).parents(".col-xs-12").addClass("has-error").removeClass("has-success");
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents(".col-xs-12").addClass("has-success").removeClass("has-error");
        }
    });

});
