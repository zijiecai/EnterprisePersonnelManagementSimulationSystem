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

	//���ְλ
	@Override
	public int addJob(Job job) {
		// TODO Auto-generated method stub
		return mapper.insert(job);
	}

	//ͨ��ְλ��Ų�ѯְλ����
	@Override
	public Job findById(Integer jid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(jid);
	}

	//ͨ��ְλ���Ʋ�ѯְλ����
	@Override
	public List<Job> findByJname(String jname) {
		// TODO Auto-generated method stub
		JobExample example1 = new JobExample();
		Criteria criteria = example1.createCriteria();
		criteria.andJnameEqualTo(jname);
		return mapper.selectByExample(example1);
	}

	//��ѯ����ְλ����
	@Override
	public List<Job> findJobs() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(new JobExample());
	}

	//��ѯ����ְλ������
	@Override
	public int countByExample(JobExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}
	
	//ģ����ѯְλ
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

	//�޸�ְλ
	@Override
	public int updateJob(Job job) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(job);
	}

	//ɾ��ְλ
	@Override
	public int deleteJob(Integer jid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(jid);
	}

}
