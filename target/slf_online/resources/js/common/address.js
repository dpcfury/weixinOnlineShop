$.validator.setDefaults({
    submitHandler: function(form) {
        console.log("执行提交");
        form.submit();
    }
});
//手机号码验证
$.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");
$(document).ready(function() {
    $("#addAddress").validate({
        rules: {
            "address": "required",
            "name": "required",
            "phone": {
                required: true,
                isMobile: true
            }
        },
        messages: {
            "address": "请输入您的具体家庭地址（不用省市区）",
            "name": "请输入收货人姓名",
            "phone": {
                required: "请输入电话号码",
                isMobile: "您输入的号码格式不正确"
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