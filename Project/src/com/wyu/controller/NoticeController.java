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
		String addInfo = "恭喜你!添加成功!";
		
		int flag = service.addNotice(notice);
		if(flag!=0)
		{
			System.out.println("添加成功");
		}
		else {
			addInfo = "添加失败,公告编号为"+notice.getId()+"的公告已存在!";
			System.out.println("添加失败");
		}
		model.addAttribute("addInfo", addInfo);
		return "addNotice";
	}
//公告分页操作
	@RequestMapping(value="/findNotices",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUsers(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
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
		
		//将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
		
	}
	//模糊查询
	@RequestMapping(value = "/selectNoticeLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectNoticeLike(Notice notice,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("notice.rid----->"+notice);
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
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
		
		//将其转换为JSON数据，并压入值栈返回
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
	}
	//删除角色
	@RequestMapping(value="/deleteNotice",produces ="text/plain;charset=utf-8")
	@ResponseBody
	
	public String deleteNotice(Notice notice) {
		Map<String,String> map=new HashMap<>();
		Integer rid = notice.getId(); 
		System.out.println("rid-----------"+rid);
		//调用service完成删除
		int flag = service.deleteNotice(rid);
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
		map.put("Info","很遗憾删除失败,出现了异常错误!");
		//将其转换为JSON数据，并压入值栈返回
		
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
		String updateInfo="恭喜您,修改成功";
		if(flag!=0)
		{
			System.out.println("修改成功");
			map.put("code", "200");
			map.put("msg", "成功");
		}
		else {
			updateInfo = "修改失败,编号为"+notice.getId()+"的公告不存在!";
			System.out.println("修改失败");
			map.put("code", "0");
			map.put("msg", "失败");
		}
		map.put("Info", updateInfo);
		//将其转换为JSON数据，并压入值栈返回
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
	String updateInfo="恭喜您,修改成功";
	if(flag!=0)
	{
		System.out.println("修改成功");
		map.put("code", "200");
		map.put("msg", "成功");
	}
	else {
		updateInfo = "修改失败,角色编号为"+notice.getId()+"的角色不存在!";
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

@RequestMapping("/countByNotice")
@ResponseBody
public String countByNotice() {
 NoticeExample example = new NoticeExample();
 int count = service.countByExample(example);
 String count1 = String.valueOf(count);
 return count1;
}

}
