package com.wyu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.pojo.Uploadfile;
import com.wyu.pojo.UploadfileExample;
import com.wyu.pojo.User;
import com.wyu.service.UploadAndDLService;

@Controller
public class UploadAndDLController {
	@Autowired
	private UploadAndDLService service;
	private String path = "E:/upload";
	 @RequestMapping(value="/upload",method = {RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    // 不返回json数据就要添加ResponseBody标签
	    public String upload(@RequestParam("mf") MultipartFile  file , HttpServletRequest request,HttpSession session)  {
	   // String path = request.getSession().getServletContext().getRealPath("/FILE");
			User user=(User)session.getAttribute("user");
			String username="小智";
			String user_num="111";
			if(user==null)
			{
				System.out.println("NO");
			}
			else {
				username=user.getUsername();
				user_num=user.getNumber();
			}
			System.out.println(user);
			Uploadfile uploadfile = new Uploadfile();
			
		 	System.out.println(path);
		 // 构造文件夹对象
			File dirPath = new File(path);
			//int uid= getFileCount(dirPath)+1;
			List<Uploadfile> list = service.findUploadfiles();
			int uid=0;
			if (list.size()==0||list==null){
				uid=1;
			}
			else {
				uid = list.get(list.size()-1).getId()+1;
			}
			uploadfile.setId(uid);
			if (!dirPath.exists()) {
				dirPath.mkdirs();// 创建文件夹
			}
			
			String originName = file.getOriginalFilename();
			uploadfile.setUserNum(user_num);
			uploadfile.setUploadname(username);
			uploadfile.setFilename(originName);
			uploadfile.setUploaddate(new Date());
			System.out.println(uploadfile);
			
			String sdf = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String newName = username + "_" + sdf + "_" + originName ;
			// 得到文件名
	        File newfile = new File(path, newName);
	         Map map = new HashMap();
	        System.out.printf(file.getOriginalFilename());

	        try {
	            file.transferTo(newfile);
	            map.put("msg","OK");
	            map.put("code","200");
	        } catch (IOException e) {
	            e.printStackTrace();
	            map.put("msg","erro");
	            map.put("code","0");
	            String res = JSON.toJSONString(map);
				System.out.println("---res:"+res);
		        return res;
	        }
	        if(map.get("code")=="200")
	        {
	        	int flag = service.insertUploadFile(uploadfile);
	        	if(flag!=0)
	        	{
	        		System.out.println("插入成功");
	        	}
	        	else {
	        		System.out.println("插入失败");
	        	}
	        }
	        String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
	        return res;
	    }
	public int getFileCount(File dirPath) {
		int fileCount=0;
		int folderCount=0;
		File list[] = dirPath.listFiles();
		for(int i = 0; i < list.length; i++){
		    if(list[i].isFile()){
		        fileCount++;
		    }else{
		    //    folderCount++;
		    }
		}
		 
		System.out.println("文件个数："+fileCount);
		//System.out.println("文件夹数："+folderCount);
		return fileCount;
	}
//分页操作
	@RequestMapping(value="/findUploadFiles",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUploadFiles(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
		List<Uploadfile> list = service.findUploadfiles();
		PageInfo<Uploadfile> pageInfo = new PageInfo<>(list);
		UploadfileExample example = new UploadfileExample();
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
	@RequestMapping(value = "/selectUploadfileLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectUploadfileLike(Uploadfile Uploadfile,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("Uploadfile.rid----->"+Uploadfile);
		//使用分页插件完成分页 page --> 当前页，rows --> 查询的条数
		PageHelper.startPage(currentPage,pageSize);
		
		//先查询所有的数据
		List<Uploadfile> list = service.selectUploadfileLike(Uploadfile);
		PageInfo<Uploadfile> pageInfo = new PageInfo<>(list);
		UploadfileExample example = new UploadfileExample();
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
		@RequestMapping(value="/deleteUploadfile",produces ="text/plain;charset=utf-8")
		@ResponseBody
		public String deleteUploadfile(Uploadfile uploadfile) {
			Map<String,String> map=new HashMap<>();
			Integer id = uploadfile.getId(); 
			System.out.println(uploadfile);
			if(id!=null)
			{
				Uploadfile upload_file = service.selectUploadfileByKey(id);
				if(upload_file!=null)
				{
					String deleteFilename=getDeleteFileName(upload_file);
					File file=new File(path,deleteFilename);
					if(file.exists())
					{
						System.out.println(file.getName());
						boolean flag=file.delete();
						int flag_=service.deleteUploadfile(id);
						if(flag==true&&flag_!=0)
						{
							map.put("code", "200");
							map.put("msg", "成功");
							map.put("Info","恭喜您，删除成功！");
							//将其转换为JSON数据，并压入值栈返回
							String res = JSON.toJSONString(map);
							System.out.println("---res:"+res);
							return res;
						}
					}
					
				}
			}
			map.put("code", "0");
			map.put("msg", "失败");
			map.put("Info","删除失败,找不到该文件可能已被删除，请刷新再试!");
			//将其转换为JSON数据，并压入值栈返回
		
			String res = JSON.toJSONString(map);
			System.out.println("---res:"+res);
			
			return res;
		}
		public String getDeleteFileName(Uploadfile upload_file) {
			String username=upload_file.getUploadname();
			String originName=upload_file.getFilename();
			String sdf = new SimpleDateFormat("yyyyMMddHHmmss").format(upload_file.getUploaddate());
			String filename=username+"_"+sdf+"_"+originName;
			System.out.println(filename);
			return filename;
		}
		
		@RequestMapping("/countByUploadfile")
		@ResponseBody
		public String countByUploadfile() {
			UploadfileExample example = new UploadfileExample();
			int count = service.countByExample(example);
			String count1 = String.valueOf(count);
			return count1;
		}
		 @RequestMapping(value="download",method=RequestMethod.GET)
		    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
			 	int id=Integer.valueOf(request.getHeader("id"));
		    	System.out.println(id);
		    	Uploadfile uploadfile=new Uploadfile();
		    	uploadfile.setId(id);
				Uploadfile upload_file = service.selectUploadfileByKey(id);
				String filename=getDeleteFileName(upload_file);
				File file = new File(path,filename);
		        if (!file.exists()) {
		        	response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
		        	response.getWriter().print("<html><body><script type='text/javascript'>alert('您要下载的资源已被删除！');</script></body></html>");
		        	response.getWriter().close(); 
		            System.out.println("您要下载的资源已被删除！！");  
		            return;  
				}
	            System.out.println(file.getAbsoluteFile());  
	            System.out.println(file.getName()); 
		        System.out.println("您要下载的资源存在！");  
		        //转码，免得文件名中文乱码  
		        filename = URLEncoder.encode(filename,"UTF-8");  
		        //设置文件下载头  
		        response.addHeader("Content-Disposition", "attachment;filename=" + filename);     
		        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
		        response.setContentType("multipart/form-data"); 
		        // 读取要下载的文件，保存到文件输入流
		        FileInputStream in = new FileInputStream(file);
		        // 创建输出流
		        OutputStream out = response.getOutputStream();
		        // 创建缓冲区
		        byte buffer[] = new byte[1024]; 
		        int len = 0;
		        //循环将输入流中的内容读取到缓冲区当中
		        while((len = in.read(buffer)) > 0){
		        	out.write(buffer, 0, len);
		        }
		        //关闭文件输入流
		        in.close();
		        // 关闭输出流
		        out.close();
		        //response.addHeader("filename", filename);
		    	
		}
}
