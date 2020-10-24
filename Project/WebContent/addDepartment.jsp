<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>添加部门</title>
</head>

<body>
  <form class="layui-form" action="addDept">
    <div class="layui-form-item">
      <label class="layui-form-label">部门编号：</label>
      <div class="layui-input-block">
        <input type="text" name="did" id="did" required lay-verify="required" placeholder="请输入部门编号" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">部门名称：</label>
      <div class="layui-input-block">
        <input type="text" name="dname" id="dname" required lay-verify="required" placeholder="请输入部门名称" autocomplete="off"
          class="layui-input">
      </div>
    </div>

    <div class="layui-form-item">
      <label class="layui-form-label">备注：</label>
      <div class="layui-input-block">
        <input type="text" name="dremark" placeholder="可选填备注" autocomplete="off" class="layui-input">
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
  //Demo
  layui.use(['layer', 'form', 'table', 'layedit', 'laydate', 'jquery'], function () {
	  var form = layui.form,
	  laydate = layui.laydate,
  	  layer = layui.layer,
  	  $ = layui.jquery;
    
    $(function (){
    	
    	//验证部门编码是否存在
    	$("#did").keyup(function(){
    		var didValue = $("#did").val();
    		didValue = parseInt(didValue);
    		var json = {"did":didValue};
    		//用post方法完成ajax提交数据:url,json,data返回值
    		$.post("didAjax",json,function(data){
    			if(data == "1"){
    				layer.msg("该部门编号已存在");
    				$("#sp1").text("该部门编号已存在");
    			}else{
    				$("#sp1").text("");
    			}
    		})
    	})
    	
    	//验证部门名称是否存在
    	$("#dname").keyup(function(){
    		var dnameValue = $("#dname").val();
    		var json = {"dname":dnameValue};
    		//用post方法完成ajax提交数据:url,json,data返回值
    		$.post("dnameAjax",json,function(data){
    			if(data == "1"){
    				layer.msg("该部门名称已存在");
    				$("#sp2").text("该部门名称已存在");
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
    });

    //监听提交
    form.on('submit(formDemo)', function (data) {
//       layer.msg(JSON.stringify(data.field));
      var did = $("#sp1").html();
      var dname = $("#sp2").html();
      if(did == "该部门编号已存在" || dname == "该部门名称已存在"){
    	  layer.msg("部门编号或名称已存在，请检查后重新输入");
    	  return false;
      }
      return true;
    });
  });
</script>

</html>