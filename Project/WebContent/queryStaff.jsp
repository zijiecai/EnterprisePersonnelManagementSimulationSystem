<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>查询员工</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css" media="all">
   <script type="text/javascript" href="./layui/layui.js"></script>
  <script type="text/javascript" href="./layui/lay/modules/jquery.js"></script>
  <script type="text/javascript" src="logincss/js/jquery.min.js"></script>
  <script type="text/javascript" src="logincss/js/jquery.easyui.min.js"></script>
</head>

<body>
	<form class="layui-form" action="">
	  <div class="layui-inline">
	    <label for="number" class="layui-form-label">员工ID：</label>
	    <div class="layui-input-inline">
	      <input type="text" name="id" id="id" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-inline">
	    <label for="username" class="layui-form-label">姓名：</label>
	    <div class="layui-input-inline"> 
	      <input type="text" name="name" id="name" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-inline">
	    <label for="username" class="layui-form-label">手机号码：</label>
	    <div class="layui-input-inline">
	      <input type="text" name="phone" id="phone" class="layui-input">
	    </div>
	  </div>
  	<button class="layui-btn" lay-submit lay-filter="provinceSearch">查询</button> 
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
    layui.use(['table','form'], function () {
      var table = layui.table;
      var form = layui.form;
      
      table.render({
        elem: '#test',
        url: '${pageContext.request.contextPath}/findEmployees',
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
            title: '员工ID',
            width: 100,
            fixed: 'left',
            unresize: true,
            sort: true,
            align: 'center'
          }, {
            field: 'deptId',
            title: '部门编号',
            width: 90,
            align: 'center'
          }, {
            field: 'jobId',
            title: '职位编号',
            width: 90,
            align: 'center'
          }, {
              field: 'name',
              title: '姓名',
              width: 80,
              align: 'center'
          }, {
            field: 'cardId',
            title: '身份证号',
            width: 90,
            align: 'center'
          }, {
            field: 'address',
            title: '现住地址',
            width: 90,
            align: 'center'
          }, {
            field: 'postCode',
            title: '邮政编码',
            width: 90,
            align: 'center'
          }, {
            field: 'tel',
            title: '手机号码',
            width: 90,
            align: 'center'
          }, {
            field: 'phone',
            title: '电话号码',
            width: 90,
            align: 'center'
          }, {
            field: 'qqNum',
            title: 'QQ号码',
            width: 90,
            align: 'center'
          }, {
            field: 'email',
            title: '邮箱地址',
            width: 90,
            align: 'center'
          }, {
            field: 'sex',
            title: '性别',
            width: 50,
            sort: true,
            align: 'center'
          }, {
            field: 'party',
            title: '政治面貌',
            width: 90,
            align: 'center'
          }, {
            field: 'birthday',
            title: '出生日期',
            width: 90,
            align: 'center',
            templet: "<div>{{layui.util.toDateString(d.birthday, 'yyyy-MM-dd HH:mm:ss')}}</div>"
          }, {
            field: 'race',
            title: '民族',
            width: 50,
            align: 'center'
          }, {
            field: 'education',
            title: '学历',
            width: 80,
            align: 'center'
          }, {
            field: 'speciality',
            title: '特长',
            width: 80,
            align: 'center'
          }, {
            field: 'hobby',
            title: '爱好',
            width: 80,
            align: 'center'
          }, {
            field: 'remark',
            title: '备注',
            width: 80,
            align: 'center'
          }, {
            field: 'createdate',
            title: '创建时间',
            width: 90,
            align: 'center',
            templet: "<div>{{layui.util.toDateString(d.createdate, 'yyyy-MM-dd HH:mm:ss')}}</div>"
          }, {
            fixed: 'right',
            title: '操作',
            toolbar: '#barDemo',
            width: 150,
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
            var json = {"id":data.id};
            //用post方法完成ajax提交数据：url,json,data返回值
            $.post("deleteEmployee",json,function(data){
            	if(data == "1"){
            		layer.open({
            			 closeBtn: 1, //关闭按钮是否显示 1显示0不显示
         				  title: '恭喜你', //页面标题
         				  shadeClose: true, //点击遮罩区域是否关闭页面
         				  shade: 0.8,  //遮罩透明度
         				  content: '删除成功'	
            			});
            		}

            else{
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
        	document.getElementById("newid").value = data.id;
        	document.getElementById("newname").value = data.name;
        	document.getElementById("newqqNum").value = data.qqNum;
        	document.getElementById("newphone").value = data.phone;
        	document.getElementById("newemail").value = data.email;
        	document.getElementById("neweducation").value = data.education;
        	document.getElementById("newremark").value = data.remark == undefined?'':data.remark;
        	
        	layer.open({
                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                type: 1,
                title: "修改员工信息",
                area: ['420px', '520px'],
                content: $("#popUpdateTest") //引用的弹出层的页面层的方式加载修改界面表单
              });
              //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
              setFormValue(obj, data);
//           layer.prompt({
//             formType: 2,
//             value: data.email
//           }, function (value, index) {
//             obj.update({
//               email: value
//             });
//             layer.close(index);
//           });
        }
      });
      
      
    //监听弹出框表单提交，massage是修改界面的表单数据'submit(demo11),是修改按钮的绑定
      function setFormValue(obj, data) {
    	
        form.on('submit(demo11)', function (massage) {
          $.ajax({
            url: 'updateEmployee',
            type: 'POST',
            data: {
//               olddid: data.did,
//               olddname: data.dname,
//               olddremark: data.dremark,
    			  //编号不让改，传递旧的编号
              id: data.id,
              name: massage.field.newname,
              qqNum: massage.field.newqqNum,
              phone: massage.field.newphone,
              email: massage.field.newemail,
              education: massage.field.neweducation,
              remark: massage.field.newremark,
            },
            async: false,
            success: function (msg) {
            	msg =  $.parseJSON(msg);
//             	alert(typeof(msg.data));
            	if(msg.data == "1"){
            		alert("修改成功！");
            		/* layer.open({
           			      closeBtn: 1, //关闭按钮是否显示 1显示0不显示
        				  title: '恭喜你', //页面标题
        				  shadeClose: true, //点击遮罩区域是否关闭页面
        				  shade: 0.8,  //遮罩透明度
        				  content: '修改成功'	
           			}); */
           		}else{
           			alert("修改失败！");
                	/* layer.open({
                  		closeBtn: 1, //关闭按钮是否显示 1显示0不显示
               			title: '错误信息', //页面标题
               			shadeClose: true, //点击遮罩区域是否关闭页面
               			shade: 0.8,  //遮罩透明度
               			content: '修改失败'	
                  	}); */
            	}
            }
          })
        })
      }
      
      form.on('submit(provinceSearch)', function(data) {
	    	var data = data.field;
	    	
	    	
	    	table.reload('test', {
	    	  url:'${pageContext.request.contextPath}/findEmployeesLike',
	    	  where: { //设定异步数据接口的额外参数，任意设
	    		id:data.id,
	    	    	name: data.name,
	    	    phone:data.phone,
	    	    roleId:data.roleId
	    	  }
	    	  ,page: {
	    	    curr: 1 //重新从第 1 页开始
	    	  }
	    	});
	    	return false;
	    });
    });
  </script>
<!-- //这里是弹出层的表单信息
//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
  <div class="layui-row" id="popUpdateTest" style="display:none;">
    <div class="layui-col-md10">
      <form class="layui-form layui-from-pane" action="" style="margin-top:20px">

        <div class="layui-form-item">
          <label class="layui-form-label">员工ID：</label>
          <div class="layui-input-block">
            <input type="text" name="newid" id="newid" placeholder="请输入用户编号" autocomplete="off"
              class="layui-input layui-disabled">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">姓名：</label>
          <div class="layui-input-block">
            <input type="text" name="newname" id="newname"   required lay-verify="required" placeholder="请输入用户名称"
              autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">QQ号码：</label>
          <div class="layui-input-inline">
            <input type="text" name="newqqNum" id="newqqNum"  required lay-verify="required" placeholder="请输入QQ号码"
              autocomplete="off" class="layui-input">
          </div>
          <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">手机号码：</label>
          <div class="layui-input-block">
            <input type="text" name="newphone" id="newphone" required lay-verify="required|phone" placeholder="请输入手机号码"
              autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">邮箱地址：</label>
          <div class="layui-input-block">
            <input type="text" name="newemail" id="newemail" required lay-verify="required|email" placeholder="请输入邮箱地址"
              autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">学历：</label>
          <div class="layui-input-block">
            <input type="text" name="neweducation" id="neweducation" required lay-verify="required" placeholder="请输入学历"
              autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">备注：</label>
          <div class="layui-input-block">
            <input type="text" name="newremark" id="newremark" placeholder="可选填备注" autocomplete="off" class="layui-input">
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