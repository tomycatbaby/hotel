define(['jquery'],function($){
	return{
		mainPage:function(){
			$(function(){
				$.ajax({
					url: '/Hotel/WebApp/Main-body.html',
					type: 'GET',
					dataType: 'html',
					success:function(data){
						$('body').append(data);
						//$('.hidden').before("<script src='/Hotel/WebApp/js/Main-body.js'><script>");
						require(['Main-body'],function(){})
					}
				});
			})
		}
	}
})

