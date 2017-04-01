//require.config({
//	baseUrl:"js",
//	paths:{
//		"jquery":"iframe/jquery-2.1.4.min",
//		"jqueryForm":"iframe/jquery.form.min",
//		"vue":"iframe/vue",
//		"Main-page":"Main-page",
//		"Main-body":"Main-body",
//		"about":"about",
//		"My-hotel":"My-hotel",
//		"My-page":"My-page",
//		"My-selfInfo":"My-selfInfo",
//		"orderHotel":"Order-hotel",
//		"orderInfo":"Order-info",
//		"login":"login"
//	},
//	shim:{
//		"jqueryForm":['jquery']
//	}
//});

define(['common'],function(){
	require(['orderInfo'],function(orderInfo){
		orderInfo.orderInfo();
	});
});
