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
import com.wyu.pojo.Job;
import com.wyu.pojo.JobExample;
import com.wyu.service.JobService;

@Controller
public class JobController {

	@Autowired
	private JobService service;
	
	//添加职位
	@RequestMapping("/addJob")
	public String addJob(Job job,Model model) {
		System.out.println("--------->"+job);
		
		int in = service.addJob(job);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("添加成功！");
			addInfo = "1";
		}
		//将添加的结果用model放到request域中
		model.addAttribute("addInfo1", addInfo);
		
		return "addPost";
		
	}
	
	//职位编号是否已存在的验证
	@RequestMapping("/jidAjax")
	@ResponseBody
	public String jidAjax(Integer jid) {
		System.out.println(jid);
		Job job = service.findById(jid);
		if(job == null) {
			return "0";
		}
		
		return "1";
		
	}
	
	//职位名称是否已存在的验证
		@RequestMapping("/jnameAjax")
		@ResponseBody
		public String jnameAjax(String jname) {
			System.out.println(jname);
			List<Job> list = service.findByJname(jname);
			//判断list是否为空
			if(list.size() > 0) {
				return "1";
			}
			
			return "0";
			
		}
	
		    //查询所有职位
		    //Job分页操作
			@RequestMapping(value="/findJobs",produces = "applications/json;charset=utf-8")
			@ResponseBody
			public String findJobs(@RequestParam(value="page",defaultValue="1")Integer currentPage,
					@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
				
				//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
				PageHelper.startPage(currentPage,pageSize);
				
				//先查询所有的数据
				List<Job> list = service.findJobs();
				PageInfo<Job> pageInfo = new PageInfo<>(list);
				JobExample example = new JobExample();
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
			
		//模糊查询职位
		//Job分页操作
		@RequestMapping(value="/findJobsLike",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findJobsLike(Job job,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
					
			//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
			PageHelper.startPage(currentPage,pageSize);
					
			//先查询所有的数据
			List<Job> list = service.findJobsLike(job);
			PageInfo<Job> pageInfo = new PageInfo<>(list);
			JobExample example = new JobExample();
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
	
	//修改职位
	@RequestMapping("/updateJob")
	@ResponseBody
	public String updateJob(Job job) {
		System.out.println("job---------->"+job);
		
		//调用service层完成修改操作
		int in = service.updateJob(job);
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
		
	//删除职位
		@RequestMapping("/deleteJob")
	public String deleteJob(Integer jid) {
		System.out.println(jid);
		
		//调用service层完成删除操作
		int in = service.deleteJob(jid);
		if(in > 0) {
			System.out.println("删除成功");
			return "1";
		}
		return "0";
		
	}
		
	//查询职位的数量
	@RequestMapping("/countByJob")
	@ResponseBody
	public String countByJob() {
		JobExample example = new JobExample();
		int count = service.countByExample(example);
		String count1 = String.valueOf(count);
		return count1;
	}
		
}
