define(['jquery'],function($){
	return{
		MyHotel:function(){
			$(function(){
				var $money,$username;
				$.ajax({
					url: '/Hotel/WebApp/My-header.html',
					type: 'GET',
					dataType: 'html',
					data: {},
					success:function(data){
						$('body').append(data);
						$.ajax({
							url: '/Hotel/WebApp/refresh.action',
							type: 'GET',
							dataType: 'json',
							data: {},
							success:function(data){
								var data = $.parseJSON(data.content);
								if(data[0].status=="1"){
									$username = data[1].username;
									$money = data[1].money;
									$.ajax({
										url: '/Hotel/WebApp/My-body.html',
										type: 'GET',
										dataType: 'html',
										data: {},
										success:function(data){
											$('body').append(data);
											$("#money").text($money);
											$('.username').text($username);
											console.log($username);
											$('.state').on('click', 'li', function(event) {
												event.preventDefault();
												$(this).css({
													"color": "#651c4d",
													"border-top": ".02rem solid #d987bb",
													"margin-bottom": "-.01rem",
													"background": "#fff",
													"line-height": ".39rem"
												}).siblings().css({
													"color": "#000",
													"border-top": ".01rem solid #eeeae9",
													"margin-bottom": "0rem",
													"background": "#fcfafa",
													"line-height":".41rem"
												})
											});
											$.ajax({
												url: '/Hotel/WebApp/getOrder.action',
												type: 'GET',
												dataType: 'json',
												data: {},
												success:function(data){
													var data =  $.parseJSON(data.content);
													var $startDay = (data[0].startDay).slice(0,4)+"/"+(data[0].startDay).slice(5,7)+"/"+(data[0].startDay).slice(8,10);
													var $finalDay = (data[0].finalDay).slice(0,4)+"/"+(data[0].finalDay).slice(5,7)+"/"+(data[0].finalDay).slice(8,10);
													$("#orderId").text(data[0].orderId);
													$("#roomId").text(data[0].roomId);
													$("#name").text(data[0].name);
													$("#totalMoney").text("¥" + data[0].totalMoney);
													$("#status").text(data[0].Status);
													$("#bookDay").text(data[0].bookDay);
													$("#startDay").text($startDay);
													$("#finalDay").text($finalDay);
												}
											})
										}
									})
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
								url: '/Hotel/loginOut.action',
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
				});
			})
		}
	}
})
