$(function() {
    $('#submit').click(function() {
        $.ajax({
            type: "POST",
            //预期服务器返回的数据类型
            dataType: "json",
            url: "/user/login" ,
            data: $('#loginFrom').serialize(),
            success: function (result) {
                //打印服务端返回的数据(调试用)
                console.log(result);
                if (result.resultCode == 200) {
                    window.location.href="echartsDemo/echartsDemo1.html";
                }else {
                    alert(result.msg);
                    window.location.href="login.html";
                }
            },
            error : function() {
            }
        });
        return false
    });
});