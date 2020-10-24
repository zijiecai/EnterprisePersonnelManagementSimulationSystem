<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    .title {
      color: #2C3A47;
      border-left: 10px solid #6c5ce7;
      padding-left: 10px;
    }

    .title span {
      font-size: 24px;
      color: #6c5ce7;
    }

    .display {
      position: relative;
      padding: 20px;
      border: 4px solid #1e90ff;
      border-radius: 12px;
      margin-top: 35px;
    }

    .flex1 {
      display: flex;
    }

    .display .allData {
      position: absolute;
      top: -14px;
      left: 18px;
      font-size: 20px;
      background-color: #fff;
      padding: 0 6px 0;
    }

    .display .item {
      font-size: 18px;
      position: relative;
      flex: 2;
      display: flex;
      flex-direction: column;
      text-align: center;
    }

    .item span {
      color: #84817a;
      padding: 8px;
      flex: 1;
    }

    .item .num {
      font-size: 32px;
      color: #7158e2;
    }

    .display .content {
      display: flex;
    }

    .content .name {
      flex: 1;
      padding: 10px;
      font-size: 18px;
      font-weight: 700;
    }

    .content .answer {
      padding: 10px;
      font-size: 18px;
      flex: 10;
    }
  </style>
</head>

<body>
  <h1 class="title">欢迎您，<span>${user.username} !</span></h1>
  <div class="display flex1">
    <span class="allData">数据统计</span>
    <div class="item">
      <span>用户数</span>
      <span class="num" id="userNum"></span>
    </div>
    <div class="item">
      <span>员工数</span>
      <span class="num" id="staffNum"></span>
    </div>
    <div class="item">
      <span>部门数</span>
      <span class="num" id="deptNum"></span>
    </div>
    <div class="item">
      <span>职位数</span>
      <span class="num" id="postNum"></span>
    </div>
    <div class="item">
      <span>角色数</span>
      <span class="num" id="roleNum"></span>
    </div>
    <div class="item">
      <span>公告数</span>
      <span class="num" id="noticeNum"></span>
    </div>
    <div class="item">
      <span>文件数</span>
      <span class="num" id="fileNum"></span>
    </div>
  </div>
  <div class="display">
    <span class="allData">系统信息</span>
    <div class="content">
      <div class="name">开发语言</div>
      <div class="answer">Java HTML CSS JavaScript</div>
    </div>
    <div class="content">
      <div class="name">框架</div>
      <div class="answer">SSM Jquery LayUI</div>
    </div>
    <div class="content">
      <div class="name">操作系统</div>
      <div class="answer">win10</div>
    </div>
    <div class="content">
      <div class="name">数据库</div>
      <div class="answer">MySQL5.7</div>
    </div>
    <div class="content">
      <div class="name">服务器</div>
      <div class="answer">Tomcat8.5</div>
    </div>
    <div class="content">
      <div class="name">Java环境</div>
      <div class="answer">JDK 1.8</div>
    </div>
  </div>
  <div class="display">
    <span class="allData">开发团队</span>
    <div class="content">
      <div class="name">成员</div>
      <div class="answer">蔡梓杰、朱国浩、郑磊</div>
    </div>
  </div>

</body>
<script src="./layui/layui.js"></script>
<script>
	layui.use(['jquery'], function () {
		var $ = layui.jquery;
		
		//查询user表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByUser",
			datatype: "text",
			success: function (data){
				$("#userNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
		
		//查询employee表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByEmployee",
			datatype: "text",
			success: function (data){
				$("#staffNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
		
		//查询job表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByJob",
			datatype: "text",
			success: function (data){
				$("#postNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
	
		
		//查询Dept表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByDept",
			datatype: "text",
			success: function (data){
				$("#deptNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
		
		//查询Role表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByRole",
			datatype: "text",
			success: function (data){
				$("#roleNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
		
		//查询notice表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByNotice",
			datatype: "text",
			success: function (data){
				$("#noticeNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
		
		//查询uploadfile表数据量的ajax请求
		$.ajax({
			type: "get",
			url: "countByUploadfile",
			datatype: "text",
			success: function (data){
				$("#fileNum").text(data);
			},
			error: function(){
				alert("查询失败");
			}
		})
	
	})

</script>

</html>