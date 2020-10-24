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
	    // ������json���ݾ�Ҫ���ResponseBody��ǩ
	    public String upload(@RequestParam("mf") MultipartFile  file , HttpServletRequest request,HttpSession session)  {
	   // String path = request.getSession().getServletContext().getRealPath("/FILE");
			User user=(User)session.getAttribute("user");
			String username="С��";
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
		 // �����ļ��ж���
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
				dirPath.mkdirs();// �����ļ���
			}
			
			String originName = file.getOriginalFilename();
			uploadfile.setUserNum(user_num);
			uploadfile.setUploadname(username);
			uploadfile.setFilename(originName);
			uploadfile.setUploaddate(new Date());
			System.out.println(uploadfile);
			
			String sdf = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String newName = username + "_" + sdf + "_" + originName ;
			// �õ��ļ���
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
	        		System.out.println("����ɹ�");
	        	}
	        	else {
	        		System.out.println("����ʧ��");
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
		 
		System.out.println("�ļ�������"+fileCount);
		//System.out.println("�ļ�������"+folderCount);
		return fileCount;
	}
//��ҳ����
	@RequestMapping(value="/findUploadFiles",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String findUploadFiles(@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
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
		
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
		
	}
//ģ����ѯ	
	@RequestMapping(value = "/selectUploadfileLike",produces = "applications/json;charset=utf-8")
	@ResponseBody
	public String selectUploadfileLike(Uploadfile Uploadfile,@RequestParam(value="page",defaultValue="1")Integer currentPage,
			@RequestParam(value = "limit",defaultValue = "4")Integer pageSize,Model model) {
		
		System.out.println("Uploadfile.rid----->"+Uploadfile);
		//ʹ�÷�ҳ�����ɷ�ҳ page --> ��ǰҳ��rows --> ��ѯ������
		PageHelper.startPage(currentPage,pageSize);
		
		//�Ȳ�ѯ���е�����
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
		
		//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		result.put("data", pageInfo.getList());
		
		System.out.println(json);
		String res = JSON.toJSONString(result);
		System.out.println("---res:"+res);
		
		return res;
	}
//ɾ����ɫ
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
							map.put("msg", "�ɹ�");
							map.put("Info","��ϲ����ɾ���ɹ���");
							//����ת��ΪJSON���ݣ���ѹ��ֵջ����
							String res = JSON.toJSONString(map);
							System.out.println("---res:"+res);
							return res;
						}
					}
					
				}
			}
			map.put("code", "0");
			map.put("msg", "ʧ��");
			map.put("Info","ɾ��ʧ��,�Ҳ������ļ������ѱ�ɾ������ˢ������!");
			//����ת��ΪJSON���ݣ���ѹ��ֵջ����
		
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
		        	response.setContentType("text/html; charset=UTF-8");//ע��text/html����application/html
		        	response.getWriter().print("<html><body><script type='text/javascript'>alert('��Ҫ���ص���Դ�ѱ�ɾ����');</script></body></html>");
		        	response.getWriter().close(); 
		            System.out.println("��Ҫ���ص���Դ�ѱ�ɾ������");  
		            return;  
				}
	            System.out.println(file.getAbsoluteFile());  
	            System.out.println(file.getName()); 
		        System.out.println("��Ҫ���ص���Դ���ڣ�");  
		        //ת�룬����ļ�����������  
		        filename = URLEncoder.encode(filename,"UTF-8");  
		        //�����ļ�����ͷ  
		        response.addHeader("Content-Disposition", "attachment;filename=" + filename);     
		        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
		        response.setContentType("multipart/form-data"); 
		        // ��ȡҪ���ص��ļ������浽�ļ�������
		        FileInputStream in = new FileInputStream(file);
		        // ���������
		        OutputStream out = response.getOutputStream();
		        // ����������
		        byte buffer[] = new byte[1024]; 
		        int len = 0;
		        //ѭ�����������е����ݶ�ȡ������������
		        while((len = in.read(buffer)) > 0){
		        	out.write(buffer, 0, len);
		        }
		        //�ر��ļ�������
		        in.close();
		        // �ر������
		        out.close();
		        //response.addHeader("filename", filename);
		    	
		}
}
