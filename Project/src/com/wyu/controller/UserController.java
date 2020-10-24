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
//		model.addAttribute("loginMS", "�ҵĵ�һ��SSM��Ŀ");
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
		
		//����service���е�login
		if(number == null||number == "") {
			String loginInfo = "";
			session.removeAttribute("loginInfo3");
			return "login";
		}
		User user = service.login(number);
		//�жϣ����������û������û���������󣬵�¼�ɹ�
		
		session.removeAttribute("loginInfo3");
		String loginInfo = "";
		if(user == null) {
			loginInfo="�û�������";
		}
		if(user != null) {
			if(password.equals(user.getPassword())) {
				System.out.println("��¼�ɹ���");
				session.setAttribute("user", user);
				loginInfo="";
				session.removeAttribute("loginInfo3");
				//����û���¼�ɹ����ͽ����û�����Ϣ���浽session���У�������ҳ����ʣ�index
				return "index";
			}
			else {
				loginInfo = "�������";
				System.out.println("�������");
				
			}
		}
		
		session.setAttribute("loginInfo3",loginInfo);
//		service.login(number);
		return "login";
	}
	
	//����û�
	@RequestMapping("/addUser")
	public String addUser(User user,Model model) {
		System.out.println("------->"+user);
		
		user.setCreatedate(new Date());
		
		int in = service.addUser(user);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("��ӳɹ���");
			addInfo = "1";
		}
		//����ӵĽ����model�ŵ�request����
		model.addAttribute("addInfo", addInfo);
		
		return "addUser";
	}
	
	//�û�����Ƿ��Ѵ��ڵ���֤
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
	
	//�ֻ����Ƿ��Ѵ��ڵ���֤
	@RequestMapping("/phoneAjax")
	@ResponseBody
	public String phoneAjax(String phone) {
		System.out.println(phone);
		List<User> list = service.fineByPhone(phone);
		//�ж�list�Ƿ�Ϊ��
		if(list.size()>0) {//˵��������
			
			return "1";//1������ע��
		}
		
		return "0";//0����ע��
	}
	
	//user��ҳ����
	@RequestMapping(value="/findUsers",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUsers(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
		List<User> list = service.findUsers();
		PageInfo<User> pageInfo = new PageInfo<>(list);
		UserExample example = new UserExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		
		list.forEach(li->{
			System.out.println(li);
//			System.out.println("ʱ��-------------->"+li.getCreatedate().toString());
//			li.setCreatedate(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(li.getCreatedate()));
		});
		String json = JSON.toJSONString(list);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
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
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		
		String res = JSON.toJSONString(result);
		
		System.out.println("------------->"+res);
		return res;
	}
	
	//��ѯ�û�����
	@RequestMapping("/countByUser")
	@ResponseBody
	public String countByUser() {
		
		UserExample example = new UserExample();
		int count = service.countByExample(example);
		System.out.println(count);
		String count1 = String.valueOf(count);
		return count1;
	}
	
	//�޸ĵ�ǰ�û���Ϣ
		@RequestMapping("/updateThisUser")
		public String updateThisUser(User user,HttpSession session) {
			System.out.println("user--------->"+user);
			//����service������޸Ĳ���
			int in = service.updateUser(user);
			System.out.println(in);
			if(in > 0) {
				System.out.println("�޸ĳɹ���");
				session.setAttribute("user", user);
				return "index";
			}
			return "index";	
		}
	
	//�޸��û���Ϣ
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User user,HttpSession session) {
		System.out.println("user--------->"+user);
		//����service������޸Ĳ���
		int in = service.updateUser(user);
		System.out.println(in);
		Map<String, Object> result = new HashMap<String, Object>();
		if(in > 0) {
			System.out.println("�޸ĳɹ���");
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
	
	//ɾ���û���Ϣ
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(String number) {
		System.out.println("number------>"+number);
		//����service�����ɾ������
		int in = service.deleteUser(number);
		if (in > 0) {
			System.out.println("ɾ���ɹ�");
			return "1";
		}
		return "0";
	} 
	
}
