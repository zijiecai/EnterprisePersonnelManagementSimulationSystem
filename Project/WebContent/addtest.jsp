<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>添加用户</title>
</head>

<body>
  <form action="addUser">
    <label class="layui-form-label">用户编号：</label>
    <div class="layui-input-block">
      <input type="text" name="number" required lay-verify="required" placeholder="请输入用户编号" autocomplete="off"
        class="layui-input">
    </div>
    <label class="layui-form-label">用户名称：</label>
    <div class="layui-input-block">
      <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名称" autocomplete="off"
        class="layui-input">
    </div>
    <label class="layui-form-label">密码：</label>
    <div class="layui-input-inline">
      <input type="password" name="password" required lay-verify="required|pass" placeholder="请输入密码" autocomplete="off"
        class="layui-input">
    </div>
    <label class="layui-form-label">确认密码：</label>
    <div class="layui-input-inline">
      <input type="password" name="password2" required lay-verify="required|confirmPass" placeholder="请再次输入密码"
        autocomplete="off" class="layui-input">
    </div>
    <label class="layui-form-label">手机号码：</label>
    <div class="layui-input-block">
      <input type="text" name="phone" required lay-verify="required|phonenumber" placeholder="请输入手机号码"
        autocomplete="off" class="layui-input">
    </div>
    <label class="layui-form-label">选择角色：</label>
    <div class="layui-input-block">
     <input type="text" name="role_id" required lay-verify="required" autocomplete="off" class="layui-input">
<!--       <select name="role_id" lay-verify="required"> -->
<!--         <option value=""></option> -->
<!--         <option value="1">普通用户</option> -->
<!--         <option value="2">管理员</option> -->
<!--         <option value="3">超级管理员</option> -->
<!--       </select> -->
    </div>
    <label class="layui-form-label">备注：</label>
    <div class="layui-input-block">
      <input type="text" name="remark" placeholder="可选填备注" autocomplete="off" class="layui-input">
    </div>
    <input type="submit" value="提交"></input>
  </form>
</body>

<script>
</script>

</html>