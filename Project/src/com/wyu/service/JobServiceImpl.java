package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.JobMapper;
import com.wyu.pojo.Job;
import com.wyu.pojo.JobExample;
import com.wyu.pojo.JobExample.Criteria;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobMapper mapper;

	//添加职位
	@Override
	public int addJob(Job job) {
		// TODO Auto-generated method stub
		return mapper.insert(job);
	}

	//通过职位编号查询职位数据
	@Override
	public Job findById(Integer jid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(jid);
	}

	//通过职位名称查询职位数据
	@Override
	public List<Job> findByJname(String jname) {
		// TODO Auto-generated method stub
		JobExample example1 = new JobExample();
		Criteria criteria = example1.createCriteria();
		criteria.andJnameEqualTo(jname);
		return mapper.selectByExample(example1);
	}

	//查询所有职位数据
	@Override
	public List<Job> findJobs() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(new JobExample());
	}

	//查询所有职位的数量
	@Override
	public int countByExample(JobExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}
	
	//模糊查询职位
	@Override
	public List<Job> findJobsLike(Job job) {
		// TODO Auto-generated method stub
		JobExample example2 = new JobExample();
		Criteria criteria = example2.createCriteria();
		if(job.getJid() != null) {
			criteria.andJidEqualTo(job.getJid());
		}
		criteria.andJnameLike("%"+job.getJname()+"%");
		
		example2.or();
		
		return mapper.selectByExample(example2);
	}

	//修改职位
	@Override
	public int updateJob(Job job) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(job);
	}

	//删除职位
	@Override
	public int deleteJob(Integer jid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(jid);
	}

}
