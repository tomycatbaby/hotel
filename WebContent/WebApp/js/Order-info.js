define(['jquery','login'],function($){
	return{
		orderInfo:function(){
            var url = (location.href).toString();
            if(url.indexOf("ID")!=-1){
                var $id = url.split("ID=");
            }
            $.ajax({
                url:'/Hotel/WebApp/Order-header.html',
                type:"POST",
                dataType:"html",
                success:function(data){
                    $(".container").before(data);
                }
            });
            $.ajax({
                url:'/Hotel/WebApp/Order-footer.html',
                type:"POST",
                dataType:"html",
                success:function(data){
                    $(".container").after(data);
                }
            });
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
                        $('.login').remove();
                        $('.logo-bar').after("<li class='user'><a href='#'>"+data[1].username+"</a></li>");
                        $(".logo-bar").after("<li class='logout'><a href='#'>退出</a></li>");
                    }
                    else{
                    	alert("请登录之后操作");
                    }
                },
                "error":function(){
                    console.log("网络错误！");
                }
            });

            //加载订单信息
            $.ajax({
                url:"",
                type:"POST",
                "dataType":"json",
                data:{"ID":$id},
                success:function(data){
                    var data = $.parseJSON(data.content);
                    console.log(data);
                }
            })

            //取消订单
            $("#pay_cancel").on('click',function(){
                $.ajax({
                    url:"",
                    type:"POST",
                    "dataType":"json",
                    data:{"ID":$id},
                    success:function(data){
                        var data = $.parseJSON(data.content);
                        console.log(data);
                    }
                })
            })


            $("#pay").on('click',function(){
                var $price = $("#money_left").text()
                $.ajax({
                    url:"",
                    type:"POST",
                    "dataType":"json",
                    data:{"ID":$id,"price":$price},
                    success:function(data){
                        var data = $.parseJSON(data.content);
                        console.log(data);
                        $(".error").remove();
                        if(data[0].status==0){
                            $("#pay").after("<span class='error'>您的余额不足</span>")
                        }
                        else{

                        }
                    }
                })
            })
		}
	}
})