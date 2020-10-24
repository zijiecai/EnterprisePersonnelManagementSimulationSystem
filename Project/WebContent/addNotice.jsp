<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>添加公告</title>
</head>

<body>
  <form class="layui-form" action="${pageContext.request.contextPath}/addNotice">
    <div class="layui-form-item">
      <label class="layui-form-label">公告编号：</label>
      <div class="layui-input-block">
        <input type="text" name="id" required lay-verify="required" placeholder="请输入公告编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">标题：</label>
      <div class="layui-input-block">
        <input type="text" name="name" required lay-verify="required" placeholder="请输入公告标题" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">主题：</label>
      <div class="layui-input-block">
        <input type="text" name="title" required lay-verify="required" placeholder="请输入公告主题" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item layui-form-text">
      <label class="layui-form-label">公告内容：</label>
      <div class="layui-input-block">
        <textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
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
        <button class="layui-btn" lay-submit lay-filter="formDemo">发布公告</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>
  </form>
</body>
<script src="./layui/layui.js"></script>
<script>
  //Demo
  layui.use('form', function () {
    var form = layui.form;
    var addInfo =  "${addInfo}";
    if(addInfo!=""){
    	layer.open({
      	  title: '提示'
      	  ,content: addInfo
      	}); 
    }
    //监听提交
    /*
    form.on('submit(formDemo)', function (data) {
      layer.msg(JSON.stringify(data.field));
      return false;
    });*/
  });
</script>

</html>