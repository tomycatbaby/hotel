define(['jquery'],function($){
	return{
		mainAbout:function(){
			$(function(){
				$.ajax({
					url: '/Hotel/WebApp/Main-body.html',
					type: 'GET',
					dataType: 'html',
					success:function(data){
						$('body').append(data);
						$('.info').remove();
					
						//$('.hidden').before("<script src='/Hotel/WebApp/js/MainBody.js'><\/script>");
						require(['Main-body'],function(){
							$(".bar li:nth-child(1)").removeClass('active');
							$(".bar li:nth-child(3)").addClass('active');
							$('.bar').on('mouseout', 'li', function() {
								event.preventDefault();
								var $value = $(this).attr("value");
								if ($value!="3") {
									$(this).removeClass('active');
									$(".bar li:nth-child(3)").addClass('active');
								}
								$("#bar_LP").css({
									"left":"5.48rem",
									"width":"0.72rem"
								});
							});
						});
					}
				});
				
			})
		}
	}
	
})
