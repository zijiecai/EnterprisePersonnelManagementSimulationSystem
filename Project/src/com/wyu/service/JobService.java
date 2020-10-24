package com.wyu.service;

import java.util.List;

import com.wyu.pojo.Job;
import com.wyu.pojo.JobExample;

public interface JobService {

	//���ְλ
	int addJob(Job job);

	//ͨ������(ְλ���)��ѯְλ����
	Job findById(Integer jid);

	//ͨ��ְλ���Ʋ�ѯְλ����
	List<Job> findByJname(String jname);

	//��ѯ����ְλ����
	List<Job> findJobs();

	//��ѯְλ���ݵ�����
	int countByExample(JobExample example);
	
	//ģ����ѯְλ
	List<Job> findJobsLike(Job job);

	//�޸�ְλ
	int updateJob(Job job);

	//ɾ��ְλ
	int deleteJob(Integer jid);

}
