<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="logincss/css/icon.css">
<!-- 	<link rel="stylesheet" type="text/css" href="logincss/css/easyui.css"> -->
  
  <script type="text/javascript" src="logincss/js/jquery.min.js"></script>
  <script type="text/javascript" src="logincss/js/jquery.easyui.min.js"></script>
  
  <title>添加员工</title>
</head>

<body>
  <form class="layui-form" action="${pageContext.request.contextPath }/addEmployee">
    <div class="layui-form-item">
      <label class="layui-form-label">员工编号：</label>
      <div class="layui-input-block">
        <input type="text" name="id" id="id" required lay-verify="required" placeholder="请输入员工编号" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">部门编号：</label>
      <div class="layui-input-block">
        <input type="text" name="deptId" id="deptId" required lay-verify="required" placeholder="请输入部门编号" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">职位编号：</label>
      <div class="layui-input-inline">
        <input type="text" name="jobId" id="jobId" required lay-verify="required" placeholder="请输入职位编号" autocomplete="off"
          class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">姓名：</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off"
          class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">身份证号：</label>
      <div class="layui-input-inline">
        <input type="text" name="cardId" id="cardId" required lay-verify="required|identity" placeholder="请输入身份证号" autocomplete="off"
          class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">现住住址：</label>
      <div class="layui-input-inline">
        <input type="text" name="address" id="address" required lay-verify="required" placeholder="请输入现住地址" autocomplete="off"
          class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">邮政编码：</label>
      <div class="layui-input-inline">
        <input type="text" name="postCode" id="postCode" required lay-verify="required|number" placeholder="请输入邮政编码"
          autocomplete="off" class="layui-input">
      </div>
      <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">手机号码：</label>
      <div class="layui-input-block">
        <input type="text" name="tel" id="tel" required lay-verify="required|phonenumber" placeholder="请输入手机号码"
          autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">电话号码：</label>
      <div class="layui-input-block">
        <input type="text" name="phone" id="phone" required lay-verify="required|phonenumber" placeholder="请输入电话号码"
          autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">QQ号码：</label>
      <div class="layui-input-block">
        <input type="text" name="qqNum" id="qqNum" required lay-verify="required|number" placeholder="请输入QQ号码" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">邮箱地址：</label>
      <div class="layui-input-block">
        <input type="text" name="email" id="email" required lay-verify="required|email" placeholder="请输入邮箱地址" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">性别：</label>
      <div class="layui-input-block">
        <input type="radio" name="sex" id="sex" value="男" title="男" checked>
        <input type="radio" name="sex" id="sex" value="女" title="女">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">政治面貌：</label>
      <div class="layui-input-block">
        <select name="party" id="party" lay-verify="required">
          <option></option>
          <option value="团员" name="party" id="party">共青团员</option>
          <option value="党员" name="party" id="party">中共党员</option>
          <option value="群众" name="party" id="party">群众</option>
          <option value="其他" name="party" id="party">其他</option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">出生日期：</label>
      <div class="layui-input-block">
        <input type="text" id="birthday" name="birthday" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">民族：</label>
      <div class="layui-input-block">
        <input type="text" name="race" id="race" required lay-verify="required" placeholder="请输入民族" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">学历：</label>
      <div class="layui-input-block">
        <input type="text" name="education" id="education" required lay-verify="required" placeholder="请输入学历" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">特长：</label>
      <div class="layui-input-block">
        <input type="text" name="speciality" id="speciality" required lay-verify="required" placeholder="请输入特长" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">爱好：</label>
      <div class="layui-input-block">
        <input type="text" name="hobby" id="hobby" required value="吃饭、睡觉、打豆豆" lay-verify="required" placeholder="请输入爱好" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注：</label>
      <div class="layui-input-block">
        <input type="text" name="remark" id="remark" placeholder="可选填备注" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>
  </form>
  <div id="sp1" style="display:none;"></div>
  <div id="sp2" style="display:none;"></div>
</body>
<script src="./layui/layui.js"></script>
<script>
	

</script>
<script>
  //Demo

  layui.use('laydate', function () {
    var laydate = layui.laydate;

    //出生日期时间
    laydate.render({
      elem: '#birthday', //指定元素
      type: 'datetime'
    });
  });

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
        if ($('input[name=password1]').val() !== value)
          return '两次密码输入不一致！';
      },
    });
    
  //页面加载完成事件
	$(function(){
		
		//验证员工编号是否存在
		$("#id").keyup(function(){
    		var idValue = $("#id").val();
    		idValue = parseInt(idValue);
    		var json = {"id":idValue};
    		//用post方法完成ajax提交数据:url,json,data返回值
    		$.post("idAjax",json,function(data){
    			if(data == "1"){
    				layer.msg("该员工编号已存在");
    				$("#sp1").text("该员工编号已存在");
    			}else{
    				$("#sp1").text("");
    			}
    		})
    	})
		
		var addInfo ="${addInfo2}";
		if(addInfo == "0"){
  		  layer.open({
				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
				  title: '错误提示', //页面标题
				  shadeClose: true, //点击遮罩区域是否关闭页面
				  shade: 0.8,  //遮罩透明度
				  content: '添加失败'
			});  
//   		alert("添加失败！");
  		}else if(addInfo == "1"){
  		layer.open({
				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
				  title: '恭喜你', //页面标题
				  shadeClose: true, //点击遮罩区域是否关闭页面
				  shade: 0.8,  //遮罩透明度
				  content: '添加成功'
			}); 
//   		alert("添加成功！");
  		}
		
	});

	//监听提交
    form.on('submit(formDemo)', function (data) {
//       layer.msg(JSON.stringify(data.field));
      var id = $("#sp1").html();
      if(id == "该员工编号已存在"){
    	  layer.msg("员工编号已存在，请重新输入编号");
    	  return false;
      }
      return true;
    });
  });
</script>

</html>