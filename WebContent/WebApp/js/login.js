define(['jquery'],function($){
//退出登录事件
    $(document).on('click',".logout", function(event) {
        event.preventDefault();
        $.ajax({
            url: '/Hotel/loginOut.action',
            type: 'GET',
            dataType: 'json',
            data: {},
            success:function(){
                location.reload();
            }
        })
    });


//点击关闭按钮事件
    $(document).on('click', '.close',function() {
        $('.hidden').css({
            "visibility":"hidden"
        });
        $('.box').removeClass('box-login-active').removeClass('box-register-active');
        $('.box input').val('');
        $('.error').text('');
    });

//点击登录按钮事件
    $(document).on('click','.login-button',login);
    function login (){
        console.log(1);
        $('.hidden').css({
            "visibility":"visible"
        });
        $('.box').addClass('box-login-active').removeClass('box-register-active');
        $('.box-hotel-register').css({
            "font-size":".16rem",
            "color":"#7f1f59",
            "margin-top":".24rem"
        });
        $('.box-hotel-login').css({
            "font-size":".2rem",
            "color":"#000",
            "margin-top":".21rem"
        });
        $('.login-box').css({
            "display":"block"
        });
        $('.register-box').css({
            "display":"none"
        });
    }

//点击注册按钮事件
    $(document).on('click','.register-button', register);
    function register(){
        $('.hidden').css({
            "visibility":"visible"
        });
        $('.box').addClass('box-register-active').removeClass('box-login-active');
        $('.box-hotel-login').css({
            "font-size":".16rem",
            "color":"#7f1f59",
            "margin-top":".24rem"
        });
        $('.box-hotel-register').css({
            "font-size":".2rem",
            "color":"#000",
            "margin-top":".21rem"
        });
        $('.register-box').css({
            "display":"block"
        });
        $('.login-box').css({
            "display":"none"
        });
    }

//登录事件
    $(document).on('click','#box_up',function() {
        var $username = encodeURI($("#username").val());
        var $password = $("#password").val();
        $.ajax({
            "type":"POST",
            "url":"/Hotel/login.action",
            "dataType":"json",
            "data":{"username":$username,"password":$password},
            "contentType":"application/x-www-form-urlencoded; charset=UTF-8",
            "success":function(data){
                var data = $.parseJSON(data.content);
                if(data[0].status=="1"){
                    $('#box_up').before('<p class="login-success">登陆成功！</p>');
                    setTimeout(function(){
                        location.reload();
                    },500)
                }
                else{
                    $('#box_up').before('<p class="login-error">用户名或者密码错误！</p>');
                }
            },
            "error":function(){
                alert("网络不畅，请稍后再试！");
            }
        })
    });

//注册事件
    $(document).on('click','#box_up_r', function() {
        $.ajax({
            "type":"GET",
            "url":"/Hotel/register.action",
            "dataType":"json",
            "contentType":"charset=UTF-8",
            "data":{"username":$register_username,"password":$register_password},
            "beforeSend":function(){
                if($('.error').text()!=""||$register_username==""||$register_password==""||$register_password_confirm==""){
                    return false;
                }
            },
            "success":function(data){
                var data = $.parseJSON(data.content);
                console.log(data[0].username);
                $('#box_up_r').before('<p class="login-success">注册成功！</p>');
                setTimeout(function(){
                    location.reload();
                },500)
            },
            "error":function(){
                alert("网络不畅，请稍后再试！");
            }
        })
    });

//检测用户名是否被占用和是否符合规范
    $(document).on('blur','#register_username',  function(event) {
        event.preventDefault();
        var $username = $(this).val();
        if($username==""){
            $('.error-username').text('用户名不能为空');
        }
        else if(!$reg1.test($username)) {
            $('.error-username').text('用户名不合规范');
        }
        else{
            $register_username = encodeURI($username);
            $('.error-username').text('');
            $.ajax({
                "url": '/Hotel/check.action',
                "type": 'GET',
                "dataType": 'json',
                "data": {"username": $register_username},
                "success":function(data){
                    var data = $.parseJSON(data.content);
                    if(data[0].status=="0"){
                        $('.error-username').text('用户名已被占用');
                    }
                    else if(data[0].status=="1"){
                        $('.error-username').text('');
                    }
                }
            })
        }
    });

//检测密码是否符合规范
    $(document).on('blur', '#register_password',function(event) {
        event.preventDefault();
        var $password = $(this).val();
        if($password==""){
            $('.error-password').text('密码不能为空');
        }
        else if(!$reg2.test($password)){
            $('.error-password').text('密码不合规范');
        }
        else{
            $('.error-password').text('');
            $register_password = $password;
        }
    });

//检测两次密码输入是否一致
    $(document).on('blur','#register_password_confirm', function(event) {
        event.preventDefault();
        var $password = $(this).val();
        if($register_password_confirm!=$register_password){
            $('.error-password-confirm').text('两个密码不一致');
        }
        else{
            $('.error-password-confirm').text('');
            $register_password_confirm = $password;
        }
    });
})

