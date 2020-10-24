<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>添加用户</title>
</head>

<body>
  <form class="layui-form" action="addUser">
    <div class="layui-form-item">
      <label class="layui-form-label">用户编号：</label>
      <div class="layui-input-block">
        <input type="text" name="number" id="number" required lay-verify="required" placeholder="请输入用户编号" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">用户名称：</label>
      <div class="layui-input-block">
        <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名称" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">密码：</label>
      <div class="layui-input-inline">
        <input type="password" name="password" required lay-verify="required|pass" placeholder="请输入密码"
          autocomplete="off" class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">确认密码：</label>
      <div class="layui-input-inline">
        <input type="password" name="password2" required lay-verify="required|confirmPass" placeholder="请再次输入密码"
          autocomplete="off" class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">手机号码：</label>
      <div class="layui-input-block">
        <input type="text" name="phone" id="phone" required lay-verify="required|phone" placeholder="请输入手机号码"
          autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">选择角色：</label>
      <div class="layui-input-block">
        <select name="roleId" id="sel" lay-verify="required">
          <option value=""></option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注：</label>
      <div class="layui-input-block">
        <input type="text" name="remark" placeholder="可选填备注" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
      	<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
      </>
  </form>
  <div id="sp1" style="display:none;"></div>
  <div id="sp2" style="display:none;"></div>
</body>
<script src="./layui/layui.js"></script>

<script>
  //Demo
  layui.use(['layer', 'form', 'table', 'layedit', 'laydate', 'jquery'], function () {
	  var form = layui.form,
		  laydate = layui.laydate,
      	  layer = layui.layer,
      	  $ = layui.jquery;
    
	  form.verify({
	      // 校验密码的长度，确保长度在6-12位之间
	      pass: [
	        /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
	      ],
	      //校验手机号码的格式
	      phonenumber: [
	        /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
	        '手机号码格式不正确'
	      ],

	      // 校验两次密码是否一致
	      confirmPass: function (value) {
	        if ($('input[name=password]').val() !== value)
	          return '两次密码输入不一致！';
	      },
	    });  
	  
 
    $(function () {
    	
    	//验证用户编号是否已存在
    	$("#number").keyup(function(){
    		var numberValue = $("#number").val();
    		var json = {"number":numberValue};
    		//用post方法完成ajax提交数据:url,json,data返回值
    		$.post("numberAjax",json,function(data){
    			if(data == "1"){
    				layer.msg("该用户编号已被注册！");
    				$("#sp1").text("该用户编号已被注册");
    			}else{
    				$("#sp1").text("");
    			}
    		})
    	})
    	
    	//验证手机号是否已存在
    	$("#phone").keyup(function(){
    		var phoneValue = $("#phone").val();
    		var json = {"phone":phoneValue};
    		//用post方法完成ajax提交数据:url,json,data返回值
    		$.post("phoneAjax",json,function(data){
    			if(data == "1"){
    				layer.msg("该手机号已被注册！");
    				$("#sp2").text("该手机号已被注册");
    			}else{
    				$("#sp2").text("");
    			}
    		})
    	})
    	
    	var addInfo = "${addInfo}";
    	if(addInfo == "0"){
    		  layer.open({
				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
				  title: '错误提示', //页面标题
				  shadeClose: true, //点击遮罩区域是否关闭页面
				  shade: 0.8,  //遮罩透明度
				  content: '添加失败'
			});  
//     		alert("添加失败！");
    	}else if(addInfo == "1"){
    		layer.open({
				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
				  title: '恭喜你', //页面标题
				  shadeClose: true, //点击遮罩区域是否关闭页面
				  shade: 0.8,  //遮罩透明度
				  content: '添加成功'
			}); 
//     		alert("添加成功！");
    	}
    	
    	//角色的数据回显
       //页面打开时异步加载数据,查询信道
       $.ajax({
           type: "get",
           url: "findRoles",
           datatype: "json",
           success: function (data) {
        	   
               $.each(data, function (index, item) {
//                    alert(index);
//                    alert(item.rname);
                   $('#sel').append(new Option(item.rname,item.rid));
               });
               layui.form.render("select");
           },error: function () {
               alert("查询信道失败");
           }
       });
    });

    //监听提交
   form.on('submit(formDemo)', function (data) {
//       layer.msg(JSON.stringify(data.field));
	   var number = $("#sp1").html();
	   var phone = $("#sp2").html();
	   if(number == "该用户编号已被注册" || phone == "该手机号已被注册"){
		   layer.msg("用户编号或手机号已被注册，请检查后重新输入");
		   return false;
	   }
      return true;
    });
  });
</script>

</html>