package com.wyu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.Employee;
import com.wyu.pojo.EmployeeExample;
import com.wyu.pojo.User;
import com.wyu.pojo.UserExample;
import com.wyu.service.EmployeeService;


@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService service;
	
	
	
	@RequestMapping("/addEmployee")
	public String addUser(Employee employee,Model model) {
		
		System.out.println("---------------->"+employee);
		employee.setCreateDate(new Date());
//		employee.setName("杰尼龟");
//		employee.setCardId("413026199902166012");
		int in = service.addEmployee(employee);
		String addInfo = "0";
		if(in>0) {
			System.out.println("添加成功");
			addInfo = "1";
		}
		
		//将addUser的结果用model放到request域中
		model.addAttribute("addInfo2",addInfo);
		
		return "addStaff";
	}
	
	//员工编号是否已存在的验证
		@RequestMapping("/idAjax")
		@ResponseBody
		public String idAjax(Integer id) {
			System.out.println(id);
			
			Employee employee = service.findById(id);
			if(employee == null) {
				return "0";
			}
			
			return "1";
			
		}
	
	   //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
    
    
    @RequestMapping(value="/findEmployees",produces="applications/json;charset=utf-8")
	@ResponseBody
	public String findEmployees(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value="limit",defaultValue="4")Integer pageSize,Model model) {
		List<Employee> list = null;
		//使用分页插件完成分页:page-->当前页，rows---》查询的条数
		PageHelper.startPage(currentPage,pageSize);
		//先查询所有数据
		list = service.findEmployees();
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		EmployeeExample example = new EmployeeExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		list.forEach(li->System.out.println(li));
		String json = JSON.toJSONString(list);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		// 将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("res: "+res);
		return res;
	}
	
    
    @RequestMapping(value="/findEmployeesLike",produces="applications/json;charset=utf-8")
	@ResponseBody
	public String findEmployeesLike(Employee employee,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value="limit",defaultValue="4")Integer pageSize,Model model) {
		
		System.out.println("employee.id"+employee);
		PageHelper.startPage(currentPage,pageSize);
		List<Employee> employee1 = service.findUsersLike(employee);
		PageInfo<Employee> pageInfo = new PageInfo<>(employee1);
		EmployeeExample example = new EmployeeExample();
		int count = service.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
	
		employee1.forEach(li->System.out.println(li));
		String json = JSON.toJSONString(employee1);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		// 将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		
		String res = JSON.toJSONString(result);
		
		System.out.println("------------->"+res);
		return res;
	}
    
    //删除数据
  	@ RequestMapping("/deleteEmployee")
  	@ResponseBody
  	public String deleteUser(int id) {
  		System.out.println("要删除的Number"+id);
  		//调用service完成
  		int in = service.deleteEmployee(id);
  		if (in > 0) {
  			System.out.println("删除成功");
  			return "1";
  		}
  		return "0";
  	} 
  	
  	
  	
  //修改部门
  	@RequestMapping("/updateEmployee")
  	@ResponseBody
  	public String updateDept(Employee employee) {
  		System.out.println("employee-------->"+employee);
  				
  		//调用service层完成修改操作
  		int in = service.updateemployee(employee);
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
  	
  	@RequestMapping("/countByEmployee")
  	@ResponseBody
  	public String countByEmployee() {
  		EmployeeExample example2 = new EmployeeExample();
  		int count = service.countByExample(example2);
  		System.out.println(count);
  		String count1 = String.valueOf(count);
  		
  		return count1;
  	}
  	
  	
}
