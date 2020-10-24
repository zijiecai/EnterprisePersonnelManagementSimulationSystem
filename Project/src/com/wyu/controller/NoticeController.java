package com.wyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.Notice;
import com.wyu.pojo.NoticeExample;
import com.wyu.pojo.User;
import com.wyu.service.NoticeService;
@Controller

public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value = {"/addNotice"},produces = "text/plain;charset=utf-8")
	public String addNotice(Notice notice,HttpSession session,Model model) {
		System.out.println(notice);
		System.out.println(notice.getName());
		System.out.println();
		User user=(User)session.getAttribute("user");
		String username="1";
		String user_num="1";
		if(user==null)
		{
			System.out.println("NO");
		}
		else {
			username=user.getUsername();
			user_num=user.getNumber();
		}
		System.out.println(user);
		notice.setUsername(username);
		notice.setUserNum(user_num);
		String addInfo = "��ϲ��!��ӳɹ�!";
		
		int flag = service.addNotice(notice);
		if(flag!=0)
		{
			System.out.println("��ӳɹ�");
		}
		else {
			addInfo = "���ʧ��,������Ϊ"+notice.getId()+"�Ĺ����Ѵ���!";
			System.out.println("���ʧ��");
		}
		model.addAttribute("addInfo", addInfo);
		return "addNotice";
	}
//�����ҳ����
	@RequestMapping(value="/findNotices",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUsers(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
		List<Notice> list = service.findNotices();
		PageInfo<Notice> pageInfo = new PageInfo<>(list);
		NoticeExample example =new NoticeExample();
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
	@RequestMapping(value = "/selectNoticeLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectNoticeLike(Notice notice,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("notice.rid----->"+notice);
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
		List<Notice> list = service.selectNoticeLike(notice);
		PageInfo<Notice> pageInfo = new PageInfo<>(list);
		NoticeExample example =new NoticeExample();
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
	//ɾ����ɫ
	@RequestMapping(value="/deleteNotice",produces ="text/plain;charset=utf-8")
	@ResponseBody
	
	public String deleteNotice(Notice notice) {
		Map<String,String> map=new HashMap<>();
		Integer rid = notice.getId(); 
		System.out.println("rid-----------"+rid);
		//����service���ɾ��
		int flag = service.deleteNotice(rid);
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
		map.put("Info","���ź�ɾ��ʧ��,�������쳣����!");
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		
		String res = JSON.toJSONString(map);
		System.out.println("---res:"+res);
		
		return res;
	}/*
	@RequestMapping(value="updateNotice",produces ="applications/json;charset=utf-8")
	@ResponseBody
	public String updateNotice(Notice notice,Model model) {
		Map<String,String> map=new HashMap<>();
		System.out.println("notice-------->"+notice);
		int flag = service.updateNotice(notice);
		String updateInfo="��ϲ��,�޸ĳɹ�";
		if(flag!=0)
		{
			System.out.println("�޸ĳɹ�");
			map.put("code", "200");
			map.put("msg", "�ɹ�");
		}
		else {
			updateInfo = "�޸�ʧ��,���Ϊ"+notice.getId()+"�Ĺ��治����!";
			System.out.println("�޸�ʧ��");
			map.put("code", "0");
			map.put("msg", "ʧ��");
		}
		map.put("Info", updateInfo);
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		String res = JSON.toJSONString(map);
		System.out.println("---res:"+res);
		return res;
	}*/

@RequestMapping(value="updateNotice",produces ="text/plain;charset=utf-8",method = RequestMethod.POST)
@ResponseBody
public String updateNotice(Notice notice,Model model) {
	Map<String,String> map=new HashMap<>();
	System.out.println("notice-------->"+notice);
	int flag = service.updateNotice(notice);
	String updateInfo="��ϲ��,�޸ĳɹ�";
	if(flag!=0)
	{
		System.out.println("�޸ĳɹ�");
		map.put("code", "200");
		map.put("msg", "�ɹ�");
	}
	else {
		updateInfo = "�޸�ʧ��,��ɫ���Ϊ"+notice.getId()+"�Ľ�ɫ������!";
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

@RequestMapping("/countByNotice")
@ResponseBody
public String countByNotice() {
 NoticeExample example = new NoticeExample();
 int count = service.countByExample(example);
 String count1 = String.valueOf(count);
 return count1;
}

}
