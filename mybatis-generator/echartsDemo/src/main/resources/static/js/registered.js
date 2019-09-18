$(function() {
    $('#submit').click(function() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/user/registered" ,
            data: $('#registeredForm').serialize(),
            success: function (result) {
                console.log(result);
                if (result.resultCode == 200) {
                    alert(result.msg);
                    window.location.href="login.html";
                }else {
                    alert(result.msg);
                    window.location.href="registered.html";
                }
            },
            error : function() {
            }
        });
        return false
    });
});