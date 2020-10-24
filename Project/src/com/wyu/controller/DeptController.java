package com.wyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.Dept;
import com.wyu.pojo.DeptExample;
import com.wyu.service.DeptService;

@Controller
public class DeptController {

	@Autowired
	private DeptService service;
	
	//添加部门
	@RequestMapping("/addDept")
	public String addDept(Dept dept,Model model) {
		System.out.println("-------->"+dept);
		
		int in = service.addDept(dept);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("添加成功！");
			addInfo = "1";
		}
		//将添加的结果用model放到request域中
		model.addAttribute("addInfo", addInfo);
		
		return "addDepartment";
		
	}
	
	//部门编号是否已存在的验证
	@RequestMapping("/didAjax")
	@ResponseBody
	public String didAjax(Integer did) {
		System.out.println(did);
		
		Dept dept = service.findByDid(did);
		if(dept == null) {
			return "0";
		}
		
		return "1";
		
	}
	
	//部门名称是否已存在的验证
	@RequestMapping("/dnameAjax")
	@ResponseBody
	public String dnameAjax(String dname) {
		System.out.println(dname);
			
		List<Dept> list = service.findByDname(dname);
		//判断list是否为空
		if(list.size() > 0) {
			return "1";
		}
			
		return "0";
			
	}
	
	//查询所有部门
	//Dept分页操作
		@RequestMapping(value="/findDepts",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findDepts(@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
			PageHelper.startPage(currentPage,pageSize);
			
			//先查询所有的数据
			List<Dept> list = service.findDepts();
			PageInfo<Dept> pageInfo = new PageInfo<>(list);
			DeptExample example = new DeptExample();
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
		
	//模糊查询
		@RequestMapping(value="/findDeptsLike",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findDeptsLike(Dept dept,@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			System.out.println("dept.did------>"+dept);
			
			//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
			PageHelper.startPage(currentPage,pageSize);
			
			//模糊查询数据
			List<Dept> list = service.findDeptsLike(dept);
			PageInfo<Dept> pageInfo = new PageInfo<>(list);
			DeptExample example = new DeptExample();
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
		
		//修改部门
		@RequestMapping("/updateDept")
		@ResponseBody
		public String updateDept(Dept dept) {
			System.out.println("dept-------->"+dept);
			
			//调用service层完成修改操作
			int in = service.updateDept(dept);
			Map<String, Object> result = new HashMap<String, Object>();
	  		if(in > 0) {
	  			System.out.println("修改成功");
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
		
		//删除部门
		@RequestMapping("/deleteDept")
		@ResponseBody
		public String deleteDept(Integer did) {
			System.out.println(did);
			
			//调用service完成删除
			int in = service.deleteDept(did);
			if(in > 0) {
				System.out.println("删除成功！");
				return "1";
			}
			return "0";
		}
		
	//查询部门的数量
	@RequestMapping("/countByDept")
	@ResponseBody
	public String countByDept() {
		DeptExample example = new DeptExample();
		int count = service.countByExample(example);
		System.out.println(count);
		//将count转成String类型
		String count1 = String.valueOf(count);
		return count1;
		
	}
		
}
