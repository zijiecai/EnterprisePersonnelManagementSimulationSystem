<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>查询职位</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css" media="all">
</head>

<body>
  <form class="layui-form" action="" method="post">
  <div class="layui-inline">
    <label for="jid" class="layui-form-label">职位编号：</label>
    <div class="layui-input-inline">
      <input type="text" name="jid" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label for="jname" class="layui-form-label">职位名称：</label>
    <div class="layui-input-inline">
      <input type="text" name="jname" class="layui-input">
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
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
        url: '${pageContext.request.contextPath}/findJobs',
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
            field: 'jid',
            title: '职位编号',
            width: 200,
            fixed: 'left',
            unresize: true,
            sort: true,
            align: 'center'
          }, {
            field: 'jname',
            title: '职位名称',
            width: 300,
            align: 'center'
          }, {
            field: 'jremark',
            title: '备注',
            width: 285,
            align: 'center'
          }, {
            fixed: 'right',
            title: '操作',
            toolbar: '#barDemo',
            width: 300,
            align: 'center'
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
        if (obj.event === 'del') {
          layer.confirm('真的删除行么', function (index) {
            obj.del();
            layer.close(index);
            var json={"jid":data.jid};
            //用post方法完成ajax提交数据:url,json,data返回值
            $.post("deleteJob",json,function(data){
            	if(data == "1"){
            		layer.open({
      				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
      				  title: '恭喜你', //页面标题
      				  shadeClose: true, //点击遮罩区域是否关闭页面
      				  shade: 0.8,  //遮罩透明度
      				  content: '删除成功'
      				});  
            	}else{
            		layer.open({
        				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
        				  title: '错误提示', //页面标题
        				  shadeClose: true, //点击遮罩区域是否关闭页面
        				  shade: 0.8,  //遮罩透明度
        				  content: '删除失败'
        			});  
            	}
            })
          });
        } else if (obj.event === 'edit') {
        	
        	//将旧数据显示到编辑表中
        	document.getElementById("newjid").value = data.jid;
        	document.getElementById("newjname").value = data.jname;
        	document.getElementById("newjremark").value = data.jremark == undefined?'':data.jremark;
        	
        	layer.open({
                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                type: 1,
                title: "修改职位信息",
                area: ['420px', '330px'],
                content: $("#popUpdateTest") //引用的弹出层的页面层的方式加载修改界面表单
              });
              //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
              setFormValue(obj, data);

              //数据表格默认的信息编辑 可删
              // layer.prompt({
              //   formType: 2,
              //   value: data.dname
              // }, function (value, index) {
              //   obj.update({
              //     dname: value
              //   });
              //   layer.close(index);
              // });
        }
      });
    //监听弹出框表单提交，massage是修改界面的表单数据'submit(demo11),是修改按钮的绑定
      function setFormValue(obj, data) {
        form.on('submit(demo11)', function (massage) {
          $.ajax({
            url: 'updateJob',
            type: 'POST',
            data: {
              // olddid: data.did,
              // olddname: data.dname,
              // olddremark: data.dremark,
              jid: data.jid,
              jname: massage.field.newjname,
              jremark: massage.field.newjremark
            },
            async: false,
            success: function (msg) {
            	msg =  $.parseJSON(msg);
//             	alert(typeof(msg.data));
            	if(msg.data == "1"){
            		alert("修改成功！");
           		}else{
           			alert("修改失败！");
            	}
            }
          })
        })
      }
      // 监听提交
      // form.on('submit(formDemo)', function (data) {
      //   layer.msg(JSON.stringify(data.field));
      //   return false;
      // });
      
      //监听模糊查询的提交
      form.on('submit(formDemo)',function (data){
    	  var data = data.field;
    	  
    	  table.reload('test',{
    		  url:'${pageContext.request.contextPath}/findJobsLike',
    		  where:{
    			  jid: data.jid,
    			  jname: data.jname
    		  },
    		  page:{
    			  curr: 1
    		  }
    	  });
    	  return false;
      })
      
    });
  </script>

 <!-- //这里是弹出层的表单信息
//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
  <div class="layui-row" id="popUpdateTest" style="display:none;">
    <div class="layui-col-md10">
      <form class="layui-form layui-from-pane" action="" style="margin-top:20px">
        <div class="layui-form-item">
          <label class="layui-form-label">职位编号</label>
          <div class="layui-input-block">
            <input type="text" name="newjid" id="newjid" disabled="true" readOnly="true" autocomplete="off"
              class="layui-input layui-disabled">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">职位名称</label>
          <div class="layui-input-block">
            <input type="text" name="newjname" id="newjname" required lay-verify="required" autocomplete="off" placeholder="请输入职位名称"
              class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <input type="text" name="newjremark" id="newjremark" autocomplete="off" placeholder="请输入备注" class="layui-input">
          </div>
        </div>

        <div class="layui-form-item" style="margin-top:40px">
          <div class="layui-input-block">
            <button class="layui-btn  layui-btn-submit " lay-submit="" lay-filter="demo11">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
          </div>
        </div>
      </form>
    </div>
  </div>


</body>

</html>