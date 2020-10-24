<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>文件下载</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css" media="all">
</head>

<body>
  <form class="layui-form" action="${pageContext.request.contextPath}/findUploadFiles" method="post"></form>
  <div class="layui-inline">
    <label for="uploadName" class="layui-form-label">上传者：</label>
    <div class="layui-input-inline">
      <input type="text" name="uploadName" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label for="fileName" class="layui-form-label">文件名称：</label>
    <div class="layui-input-inline">
      <input type="text" name="fileName" class="layui-input">
    </div>
  </div>
  <button class="layui-btn" lay-submit lay-filter="formDemo">查询</button>
  </form>
  <table class="layui-hide" id="test" lay-filter="test"></table>

  <script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
      <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
      <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
      <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
  </script>

  <script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs"  lay-event="download" url="baidu.com" >下载</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  </script>


  <script src="./layui/layui.js" charset="utf-8"></script>

  <script>
  layui.use(['table','form','jquery'], function () {
      var table = layui.table;
      var $ = layui.jquery;
      var form = layui.form;

      table.render({
        elem: '#test',
        url: '${pageContext.request.contextPath}/findUploadFiles',
        toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
          ,
        defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
          title: '提示',
          layEvent: 'LAYTABLE_TIPS',
          icon: 'layui-icon-tips'
        }],
        title: '用户数据表',
        cols: [
          [{
            type: 'checkbox',
            fixed: 'left'
          }, {
            field: 'id',
            title: '文件编号',
            width: 120,
            fixed: 'left',
            unresize: true,
            sort: true
          }, {
            field: 'userNum',
            title: '上传者编号',
            width: 200,
          }, {
            field: 'uploadname',
            title: '上传者名称',
            width: 150,
          }, {
            field: 'filename',
            title: '文件名称',
            width: 380,
          }, {
            field: 'uploaddate',
            title: '上传时间',
            width: 200,
            templet: "<div>{{layui.util.toDateString(d.uploaddate, 'yyyy-MM-dd HH:mm:ss')}}</div>"
          }, {
            fixed: 'right',
            title: '操作',
            toolbar: '#barDemo',
            width: 170
          }]
        ],
        page: true
      });

      //头工具栏事件
      table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
          case 'getCheckData':
            var data = checkStatus.data;
            layer.alert(JSON.stringify(data));
            break;
          case 'getCheckLength':
            var data = checkStatus.data;
            layer.msg('选中了：' + data.length + ' 个');
            break;
          case 'isAll':
            layer.msg(checkStatus.isAll ? '全选' : '未全选');
            break;

            //自定义头工具栏右侧图标 - 提示
          case 'LAYTABLE_TIPS':
            layer.alert('这是工具栏右侧自定义的一个图标按钮');
            break;
        };
      });

      //监听行工具事件
      table.on('tool(test)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') 
        {
          layer.confirm('确定删除么?', function (index) {
            obj.del();
            layer.close(index);
            $.ajax({
        		type:'POST',
        		url: "${pageContext.request.contextPath}/deleteUploadfile",
        		data:{
        			id:data.id,
        			},
        		async:false,
        		success:function(msg){
        			var res=JSON.parse(msg);
        			var Code = res.code; //取得返回数据（Sting类型的字符串）的信息进行取值判断
                	var Info =  res.Info;
        			if(Info==null||Info=="")
        			{
        				Info="恭喜您！删除成功！"
        			}
        			confirm(Info);
        		}
        
          		})
          });
        } 
        else if (obj.event === 'download') {
          // 获取XMLHttpRequest
          var xmlResquest = new XMLHttpRequest();
          //  发起请求
          xmlResquest.open("GET", "${pageContext.request.contextPath}/download", true);
          // 设置请求头类型
          xmlResquest.setRequestHeader("Content-type", "application/json");
          xmlResquest.setRequestHeader("id", data.id);
          xmlResquest.responseType = "blob";
          //  返回
          xmlResquest.onload = function (oEvent) {
            //alert(this.status);
            var content = xmlResquest.response;
            // 组装a标签
            var elink = document.createElement("a");
            //设置文件下载路径
            elink.download = data.filename;
            elink.style.display = "none";
            var blob = new Blob([content]);

            //解决下载不存在文件的问题，根据blob大小判断
            if (blob.size == 0) {
              layer.msg('服务器没找到此文件，请联系管理员!');
            } else {
              elink.href = URL.createObjectURL(blob);
              document.body.appendChild(elink);
              elink.click();
              document.body.removeChild(elink);
            }
          };
          xmlResquest.send();
        }
      });
     //监听查询
       form.on('submit(formDemo)', function (data) {
          var data = data.field;

         table.reload('test',{
   		  url:'${pageContext.request.contextPath}/selectUploadfileLike',
   		  where:{
   			  uploadName: data.uploadName,
   			  fileName: data.uploadName
   		  },
   		  page:{
   			  curr: 1
   		  }
   	  });
         return false;
       })
 });
  </script>

</body>

</html>