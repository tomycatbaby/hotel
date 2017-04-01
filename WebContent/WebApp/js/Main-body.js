define(['jquery'],function($){
	$(function(){
		var $register_username;
		var $register_password;
		var $register_password_confirm;
		var $reg1 = /^[A-Za-z0-9]{3,11}$/;//用户名要求是3~11的位的字母或者数字
		var $reg2 = /^[A-Za-z0-9]{6,11}$/;//密码要求是6~11的位的字母或者数字

		//对于用户是否登录的检测请求
		$.ajax({
			"url": '/Hotel/WebApp/refresh.action',
			"type": 'POST',
			"dataType": 'json',
			"data": {},
			"success":function(data){
				var data = $.parseJSON(data.content);
				if(data[0].status=="1"){
					console.log(data[1].username);
					$('.register').remove();
					$('.login').remove()
					$('.float-info').before("<li class='user'><a href='#'>你好，"+data[1].username+"</a></li>");
				}
			},
			"error":function(){
				console.log("网络错误！");
			}
		});

		//退出登录事件
		$("#logout").on('click', function(event) {
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

		$('.top').on('mouseover','.user', function(event) {
			event.preventDefault();
			console.log($(this))
			$(this).attr('status','active');
			$('.float-info').css("display","block");
		});
		$('.top').on('mouseout', '.user',function(event) {
			event.preventDefault();
			$(this).attr('status','');
			if($('.float-info').attr('status')!="active"){
				$('.float-info').css("display","none");
			}
		});
		$('.float-info').on('mouseover', function(event) {
			event.preventDefault();
			$(this).css("display","block").attr('status','active');
		});
		$('.float-info').on('mouseout', function(event) {
			event.preventDefault();
			$(this).attr('status','');
			if($('.user').attr('status')!="active"){
				$(this).css("display","none");
			}

		});


		//个人信息操作


		//点击关闭按钮事件
		$('.close').on('click', function() {
			$('.hidden').css({
				"visibility":"hidden"
			});
			$('.box').removeClass('box-login-active').removeClass('box-register-active');
			$('.box input').val('');
			$('.error').text('');
		});

		//点击登录按钮事件
		$('.login-button').on('click',login);
		function login (){
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
		$('.register-button').on('click', register);
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
		$('#box_up').on('click',function() {
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
		$('#box_up_r').on('click', function() {
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
		$('#register_username').on('blur',  function(event) {
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
		$('#register_password').on('blur', function(event) {
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
		$('#register_password_confirm').on('blur', function(event) {
			event.preventDefault();
			var $password = $(this).val();
			if($password!=$register_password){
				$('.error-password-confirm').text('两个密码不一致');
			}
			else{
				$('.error-password-confirm').text('');
				$register_password_confirm = $password;
			}
		});

		//鼠标放在导航栏上触发效果
		$('.bar').on('mouseover', 'li', function() {
			event.preventDefault();
			$('.bar li').removeClass('active');
			var $value = $(this).attr("value");
			if ($value=="2") {
				$(this).addClass('active');
				$("#bar_LP").css({
					"left":"2.96rem",
					"width":"0.72rem"
				});
			}
			else if($value==3){
				$(this).addClass('active');
				$("#bar_LP").css({
					"left":"5.48rem",
					"width":"0.72rem"
				});
			}
			else if($value==1){
				$(this).addClass('active');
				$("#bar_LP").css({
					"left":".8rem",
					"width":"0.36rem"
				});
			}
		});

		//鼠标离开导航栏触发效果
		$('.bar').on('mouseout', 'li', function() {
			event.preventDefault();
			var $value = $(this).attr("value");
			if ($value!="1") {
				$(this).removeClass('active');
				$("#bar_first").addClass('active');
			}
			$("#bar_LP").css({
				"left":".8rem",
				"width":"0.36rem"
			});
		});

		//鼠标放在HOTEL触发效果
		$('.hotel-name').on('mouseover', function(event) {
			event.preventDefault();
			$(this).css({
				"color":"#ffc",
				"text-shadow":"0 0 .02rem, 0 0 .02rem"
			});
		});

		//鼠标离开HOTEL触发效果
		$('.hotel-name').on('mouseout',  function(event) {
			event.preventDefault();
			$(this).css({
				"color":"#fff",
				"text-shadow":""
			});
		});
	})
})

