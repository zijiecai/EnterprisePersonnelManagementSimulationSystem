package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.UploadfileMapper;
import com.wyu.pojo.RoleExample;
import com.wyu.pojo.Uploadfile;
import com.wyu.pojo.UploadfileExample;
import com.wyu.pojo.UploadfileExample.Criteria;

@Service
public class UploadAndDLServiceImpl implements UploadAndDLService {
	@Autowired
	private UploadfileMapper mapper;
	@Override
	public int insertUploadFile(Uploadfile uploadfile) {
		return mapper.insert(uploadfile);
	}
	@Override
	public List<Uploadfile> findUploadfiles(){
		return mapper.selectByExample(new UploadfileExample());
	}
	@Override
	public Uploadfile selectUploadfileByKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteUploadfile(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Uploadfile> selectUploadfileLike(Uploadfile uploadfile){
		UploadfileExample example = new UploadfileExample();
		Criteria criteria = example.createCriteria();
		String username=uploadfile.getUploadname();
		String filename=uploadfile.getFilename();
		if(username!=null&&username!="")
		{
			criteria.andUploadnameLike("%"+username+"%");
		}
		if(filename!=null&&filename!="")
		{
			criteria.andFilenameLike("%"+filename+"%");

		}
		List<Uploadfile> UploadfileList =mapper.selectByExample(example);
		return UploadfileList;
	}
	@Override
	public int countByExample(UploadfileExample example) {
		return mapper.countByExample(example);
	}
}
