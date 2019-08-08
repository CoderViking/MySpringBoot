if('ontouchstart' in document.documentElement) document.write("<script th:src='@{js/jquery.mobile.custom.min.js}'>"+"<"+"/script>");

<!-- inline scripts related to this page -->
    jQuery(function($) {
        $(document).on('click', '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });



//you don't need this, just used for changing background
jQuery(function($) {
    $('#btn-login-dark').on('click', function(e) {
        $('body').attr('class', 'login-layout');
        $('#id-text2').attr('class', 'white');
        $('#id-company-text').attr('class', 'blue');

        e.preventDefault();
    });
    $('#btn-login-light').on('click', function(e) {
        $('body').attr('class', 'login-layout light-login');
        $('#id-text2').attr('class', 'grey');
        $('#id-company-text').attr('class', 'blue');

        e.preventDefault();
    });
    $('#btn-login-blur').on('click', function(e) {
        $('body').attr('class', 'login-layout blur-login');
        $('#id-text2').attr('class', 'white');
        $('#id-company-text').attr('class', 'light-blue');

        e.preventDefault();
    });
    $('#login').on('click',function () {
        var account = $('#account').val();
        var password = $('#password').val();
        $.ajax({
            type: "post",
            url:"/user/login",
            data:{
                account:account,
                password:password
            },
            async:true,
            dataType:"json",
            success:function(res){
                console.log("success:"+res.data);
                if ("success"===res.data) location.href = "/my/myIndex";
                else layer.msg(res.msg, {icon: 5});
                return false;
            },
            error:function(err){
                console.log("error:"+err.status);
                console.log(err);
                if ("success"===err.data) location.href = "/my/myIndex";
                else layer.msg(err.msg, {icon: 5});
            }});
        console.log("请求成功");
    });
});