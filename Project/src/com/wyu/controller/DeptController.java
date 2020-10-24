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
	
	//��Ӳ���
	@RequestMapping("/addDept")
	public String addDept(Dept dept,Model model) {
		System.out.println("-------->"+dept);
		
		int in = service.addDept(dept);
		String addInfo = "0";
		if(in > 0) {
			System.out.println("��ӳɹ���");
			addInfo = "1";
		}
		//����ӵĽ����model�ŵ�request����
		model.addAttribute("addInfo", addInfo);
		
		return "addDepartment";
		
	}
	
	//���ű���Ƿ��Ѵ��ڵ���֤
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
	
	//���������Ƿ��Ѵ��ڵ���֤
	@RequestMapping("/dnameAjax")
	@ResponseBody
	public String dnameAjax(String dname) {
		System.out.println(dname);
			
		List<Dept> list = service.findByDname(dname);
		//�ж�list�Ƿ�Ϊ��
		if(list.size() > 0) {
			return "1";
		}
			
		return "0";
			
	}
	
	//��ѯ���в���
	//Dept��ҳ����
		@RequestMapping(value="/findDepts",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findDepts(@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
			PageHelper.startPage(currentPage,pageSize);
			
			//�Ȳ�ѯ���е�����
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
			
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
			result.put("data", pageInfo.getList());
			
			System.out.println(json);
			String res = JSON.toJSONString(result);
			System.out.println("---res:"+res);
			
			return res;
			
		}
		
	//ģ����ѯ
		@RequestMapping(value="/findDeptsLike",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findDeptsLike(Dept dept,@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			System.out.println("dept.did------>"+dept);
			
			//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
			PageHelper.startPage(currentPage,pageSize);
			
			//ģ����ѯ����
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
			
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
			result.put("data", pageInfo.getList());
			
			System.out.println(json);
			String res = JSON.toJSONString(result);
			System.out.println("---res:"+res);
			
			return res;
			
		}
		
		//�޸Ĳ���
		@RequestMapping("/updateDept")
		@ResponseBody
		public String updateDept(Dept dept) {
			System.out.println("dept-------->"+dept);
			
			//����service������޸Ĳ���
			int in = service.updateDept(dept);
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
		
		//ɾ������
		@RequestMapping("/deleteDept")
		@ResponseBody
		public String deleteDept(Integer did) {
			System.out.println(did);
			
			//����service���ɾ��
			int in = service.deleteDept(did);
			if(in > 0) {
				System.out.println("ɾ���ɹ���");
				return "1";
			}
			return "0";
		}
		
	//��ѯ���ŵ�����
	@RequestMapping("/countByDept")
	@ResponseBody
	public String countByDept() {
		DeptExample example = new DeptExample();
		int count = service.countByExample(example);
		System.out.println(count);
		//��countת��String����
		String count1 = String.valueOf(count);
		return count1;
		
	}
		
}
