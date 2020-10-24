package com.wyu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MailcapCommandMap;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.User;
import com.wyu.pojo.UserExample;
import com.wyu.service.UserService;
import com.wyu.service.UserServiceImpl;

@Controller
public class UserController {
	
//	@RequestMapping("/login")
//	public String login(Model model){
//		model.addAttribute("loginMS", "我的第一个SSM项目");
//		System.out.println("1111111");
//		return "index";
//	}
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/index")
	 public String index() {
	  return "index";
	 }
	 @RequestMapping("/home")
	 public String home() {
	  return "home";
	 }
	
	@RequestMapping("/login")
	public String LoginConfig(String number,String password,HttpSession session,Model model) {
		
		//调用service层中的login
		if(number == null||number == "") {
			String loginInfo = "";
			session.removeAttribute("loginInfo3");
			return "login";
		}
		User user = service.login(number);
		//判断：三种情况：没有这个用户，密码错误，登录成功
		
		session.removeAttribute("loginInfo3");
		String loginInfo = "";
		if(user == null) {
			loginInfo="用户不存在";
		}
		if(user != null) {
			if(password.equals(user.getPassword())) {
				System.out.println("登录成功！");
				session.setAttribute("user", user);
				loginInfo="";
				session.removeAttribute("loginInfo3");
				//如果用户登录成功，就将该用户的信息保存到session域中，供其他页面访问，index
				return "index";
			}
			else {
				loginInfo = "密码错误";
				System.out.println("密码错误！");
				
			}
		}
		
		session.setAttribute("loginInfo3",loginInfo);
//		service.login(number);
		return "login";
	}
	
	//添加用户
	@RequestMapping("/addUser")
	public String addUser(User user,Model model) {
		System.out.println("------->"+user);
		
		user.setCreatedate(new Date());
		
		int in = service.addUser(user);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("添加成功！");
			addInfo = "1";
		}
		//将添加的结果用model放到request域中
		model.addAttribute("addInfo", addInfo);
		
		return "addUser";
	}
	
	//用户编号是否已存在的验证
	@RequestMapping("/numberAjax")
	@ResponseBody
	public String numberAjax(String number) {
		System.out.println(number);
		User user = service.login(number);
		if(user==null) { 
			return "0"; 
		}

		return "1"; 
		
	}
	
	//手机号是否已存在的验证
	@RequestMapping("/phoneAjax")
	@ResponseBody
	public String phoneAjax(String phone) {
		System.out.println(phone);
		List<User> list = service.fineByPhone(phone);
		//判断list是否为空
		if(list.size()>0) {//说明有数据
			
			return "1";//1不可以注册
		}
		
		return "0";//0可以注册
	}
	
	//user分页操作
	@RequestMapping(value="/findUsers",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUsers(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
		List<User> list = service.findUsers();
		PageInfo<User> pageInfo = new PageInfo<>(list);
		UserExample example = new UserExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		
		list.forEach(li->{
			System.out.println(li);
//			System.out.println("时间-------------->"+li.getCreatedate().toString());
//			li.setCreatedate(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(li.getCreatedate()));
		});
		String json = JSON.toJSONString(list);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		
		//将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
		
	}
	
	@RequestMapping(value="/findUsersLike",produces="applications/json;charset=utf-8")
	@ResponseBody
	public String findUsersLike(User user,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value="limit",defaultValue="4")Integer pageSize,Model model) {
		
		System.out.println("user.number"+user);
		System.out.println("user.roleId----------->"+user.getRoleId());
		PageHelper.startPage(currentPage,pageSize);
		List<User> user1 = service.findUsersLike(user);
		PageInfo<User> pageInfo = new PageInfo<>(user1);
		UserExample example = new UserExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
	
		user1.forEach(li->System.out.println(li));
		String json = JSON.toJSONString(user1);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		//将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		
		String res = JSON.toJSONString(result);
		
		System.out.println("------------->"+res);
		return res;
	}
	
	//查询用户数量
	@RequestMapping("/countByUser")
	@ResponseBody
	public String countByUser() {
		
		UserExample example = new UserExample();
		int count = service.countByExample(example);
		System.out.println(count);
		String count1 = String.valueOf(count);
		return count1;
	}
	
	//修改当前用户信息
		@RequestMapping("/updateThisUser")
		public String updateThisUser(User user,HttpSession session) {
			System.out.println("user--------->"+user);
			//调用service层完成修改操作
			int in = service.updateUser(user);
			System.out.println(in);
			if(in > 0) {
				System.out.println("修改成功！");
				session.setAttribute("user", user);
				return "index";
			}
			return "index";	
		}
	
	//修改用户信息
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User user,HttpSession session) {
		System.out.println("user--------->"+user);
		//调用service层完成修改操作
		int in = service.updateUser(user);
		System.out.println(in);
		Map<String, Object> result = new HashMap<String, Object>();
		if(in > 0) {
			System.out.println("修改成功！");
			session.setAttribute("user", user);
			result.put("data","1");
			String res = JSON.toJSONString(result);
			System.out.println(res);
			return res;
		}
		result.put("data","0");
		String res = JSON.toJSONString(result);
		System.out.println(res);
		return res;
	}
	
	//删除用户信息
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(String number) {
		System.out.println("number------>"+number);
		//调用service层完成删除操作
		int in = service.deleteUser(number);
		if (in > 0) {
			System.out.println("删除成功");
			return "1";
		}
		return "0";
	} 
	
}
