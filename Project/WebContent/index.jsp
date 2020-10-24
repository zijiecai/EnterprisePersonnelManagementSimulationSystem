<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css">
  <title>企业人事管理模拟系统</title>
</head>

<body class="layui-layout-body">
  <div id="sp1" style="display:none;">${user.number}</div>
  <div id="sp2" style="display:none;">${user.username}</div>
  <div id="sp3" style="display:none;">${user.password}</div>
  <div id="sp4" style="display:none;">${user.phone}</div>
  <div id="sp5" style="display:none;">${user.roleId}</div>
  <div id="sp6" style="display:none;">${user.remark}</div>
  <div class="layui-layout layui-layout-admin">
    <!-- 头部内容 -->
    <div class="layui-header">
      <div class="layui-logo title">企业人事管理模拟系统</div>
      <!-- 头部区域（可配合layui已有的水平导航） -->
      <!-- <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a href="">控制台</a></li>
        <li class="layui-nav-item"><a href="">商品管理</a></li>
        <li class="layui-nav-item"><a href="">用户</a></li>
        <li class="layui-nav-item">
          <a href="javascript:;">其它系统</a>
          <dl class="layui-nav-child">
            <dd><a href="">邮件管理</a></dd>
            <dd><a href="">消息管理</a></dd>
            <dd><a href="">授权管理</a></dd>
          </dl>
        </li>
      </ul> -->
      <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
          <a href="javascript:;">
            <img
              src="https://7975-yuncloud-123-1301997245.tcb.qcloud.la/Icon/user_image.jpg?sign=9f9d56521d6e7ba57afcf1cfcea13a30&t=1588948688"
              class="layui-nav-img">${user.username}
          </a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:void(0);" onclick="edit()">基本资料</a></dd>
            <dd><a href="login">退出登录</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="login">退出</a></li>
      </ul>
    </div>

    <!-- 侧边导航栏 -->
    <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test" lay-shrink="all">
          <li class="layui-nav-item left_navbar" data-id="1" data-url="home" mytitle="首页"><a href="#">首页</a></li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe770;</i> 用户管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="2" data-url="addUser" mytitle="添加用户"><a href="#"><i
                    class="layui-icon">&#xe654;</i>
                  添加用户</a></dd>
              <dd class="left_navbar" data-id="3" data-url="queryUser" mytitle="查询用户"><a href="#"><i
                    class="layui-icon">&#xe615;</i>
                  查询用户</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe66f;</i> 员工管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="4" data-url="addStaff" mytitle="添加员工"><a href="#"><i
                    class="layui-icon">&#xe654;</i> 添加员工</a></dd>
              <dd class="left_navbar" data-id="5" data-url="queryStaff" mytitle="查询员工"><a href="#"><i
                    class="layui-icon">&#xe615;</i> 查询员工</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe656;</i> 职位管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="6" data-url="addPost" mytitle="添加职位"><a href="#"><i
                    class="layui-icon">&#xe654;</i> 添加职位</a></dd>
              <dd class="left_navbar" data-id="7" data-url="queryPost" mytitle="查询职位"><a href="#"><i
                    class="layui-icon">&#xe615;</i> 查询职位</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe613;</i> 部门管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="8" data-url="addDepartment" mytitle="添加部门"><a href="#"><i
                    class="layui-icon">&#xe654;</i> 添加部门</a></dd>
              <dd class="left_navbar" data-id="9" data-url="queryDepartment" mytitle="查询部门"><a href="#"><i
                    class="layui-icon">&#xe615;</i> 查询部门</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe612;</i> 角色管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="10" data-url="addRole" mytitle="添加角色"><a href="#"><i
                    class="layui-icon">&#xe654;</i> 添加角色</a></dd>
              <dd class="left_navbar" data-id="11" data-url="queryRole" mytitle="查询角色"><a href="#"><i
                    class="layui-icon">&#xe615;</i> 查询角色</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe63a;</i> 公告管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="12" data-url="addNotice" mytitle="添加公告"><a href="#"><i
                    class="layui-icon">&#xe654;</i> 添加公告</a></dd>
              <dd class="left_navbar" data-id="13" data-url="queryNotice" mytitle="查询公告"><a href="#"><i
                    class="layui-icon">&#xe615;</i> 查询公告</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;"><i class="layui-icon">&#xe66d;</i> 下载管理</a>
            <dl class="layui-nav-child">
              <dd class="left_navbar" data-id="14" data-url="fileDownload" mytitle="文件下载"><a href="#"><i
                    class="layui-icon">&#xe601;</i> 文件下载</a></dd>
              <dd class="left_navbar" data-id="15" data-url="fileUpload" mytitle="文件上传"><a href="#"><i
                    class="layui-icon">&#xe681;</i> 文件上传</a></dd>
            </dl>
          </li>
        </ul>
      </div>
    </div>

    <!-- 主体内容 -->
    <div class="layui-body">
      <!-- 内容主体区域 -->
      <div style="padding: 10px;">
        <div class="layui-tab layui-tab-card" lay-filter="demo" lay-allowclose="true">
          <ul class="layui-tab-title">
            <!-- <li class="layui-this" data-id="1" data-url="home.html" mytitle="首页">首页</li> -->
          </ul>
          <div class="layui-tab-content" style="height: 700px;">
          </div>
        </div>
      </div>
    </div>

    <div class="layui-footer">
      <!-- 底部固定区域 -->
      ©企业人事管理模拟系统
      &nbsp;&nbsp;&nbsp;开发团队：计科1班1组
    </div>
  </div>
  <script src="./layui/layui.js"></script>
  <script>
    //JavaScript代码区域
    
    //点击编辑资料的用户信息编辑功能
      function edit(){
    	  var $ = layui.jquery;
    	  
    	//将旧数据显示到编辑表中
      	document.getElementById("number").value = $("#sp1").text();
      	document.getElementById("username").value = $("#sp2").text();
      	document.getElementById("password").value = $("#sp3").text();
      	document.getElementById("phone").value = $("#sp4").text();
      	document.getElementById("sel").value = $("#sp5").text();
      	document.getElementById("remark").value = $("#sp6").text() == undefined?'':$("#sp6").text();
    	  
    	  layer.open({
              //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
              type: 1,
              title: "修改您的信息",
              area: ['420px', '500px'],
              content: $("#popUpdateTest") //引用的弹出层的页面层的方式加载修改界面表单
            });
    	  
    	  setForm();
    	//动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
//           setFormValue(obj, data);
    	  
    	  return false;
      }
    
      function setForm(){
    	  var $ = layui.jquery;
          var form = layui.form;
    	  form.on('submit(demo11)',function(data){
      		var data1 = data.field;
      		$.ajax({
                  url: 'updateUser',
                  type: 'POST',
                  data: {
                    // olddid: data.did,
                    // olddname: data.dname,
                    // olddremark: data.dremark,
                    number: data1.number,
                    username: data1.username,
                    password: data1.password,
                    phone: data1.phone,
                    roleId: data1.sel,
                    remark: data1.remark,
                  },
                  success: function(){
                	  layer.open({
          				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
          				  title: '恭喜你', //页面标题
          				  shadeClose: true, //点击遮罩区域是否关闭页面
          				  shade: 0.8,  //遮罩透明度
          				  content: '修改成功'
          				});  
                  },
                  error: function(){
                	  layer.open({
          				  closeBtn: 1, //关闭按钮是否显示 1显示0不显示
          				  title: '错误提示', //页面标题
          				  shadeClose: true, //点击遮罩区域是否关闭页面
          				  shade: 0.8,  //遮罩透明度
          				  content: '修改失败'
          				});  
                  }
      		})
      		
      	})
      }
    
    
    layui.use(['element','table','form','jquery'], function () {
      var table = layui.table;
      var $ = layui.jquery;
      var form = layui.form;
      var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块      
      
      //自定义验证规则
      form.verify({
	      // 校验密码的长度，确保长度在6-12位之间
	      pass: [
	        /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
	      ],
	      //校验手机号码的格式
	      phonenumber: [
	        /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
	        '手机号码格式不正确'
	      ]
	    });  
      
      $(function(){
    	  
    	//角色的数据回显
          //页面打开时异步加载数据,查询信道
          $.ajax({
              type: "get",
              url: "findRoles",
              datatype: "json",
              success: function (data) {
           	   
                  $.each(data, function (index, item) {
//                       alert(index);
//                       alert(item.rname);
                      $('#sel').append(new Option(item.rname,item.rid));
                  });
                  layui.form.render("select");
              },error: function () {
                  alert("查询信道失败");
              }
          });
      })
      
      //监听弹出框表单提交，massage是修改界面的表单数据'submit(demo11),是修改按钮的绑定
      function setFormValue(obj, data) {
        form.on('submit(demo11)', function (massage) {
          $.ajax({
            url: 'updateThisUser',
            type: 'POST',
            data: {
              // olddid: data.did,
              // olddname: data.dname,
              // olddremark: data.dremark,
              number: massage.field.number,
              username: massage.field.username,
              password: massage.field.password,
              phone: massage.field.phone,
              roleId: massage.field.sel,
              remark: massage.field.remark,
            },
            /* success: function (msg) {
              var returnCode = msg.returnCode; //取得返回数据（Sting类型的字符串）的信息进行取值判断
              if (returnCode == 200) {
                layer.closeAll('loading');
                layer.load(2);
                layer.msg("修改成功", {
                  icon: 6
                });
                setTimeout(function () {
                  obj.update({
                    jid: massage.field.newjid,
                    jname: massage.field.newjname,
                    jremark: massage.field.newjremark
                  }); //修改成功修改表格数据不进行跳转
                  layer.closeAll(); //关闭所有的弹出层
                }, 1000);
                // 加载层 - 风格
              } else {
                layer.msg("修改失败", {
                  icon: 5
                });
              }
            } */
          })
          return false;
        })
      }
      
      //触发事件
      var active = {
        //给active绑定几项事件
        tabAdd: function (url, id, name) {
          //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
          //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
          element.tabAdd('demo', {
            title: name,
            content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url +
              '.jsp" style="width:100%;height:99%;"></iframe>',
            id: id //规定好的id
          })
          FrameWH(); //计算ifram层的大小
        },
        tabChange: function (id) {
          //切换到指定Tab项
          element.tabChange('demo', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function (id) {
          element.tabDelete("demo", id); //删除
        }
      };

      // 默认展示首页
      active.tabAdd('home', '1', '首页');
      active.tabChange('1');

      //当点击有left_navbar属性的标签时，即左侧菜单栏中内容 ，触发点击事件
      $('.left_navbar').on('click', function () {
        var dataid = $(this);

        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
          //如果比零小，则直接打开新的tab项
          active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("mytitle"));
        } else {
          //否则判断该tab项是否以及存在

          var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
          $.each($(".layui-tab-title li[lay-id]"), function () {
            //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
            if ($(this).attr("lay-id") == dataid.attr("data-id")) {
              isData = true;
            }
          })
          if (isData == false) {
            //标志为false 新增一个tab项
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("mytitle"));
          }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
      });

      function FrameWH() {
        var h = $(window).height();
        $("iframe").css("height", h + "px");
      }

      $(window).resize(function () {
        FrameWH();
      })

    });
  </script>
  
 <!-- //这里是弹出层的表单信息
//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
  <div class="layui-row" id="popUpdateTest" style="display:none;">
    <div class="layui-col-md10">
      <form class="layui-form layui-from-pane" action="updateThisUser" style="margin-top:20px">
        <div class="layui-form-item">
          <label class="layui-form-label">用户ID</label>
          <div class="layui-input-block">
            <input type="text" name="number" id="number" autocomplete="off"
              class="layui-input layui-disabled">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">用户名</label>
          <div class="layui-input-block">
            <input type="text" name="username" id="username" required lay-verify="required" autocomplete="off" placeholder="请输入用户名"
              class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">密码</label>
          <div class="layui-input-block">
            <input type="text" name="password" id="password" required lay-verify="required|pass" autocomplete="off" placeholder="请输入密码"
              class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">角色ID</label>
      <div class="layui-input-block">
        <select name="roleId" id="sel" lay-verify="required">
          <option value=""></option>
        </select>
      </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">电话号码</label>
          <div class="layui-input-block">
            <input type="text" name="phone" id="phone" required lay-verify="required|phone" autocomplete="off" placeholder="请输入电话号码"
              class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <input type="text" name="remark" id="remark" autocomplete="off" placeholder="请输入备注" class="layui-input">
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