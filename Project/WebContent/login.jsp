<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="logincss/css/square.css">
<link rel="stylesheet" type="text/css" href="logincss/css/login.css">
<link rel="stylesheet" type="text/css" href="logincss/css/styles_yellow.css"> 
<link rel="stylesheet" type="text/css" href="logincss/css/supersized.css">

<link rel="stylesheet" type="text/css" href="logincss/css/icon.css">
<link rel="stylesheet" type="text/css" href="logincss/css/easyui.css">

<script type="text/javascript" src="logincss/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="logincss/js/supersized.3.2.7.min.js"></script>
<script type="text/javascript" src="logincss/js/supersized-init.js"></script>
<script type="text/javascript" src="logincss/js/jquery.min.js"></script>
<script type="text/javascript" src="logincss/js/jquery.easyui.min.js"></script>

<style type="text/css">
	p{ color:darkred; font-size:12px;text-align:center;margin-bottom:10px; }
	a{text-align:center;}
	button{font-size:15px;font-weight:bold;}
	#btnSendCode1{
				width: 90px;
				height: 30px;  
				padding: 0 5px;
				margin: 0;
				border:1px solid white;
				font-size: 14px;
				text-align: center;
				background: transparent;
				border-radius: 30px;
				color: #FAFFF0;
				/* float:left; */
				margin:10px;	 
			}
</style>	
<title>Login</title>
</head>
<body>
<div class="jq22-container" style="padding:100px;">
		<div class="login-wrap" id="particles-js">
			<div class="login-html">
			<p id="tip">${tip}</p>
			<p id="teltip"></p>
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
				<label for="tab-1" class="tab" style="margin-left:25%;">Sign In</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up">
				<label for="tab-2" class="tab">Sign Up</label>
				<div class="login-form">
					<!-- 登录 -->
					<div class="sign-in-htm">
					<form action="${pageContext.request.contextPath}/login" method="post" id="lf">
						<div class="group">
							<label for="number" class="label">账号 | 手机</label>
							<input id="number" type="text" class="input" name="number" required="required" placeholder="账号为纯数字">
						</div>
						<div class="group">
							<label for="pass" class="label">密码</label>
							<input id="pass" type="password" class="input" data-type="password" name="password" placeholder="请输入密码"  required="required" >
						</div>
						<div class="group">
							<label for="code_input" class="label">验证码</label>
							<input type="text" id="code_input" value="" class="input"  placeholder="请输入验证码" required="required" />
							<input type="hidden" id="checkyzm" value="" />
							验证码
							<br>
							<div id="v_container" style="width: 200px;height: 50px;margin:0 auto;"></div>
							
						</div>	
						<div class="group">
							<br>
							<a href="javascript:void(0)" id="log_button"  class="button">Sign In</a>
						</div>
						<div class="hr"></div>
						</form>
					</div>
					<!-- 登录end -->
					<!-- 注册 -->
					<form method="post" action="${pageContext.request.contextPath}/reg" id="rf" onsubmit="return submitForm()">
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">用户名</label>
							<input id="user" type="text" class="input" name="username" required="required" value="${username}" placeholder="请输入用户名">
						</div>
						<div class="group">
							<label for="rpass" class="label">密码</label>
							<input id="rpass" type="password" class="input" data-type="password" name="password" required="required" placeholder="请输入密码">
						</div>
						<div class="group">
							<label for="phone1" class="label">手机</label>
							<input id="phone1" type="text" class="input" name="phone" required="required" value="${tel}" placeholder="请输入手机号码">
						</div>
						<div class="group">
							<label for="msg" class="label">短信验证码</label>
							<input id="msg" type="text" class="input" style="width:260px;float:left;margin-bottom:30px;" required="required" placeholder="请输入验证码">
							<input id="btnSendCode1" type="button" class="btn btn-default" value="获取验证码" onClick="" />
							<input id="code" name="code" type="hidden">
						</div>
						<br>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span>
							<a href="#" style="font-size:15px;">&nbsp;&nbsp;接受人事管理系统协议</a></label>
						</div>
						<div class="group">
							<button id="reg_btn" class="button" type="submit"><b>SIGN UP</b></button>
						</div>
						<div class="hr"></div>
					</div>
					</form>
					<!-- 注册end -->
				</div>
			</div>
		</div>
	</div>
	
