package com.wyu.service;

import java.util.List;

import com.wyu.pojo.Job;
import com.wyu.pojo.JobExample;

public interface JobService {

	//添加职位
	int addJob(Job job);

	//通过主键(职位编号)查询职位数据
	Job findById(Integer jid);

	//通过职位名称查询职位数据
	List<Job> findByJname(String jname);

	//查询所有职位数据
	List<Job> findJobs();

	//查询职位数据的数量
	int countByExample(JobExample example);
	
	//模糊查询职位
	List<Job> findJobsLike(Job job);

	//修改职位
	int updateJob(Job job);

	//删除职位
	int deleteJob(Integer jid);

}
