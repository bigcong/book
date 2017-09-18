$(function(){
	//判断是否为苹果手机
	if(/ip(hone|od)|ipad/i.test(navigator.userAgent)){
		//此属性为了防止 delegate 失效
    $("body").css("cursor","pointer");
}
})