</body>
<!-- scripts -->
<script src="logincss/js/particles.min.js"></script>
<script src="logincss/js/app.js"></script>
<script src="logincss/js/gVerify.js"></script>

<script>
$(function (){
	var user = "${user.username}";
	if(user == null||user == ""){
		var loginInfo = "${loginInfo3}";
		if(loginInfo!=null && loginInfo!=""){
			$.messager.alert('登录失败',"fail",'error');
		}
	}
	
});
</script>


<script>
/* $("#tip").text(""); */

$(function(){

	var verifyCode = new GVerify("v_container");//验证码

	$("#log_button").click(function(){
		if($("#number").val()==""||$("#pass").val()==""){
			$("#tip").text("账户和密码不能为空！");
		}
		else{
			var res = verifyCode.validate(document.getElementById("code_input").value);
		if(res){
			$("#lf").submit();
			//return true;
		}else{			
			$("#tip").text("验证码有误");
			//return false;
		}}
		
	})
})
				
//----------------------------------------------	
$(function(){
//检测手机号码是否重复	
$("#phone1").blur(function(){
		var phone = $.trim($('#phone1').val());
		var json = {
				"tel" : phone};	
		//alert(phone);
			$.post("${pageContext.request.contextPath}/reg/telTest",json,function(data){
				
				if(data == "1"){
					//alert("手机号已被注册")
					$("#teltip").text("手机号已被注册");

				}else{
					$("#teltip").text("");
					$('#tele').val(phone);
				}				
			}) 			 
	})
	$("#tab-1").click(function(){
		$("#teltip").text("");
	})
	$("#tab-2").click(function(){
		$("#tip").text("");
	})
//监听“发送短信”按钮------------------------------------
$("#btnSendCode1").click(function(){
	if($("#teltip").text()=="手机号已被注册"){
		//$.messager.alert('Info','手机号已被注册,无法发送短信','info');
		//$.MsgBox.Alert("消息", "手机号已被注册,无法发送短信");
		$("#teltip").text("手机号已被注册,无法发送短信");
	}
	else{
		sendMessage1();
	}
})

})	
</script>
<script>
			var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;//手机号正则 
			var count = 60; //间隔函数，1秒执行
			var InterValObj1; //timer变量，控制时间
			var curCount1;//当前剩余秒数
			/*第一*/
			function sendMessage1() {
				
				curCount1 = count;		 		 
				var phone = $.trim($('#phone1').val());//获取手机号码的值
				if (!phoneReg.test(phone)) {
					alert(" 请输入有效的手机号码"); 
					return false;
				}
				//设置button效果，开始计时
				$("#btnSendCode1").attr("disabled", "true");
				$("#btnSendCode1").val( + curCount1 + "秒再获取");
				InterValObj1 = window.setInterval(SetRemainTime1, 1000); //启动计时器，1秒执行一次
				//向后台发送处理数据------------------			
				var json = {
					"phone" : phone};
				
				$.post("${pageContext.request.contextPath}/reg/send",json,function(data){
					$("#code").val(data);
				}) 				
				//end--------------------------------	 
			}
			function SetRemainTime1() {
				if (curCount1 == 0) {                
					window.clearInterval(InterValObj1);//停止计时器
					$("#btnSendCode1").removeAttr("disabled");//启用按钮
					$("#btnSendCode1").val("重新发送");
				}
				else {
					curCount1--;
					$("#btnSendCode1").val( + curCount1 + "秒再获取");
				}
			} 
			
/*注册提交------------------------------------------------------------------------*/
			function submitForm(){
				var msg = $.trim($('#msg').val());
				var code = $("#code").val();
				var teltip = $.trim($('#teltip').val());
				
				 if(msg!=code){
					$("#tip").text("手机验证码不匹配，请重试");
					return false;
				} 
				 if(teltip=="手机号已被注册"){
					return false;
				}
				if(!$("#check").is(':checked')){
					$("#tip").text("未签署协议");
					return false;
				}
				return true;
				
			}
</script>
</html>