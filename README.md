# EnterprisePersonnelManagementSimulationSystem
### 企业人事管理模拟系统 基于Java + SSM框架 + tomcat + MySQL + H5C3 + jQuery库+ layui框架

- 前端
  - 基于layui框架，建立管理系统的整体布局框架，通过点击利用JavaScript操作DOM动态创建对应的iframe标签，建立各个子页面的选项卡的链接，包括home页面、各个表的添加和查询页面、文件上传与下载页面等子页面。
- 后端
  - 使用tomcat搭建Web服务器，基于Java语言，servlet服务器端应用程序，提供在Web上进行请求和响应的服务，基于Mybatis + Spring + SpringMVC搭建SSM框架项目，创建逆向工程文件生成mapper、pojo文件夹下的各个类文件。
- 数据库
  - 采用关系型数据库MySQL，建立名为j2eedb的数据库，并创建user、employee、job、dept、role、notice、uploadfile数据表，通过外键将主表与从表之间关联起来，用于存储企业人事管理系统的数据信息。

##### 该企业人事管理系统项目，涉及到SSM框架的搭建、逆向工程的创建、数据动态更新显示、表单验证、数据增删改查、模糊查询、文件上传与下载、验证码生成与及校验、ajax数据交互、角色下列选择框的数据回显、mybatis与layui分页结合、springMVC拦截器等知识点。

###### 用户登录界面
![用户登录界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E7%94%A8%E6%88%B7%E7%99%BB%E5%BD%95%E7%95%8C%E9%9D%A2.png)

###### 模拟系统首页子页面home
![模拟系统首页子页面home.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%A8%A1%E6%8B%9F%E7%B3%BB%E7%BB%9F%E9%A6%96%E9%A1%B5%E5%AD%90%E9%A1%B5%E9%9D%A2home.png)

###### 添加用户界面
![添加用户界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%B7%BB%E5%8A%A0%E7%94%A8%E6%88%B7%E7%95%8C%E9%9D%A2.png)

###### 添加员工界面
![添加员工界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%B7%BB%E5%8A%A0%E5%91%98%E5%B7%A5%E7%95%8C%E9%9D%A2.png)

###### 添加公告界面
![添加公告界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%B7%BB%E5%8A%A0%E5%85%AC%E5%91%8A%E7%95%8C%E9%9D%A2.png)

###### 查询用户界面
![查询用户界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%9F%A5%E8%AF%A2%E7%94%A8%E6%88%B7%E7%95%8C%E9%9D%A2.png)

###### 查询员工界面
![查询员工界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%9F%A5%E8%AF%A2%E5%91%98%E5%B7%A5%E7%95%8C%E9%9D%A2.png)

###### 查询职位界面
![查询职位界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%9F%A5%E8%AF%A2%E8%81%8C%E4%BD%8D%E7%95%8C%E9%9D%A2.png)

###### 条件查询功能
![条件查询功能.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%9D%A1%E4%BB%B6%E6%9F%A5%E8%AF%A2%E5%8A%9F%E8%83%BD.png)

###### 文件上传界面
![文件上传界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E7%95%8C%E9%9D%A2.png)

###### 文件下载界面
![文件下载界面.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E6%96%87%E4%BB%B6%E4%B8%8B%E8%BD%BD%E7%95%8C%E9%9D%A2.png)

###### 修改当前用户信息
![修改当前用户信息.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E4%BF%AE%E6%94%B9%E5%BD%93%E5%89%8D%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF.png)

###### 信息删除功能
![信息删除功能.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E4%BF%A1%E6%81%AF%E5%88%A0%E9%99%A4%E5%8A%9F%E8%83%BD.png)

###### 信息修改功能
![信息修改功能.png](https://raw.githubusercontent.com/zijiecai/img-storage/master/%E4%BF%A1%E6%81%AF%E4%BF%AE%E6%94%B9%E5%8A%9F%E8%83%BD.png)
