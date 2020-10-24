package com.wyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.Role;
import com.wyu.pojo.RoleExample;
import com.wyu.pojo.RoleExample.Criteria;
import com.wyu.service.RoleService;
@Controller
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@RequestMapping(value = "/addRole",produces = "text/plain;charset=utf-8")
	public String addRole(Role role,Model model,HttpSession session) {

		System.out.println(role);
		String addInfo = "恭喜你!添加成功!";
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		String rname = role.getRname();
		boolean flag_=service.selectRoleByName(role);
		if(flag_==true) {
			addInfo = "添加失败,角色名字为"+role.getRname()+"的角色已存在!";
		}
		int flag = service.addRole(role);
		if(flag!=0)
		{
			System.out.println("添加成功");
		}
		else {
			addInfo = "添加失败,角色编号为"+role.getRid()+"的角色已存在!";
			System.out.println("添加失败");
		}
		model.addAttribute("addInfo", addInfo);
		return "addRole";
	}
	@RequestMapping(value = "/selectRoleLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectRoleLike(Role role,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("role.rid----->"+role);
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
		List<Role> list = service.selectRoleLike(role);
		PageInfo<Role> pageInfo = new PageInfo<>(list);
		RoleExample example = new RoleExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		
		list.forEach(li->System.out.println(li));
		String json = JSON.toJSONString(list);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		System.out.println(count);
		//将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
	}

	//Role分页操作
		@RequestMapping(value="/findAllRoles",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findAllRoles(@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
			PageHelper.startPage(currentPage,pageSize);
			
			//先查询所有的数据
			List<Role> list = service.findRoles();
			PageInfo<Role> pageInfo = new PageInfo<>(list);
			RoleExample example = new RoleExample();
			int count = service.countByExample(example);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			list.forEach(li->System.out.println(li));
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
		@RequestMapping(value="updateRole",produces ="text/plain;charset=utf-8")
		@ResponseBody
		public String updateRole(Role role,Model model) {
			Map<String,String> map=new HashMap<>();
			System.out.println("role-------->"+role);
			int flag = service.updateRole(role);
			String updateInfo="恭喜您,修改成功";
			if(flag!=0)
			{
				System.out.println("修改成功");
				map.put("code", "200");
				map.put("msg", "成功");
			}
			else {
				updateInfo = "修改失败,角色编号为"+role.getRid()+"的角色不存在!";
				System.out.println("修改失败");
				map.put("code", "0");
				map.put("msg", "失败");
			}
			//model.addAttribute("updateInfo", updateInfo);
			
			map.put("Info", updateInfo);
			//将其转换为JSON数据，并压入值栈返回
		
			String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
			
			return res;
		}
		
		//删除角色
		@RequestMapping(value="/deleteRole",produces ="text/plain;charset=utf-8")
		@ResponseBody

		public String deleteRole(Role role) {
			Map<String,String> map=new HashMap<>();
			Integer rid = role.getRid(); 
			System.out.println("rid-----------"+rid);
			//调用service完成删除
			int flag = service.deleteRole(rid);
			if(flag != 0) {
				System.out.println("删除成功！");
				map.put("code", "200");
				map.put("msg", "成功");
				map.put("Info","恭喜您删除成功!");
				String res = JSON.toJSONString(map);
				System.out.println("---res:"+res);
				return res;
			}
			map.put("code", "0");
			map.put("msg", "失败");
			map.put("Info","很遗憾删除失败,该角色存在键值依赖,如需删除请先删除使用该角色的用户!");

			//将其转换为JSON数据，并压入值栈返回
		
			String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
			
			return res;
		}
		@RequestMapping(value="/findRoles",produces = "application/json;charset=utf-8")
		@ResponseBody
		public String findRoles() {
			
			//调用service层查找数据
			List<Role> list = service.findRoles();
			//将得到的结果，解析成json数据
			String json = JSON.toJSONString(list);
			System.out.println(json);
			return json;
		}

		@RequestMapping("/countByRole")
		 @ResponseBody
		 public String countByRole() {
		  RoleExample example = new RoleExample();
		  int count = service.countByExample(example);
		  String count1 = String.valueOf(count);
		  return count1;
		 }
		
} 
