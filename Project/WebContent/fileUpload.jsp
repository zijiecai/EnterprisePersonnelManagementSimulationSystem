<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>文件上传</title>
</head>

<body>
  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>文件上传</legend>
  </fieldset>
  <div class="layui-upload">
    <button type="button" class="layui-btn layui-btn-normal" id="chooseFile">选择文件</button>
    <button type="button" class="layui-btn" id="Upload">开始上传</button>
  </div>
</body>
<script src="./layui/layui.js"></script>
<script>
//文件上传
//选完文件后不自动上传
/*
upload.render({
  elem: '#chooseFile',//指定选择文件的容器
  url: '/upload/',//服务端上传接口
  method:'post',//类型
  auto: false,//不自动上传
  data:{'resourceDate':$("#resourceDate").val()},//额外传输的数据
  accept:'file',//指定上传时校验的文件类型，file：所有文件
  size:10240,//最大大小，单位kb，不支持ie8/9
  //multiple: true,是否允许多文件上传
  bindAction: '#uploadFile',//指定上传的容器
  done: function(res,index,upload){
    console.log(res);//服务器响应信息
    console.log(index);//当前文件的索引
    console.log(upload);//重新上传的方法
  },
  error:function(index,upload){
    console.log(index);//当前文件的索引
    console.log(upload);//重新上传的方法
  }
});*/
  layui.use('upload', function () {
    var $ = layui.jquery,
      upload = layui.upload;
    //选完文件后不自动上传
    upload.render({
      elem: '#chooseFile',
      url: '${pageContext.request.contextPath}/upload', //改成您自己的上传接口
      size:10240,//最大大小，单位kb，不支持ie8/9
      auto: false,
        //,multiple: true
       field:'mf',
      accept: 'file',
      bindAction: '#Upload',
      done: function(res,index,upload){
          if(res.code ="0"){
            return layer.msg('上传成功');
          }
          else{
        	  return layer.msg('上传成功');
          }
    	  
          //上传成功
    	  },
    	  error:function(index,upload){
    		  return layer.msg('上传失败');
    	  }
        
    });
  });
</script>

</html>