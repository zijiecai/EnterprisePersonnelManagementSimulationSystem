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
		String addInfo = "��ϲ��!��ӳɹ�!";
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		String rname = role.getRname();
		boolean flag_=service.selectRoleByName(role);
		if(flag_==true) {
			addInfo = "���ʧ��,��ɫ����Ϊ"+role.getRname()+"�Ľ�ɫ�Ѵ���!";
		}
		int flag = service.addRole(role);
		if(flag!=0)
		{
			System.out.println("��ӳɹ�");
		}
		else {
			addInfo = "���ʧ��,��ɫ���Ϊ"+role.getRid()+"�Ľ�ɫ�Ѵ���!";
			System.out.println("���ʧ��");
		}
		model.addAttribute("addInfo", addInfo);
		return "addRole";
	}
	@RequestMapping(value = "/selectRoleLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectRoleLike(Role role,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("role.rid----->"+role);
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
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
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
	}

	//Role��ҳ����
		@RequestMapping(value="/findAllRoles",produces = "applications/json;charset=utf-8")
		@ResponseBody
		public String findAllRoles(@RequestParam(value="page",defaultValue="1")Integer currentPage,
				@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
			
			//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
			PageHelper.startPage(currentPage,pageSize);
			
			//�Ȳ�ѯ���е�����
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
			
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
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
			String updateInfo="��ϲ��,�޸ĳɹ�";
			if(flag!=0)
			{
				System.out.println("�޸ĳɹ�");
				map.put("code", "200");
				map.put("msg", "�ɹ�");
			}
			else {
				updateInfo = "�޸�ʧ��,��ɫ���Ϊ"+role.getRid()+"�Ľ�ɫ������!";
				System.out.println("�޸�ʧ��");
				map.put("code", "0");
				map.put("msg", "ʧ��");
			}
			//model.addAttribute("updateInfo", updateInfo);
			
			map.put("Info", updateInfo);
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		
			String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
			
			return res;
		}
		
		//ɾ����ɫ
		@RequestMapping(value="/deleteRole",produces ="text/plain;charset=utf-8")
		@ResponseBody

		public String deleteRole(Role role) {
			Map<String,String> map=new HashMap<>();
			Integer rid = role.getRid(); 
			System.out.println("rid-----------"+rid);
			//����service���ɾ��
			int flag = service.deleteRole(rid);
			if(flag != 0) {
				System.out.println("ɾ���ɹ���");
				map.put("code", "200");
				map.put("msg", "�ɹ�");
				map.put("Info","��ϲ��ɾ���ɹ�!");
				String res = JSON.toJSONString(map);
				System.out.println("---res:"+res);
				return res;
			}
			map.put("code", "0");
			map.put("msg", "ʧ��");
			map.put("Info","���ź�ɾ��ʧ��,�ý�ɫ���ڼ�ֵ����,����ɾ������ɾ��ʹ�øý�ɫ���û�!");

			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		
			String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
			
			return res;
		}
		@RequestMapping(value="/findRoles",produces = "application/json;charset=utf-8")
		@ResponseBody
		public String findRoles() {
			
			//����service���������
			List<Role> list = service.findRoles();
			//���õ��Ľ����������json����
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
