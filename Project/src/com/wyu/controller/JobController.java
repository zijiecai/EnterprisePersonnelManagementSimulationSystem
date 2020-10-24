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
	
	//���ְλ
	@RequestMapping("/addJob")
	public String addJob(Job job,Model model) {
		System.out.println("--------->"+job);
		
		int in = service.addJob(job);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("��ӳɹ���");
			addInfo = "1";
		}
		//����ӵĽ����model�ŵ�request����
		model.addAttribute("addInfo1", addInfo);
		
		return "addPost";
		
	}
	
	//ְλ����Ƿ��Ѵ��ڵ���֤
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
	
	//ְλ�����Ƿ��Ѵ��ڵ���֤
		@RequestMapping("/jnameAjax")
		@ResponseBody
		public String jnameAjax(String jname) {
			System.out.println(jname);
			List<Job> list = service.findByJname(jname);
			//�ж�list�Ƿ�Ϊ��
			if(list.size() > 0) {
				return "1";
			}
			
			return "0";
			
		}
	
		    //��ѯ����ְλ
		    //Job��ҳ����
			@RequestMapping(value="/findJobs",produces = "applications/json;charset=utf-8")
			@ResponseBody
			public String findJobs(@RequestParam(value="page",defaultValue="1")Integer currentPage,
					@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
				
				//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
				PageHelper.startPage(currentPage,pageSize);
				
				//�Ȳ�ѯ���е�����
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
				
				//����ת��ΪJSON���ݣ���ѹ��ֵջ����
				result.put("data", pageInfo.getList());
				
				System.out.println(json);
				String res = JSON.toJSONString(result);
				System.out.println("---res:"+res);
				
				return res;
				
			}
			
		//ģ����ѯְλ
		//Job��ҳ����
		@RequestMapping(value="/findJobsLike",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findJobsLike(Job job,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
					
			//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
			PageHelper.startPage(currentPage,pageSize);
					
			//�Ȳ�ѯ���е�����
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
					
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
			result.put("data", pageInfo.getList());
					
			System.out.println(json);
			String res = JSON.toJSONString(result);
			System.out.println("---res:"+res);
					
			return res;
					
		}
	
	//�޸�ְλ
	@RequestMapping("/updateJob")
	@ResponseBody
	public String updateJob(Job job) {
		System.out.println("job---------->"+job);
		
		//����service������޸Ĳ���
		int in = service.updateJob(job);
		Map<String, Object> result = new HashMap<String, Object>();
  		if(in > 0) {
  			System.out.println("�޸ĳɹ�");
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
		
	//ɾ��ְλ
		@RequestMapping("/deleteJob")
	public String deleteJob(Integer jid) {
		System.out.println(jid);
		
		//����service�����ɾ������
		int in = service.deleteJob(jid);
		if(in > 0) {
			System.out.println("ɾ���ɹ�");
			return "1";
		}
		return "0";
		
	}
		
	//��ѯְλ������
	@RequestMapping("/countByJob")
	@ResponseBody
	public String countByJob() {
		JobExample example = new JobExample();
		int count = service.countByExample(example);
		String count1 = String.valueOf(count);
		return count1;
	}
		
}
