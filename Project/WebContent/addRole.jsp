<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>添加角色</title>
</head>

<body>
  <form class="layui-form" action="${pageContext.request.contextPath}/addRole">
    <div class="layui-form-item">
      <label class="layui-form-label">角色编号：</label>
      <div class="layui-input-block">
        <input type="text" name="rid" required lay-verify="required" placeholder="请输入角色编号" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">角色名称：</label>
      <div class="layui-input-block">
        <input type="text" name="rname" required lay-verify="required" placeholder="请输入角色名称" autocomplete="off"
          class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注：</label>
      <div class="layui-input-block">
        <input type="text" name="rremark" placeholder="可选填备注" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
      </>
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