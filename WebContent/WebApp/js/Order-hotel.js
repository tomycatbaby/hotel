/**
 * Created by tan on 2016/11/18.
 */
define(['jquery','jqueryForm','login'],function($){
    return {
        orderHotel:function(){
            $(function(){
                var $register_username;
                var $register_password;
                var $register_password_confirm;
                var $reg1 = /^[A-Za-z0-9]{3,11}$/;//用户名要求是3~11的位的字母或者数字
                var $reg2 = /^[A-Za-z0-9]{6,11}$/;//密码要求是6~11的位的字母或者数字
                var model = `<div class="hotel">
                                <div class="pic">
                                    <img src="images/hotel.jpg" alt="">
                                </div>
                                <div class="hotelBox">
                                    <div class="info">
                                        <p class="title" id="title">A1</p>
                                        <p class="address" id="address">潘阿姨5L</p>
                                        <div class="saying">
                                            <span class="saying-title">大家说:</span>
                                            <span class="content">位置优越</span>
                                        </div>
                                        <div class="provide">
                                            <img src="images/wifi.png" 
                                            class="wifi">
                                            <img src="images/wifi.png" class="wifi">
                                            <img src="images/wifi.png" class="wifi">
                                            <img src="images/wifi.png" class="wifi">
                                        </div>
                                        <p></p>
                                    </div>

                                </div>
                                <div class="area">
                                    <div class="price">
                                        <span>¥</span>
                                        <span id="price" class="price_num">233</span>
                                    </div>
                                    <div class="score">
                                        <span id="score" class="score_num">4.8</span><span>/5分</span>
                                    </div>
                                    <div class="comment">
                                        <span>共</span><span id="#comment" class="comment_num">5</span><span>条评论</span>
                                    </div>
                                    <div class="count">
                                        <span>已入住:</span><span class="spare">2人</span><span class="total">/3人</span>
                                    </div>
                                    <button class="detail">查看详情</button>
                                </div>
                            </div>`

                function sort(data,length){
                    $(".hotel-list").empty();
                    $("#result_num").text(length);
                    for(var i = 0;i < length;i++){
                        $(".hotel-list").append(model);
                        $(".address").eq(i).text(data[i].direction);
                        $(".price_num").eq(i).text(data[i].price);
                        $(".title").eq(i).text(data[i].roomId);
                        $(".score_num").eq(i).text(data[i].grade);
                        $(".comment_num").eq(i).text(data[i].comment);
                        $(".spare").eq(i).text(data[i].sparelive);
                        $(".hotel").eq(i).data("ID",data[i].roomId);
                    }
                }
                var choice_option = {
                    url:"chooseRoom.action",
                    type:"POST",
                    dataType:"json",
                    success:function(data){
                        var data = $.parseJSON(data.content);
                        var length = data.length;
                        //console.log(data.length);

                        sort(data,length);
                    }
                };

                $.ajax({
                    url:'/Hotel/WebApp/Order-header.html',
                    type:"POST",
                    dataType:"html",
                    success:function(data){
                        $(".bodyer").before(data);
                    }
                });
                $.ajax({
                    url:'/Hotel/WebApp/Order-footer.html',
                    type:"POST",
                    dataType:"html",
                    success:function(data){
                        $(".bodyer").after(data);
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
                    },
                    "error":function(){
                        console.log("网络错误！");
                    }
                });


                //input[type:checkbox]的唯一选项判定
                $("input[type=checkbox]").on('click',function(){
                    $(this).parent().siblings("span").find("input").prop("checked",false);
                    var $name = $(this).prop("id");
                });


                //朝北不允许有双人间
                $("#north").on("click",function(){
                    $("#ultra").prop({
                        "disabled":false
                    }).siblings("label").css("color","#000");
                    if($(this).prop("checked") == true){
                        $("#double").prop("disabled",true).siblings().css("color","#999");
                    }
                    else {
                        $("#double").prop("disabled",false).siblings().css("color","#000");
                    }
                });

                //朝南不允许有四人间
                $("#south").on("click",function(){
                    $("#double").prop({
                        "disabled":false
                    }).siblings("label").css("color","#000");
                    if($(this).prop("checked") == true){
                        $("#ultra").prop("disabled",true).siblings().css("color","#999");
                    }
                    else {
                        $("#ultra").prop("disabled",false).siblings().css("color","#000");
                    }
                });

                //双人间不允许朝北
                $("#double").on("click",function(){
                    $("#south").prop({
                        "disabled":false
                    }).siblings().css("color","#000");
                    if($(this).prop("checked") == true){
                        $("#north").prop("disabled",true).siblings().css("color","#999");
                    }
                    else {
                        $("#north").prop("disabled",false).siblings().css("color","#000");
                    }
                });

                //四人间不允许朝南
                $("#ultra").on("click",function(){
                    $("#north").prop({
                        "disabled":false
                    }).siblings().css("color","#000");
                    if($(this).prop("checked") == true){
                        $("#south").prop("disabled",true).siblings().css("color","#999");
                    }
                    else {
                        $("#south").prop("disabled",false).siblings().css("color","#000");
                    }
                });

                //三人间朝向无要求
                $("#triple").on("click",function(){
                    $(".choice").eq(0).find("input").prop({
                        "disabled":false
                    }).siblings().css("color","#000");
                });

                //清除选项
                $(".clean").on("click",function(){
                    $(this).siblings(".lab").find("input").prop({
                        "disabled":false,
                        "checked":false
                    }).find("label").css({
                        "color":"#000"
                    })
                });

                $(".hotel-list").on('mouseover','.hotel',function(){
                    $(this).addClass("hotel-hover").find(".detail").addClass("detail-hover");
                });
                $(".hotel-list").on('mouseout','.hotel',function(){
                    $(this).removeClass("hotel-hover").find(".detail").removeClass("detail-hover");
                });

                //默认排序
                $("#default_sort").on('click', function() {
                    $(this).siblings("li").removeClass("active");
                    $(this).addClass("active");
                    $.ajax({
                        type:"POST",
                        url:"",
                        data:{"type":"defaultSort"},
                        dataType:"json",
                        success:function(data){

                        }
                    })
                });

                //价格低到高排序
                $("#price_low_hight").on('click', function() {
                    $(this).siblings("li").removeClass("active");
                    $(this).addClass("active");
                    $.ajax({
                        type:"POST",
                        url:"/Hotel/WebApp/sortByPrice.action",
                        data:{"type":"priceLowHight"},
                        dataType:"json",
                        success:function(data){
                            var data = $.parseJSON(data.content);
                            var length = data.length;
                            sort(data,length);
                        }
                    })
                });

                //评分低到高排序
                $("#score_low_height").on('click', function() {
                    $(this).siblings("li").removeClass("active");
                    $(this).addClass("active");
                    $.ajax({
                        type:"POST",
                        url:"/Hotel/WebApp/sortByGrade.action",
                        data:{"type":"scoreLowHight"},
                        dataType:"json",
                        success:function(data){
                            var data = $.parseJSON(data.content);
                            var length = data.length;
                            sort(data,length);
                        }
                    })
                });

                //选项及时响应
                $("#choice_form").on('click', "input", function(event) {
                   $("#choice_form").submit();
                });

                setTimeout(function(){
                    $("#choice_form").trigger('submit');
                },1);

                //阻止表单提交
                $('#choice_form').on('submit', function(event) {
                    event.preventDefault();
                    $(this).ajaxSubmit(choice_option)
                });

                $(document).on('click','.hotel',function(){
                    var url = "/Hotel/WebApp/Order-info.html?" + "ID="+$(this).data("ID");
                    location.href = url;
                })
             });
        }
    }
})






