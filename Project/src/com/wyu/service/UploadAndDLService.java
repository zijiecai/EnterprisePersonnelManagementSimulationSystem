package com.wyu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wyu.pojo.DeptExample;
import com.wyu.pojo.Uploadfile;
import com.wyu.pojo.UploadfileExample;

@Service
public interface UploadAndDLService {
	public int insertUploadFile(Uploadfile uploadfile);
	public List<Uploadfile> findUploadfiles();
	public List<Uploadfile> selectUploadfileLike(Uploadfile uploadfile);
	public Uploadfile selectUploadfileByKey(Integer id);
	public int deleteUploadfile(Integer id);
	public int countByExample(UploadfileExample example);
}
