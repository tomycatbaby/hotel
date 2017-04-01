define(['jquery','jqueryForm'],function($){
	return{
		MyselfInfo:function(){
			$(function(){
				var $username;
				$.ajax({
					url: '/Hotel/WebApp/My-header.html',
					type: 'GET',
					dataType: 'html',
					data: {},
					success:function(data){
						$('.inner').before(data);
						$.ajax({
							url: '/Hotel/WebApp/refresh.action',
							type: 'GET',
							dataType: 'json',
							data: {},
							success:function(data){
								var data = $.parseJSON(data.content);
								if(data[0].status=="1"){
									$username = data[1].username;
									$('.username').text($username);
								}
								else{
									alert("你还未登录！");
									location.href="/Hotel/WebApp/Main-page.html"
								}
							},
							error:function(){
								$('#username').text("NULL");
							}
						});
						$("#logout").on('click', function(event) {
							event.preventDefault();
							$.ajax({
								url: '/Hotel/WebApp/loginOut.action',
								type: 'GET',
								dataType: 'json',
								data: {},
								success:function(){
									location.href="/Hotel/WebApp/Main-page.html"
								}
							})
						});
						$("#logo").on('click',  function(event) {
							event.preventDefault();
							location.href="/Hotel/WebApp/Main-page.html"
						});
						$("#slogan").on('click',  function(event) {
							event.preventDefault();
							location.href="/Hotel/WebApp/My-hotel.html"
						});
					}
				})

				

				$(".dir ul li:last-child").after("<li>&gt;</li><li><a href='#'>个人中心</a></li>");
				$(".dir ul li:last-child a").hover(function(){
					$(this).css({
						"color": "#000",
						"cursor": "text"
					});
				});

				$(".img-up").on('click', function(event) {
					event.preventDefault();
					$("#img_up").trigger('click');
				});
				$("#img_up").on('change',  function(event) {
					event.preventDefault();
					$("#img_file").ajaxSubmit({
						url:"/Hotel/WebApp/fileupload.action",
						type:"POST",
						dataType:"json",
						success:function(data){
							var data = $.parseJSON(data.content);
							$(".img-up img").prop("src",data[0].image);
						}
					})
				});
				$("#saveUser").ajaxForm({
					url:"/Hotel/WebApp/saveUser.action",
					type:"POST",
					dataType:"json",
					success:function(data){
						var data = $.parseJSON(data.content);
						if (data[0].status==1){
							location.reload();
						}
					}
				});

				$.ajax({
					url:"/Hotel/WebApp/getUser.action",
					type:"POST",
					dataType:"json",
					success:function(data){
						var data = $.parseJSON(data.content);
						if (data[0].userStatus == "0"){
							$("#username").val(data[0].name).prop("disabled","disabled").css("border","none");
							$("#id_number").val(data[0].idCard).prop("disabled","disabled").css("border","none");

							$("#choose_id").before("<input type='text' style='border: none' value='居民身份证'>").remove();
							$("#choose_sex").before("<input type='text' style='border: none' value='"+data[0].sex+"'>").remove();

							$("#nophoto").prop("src",data[0].image);
							$("#phone_number").val(data[0].cellphone);

						}
					}
				})
			})
		}
	}
})
