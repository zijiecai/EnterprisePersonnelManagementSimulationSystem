package com.wyu.service;

import java.util.List;

import com.wyu.pojo.Dept;
import com.wyu.pojo.DeptExample;

public interface DeptService {

	//��Ӳ���
	int addDept(Dept dept);
	
	//ͨ������(���ű��)��ѯ��������
	Dept findByDid(Integer did);
	
	//��ѯ���в��ŵ�����
	List<Dept> findDepts();
	
	int getDeptsCount();
	
	int countByExample(DeptExample example);

	//ͨ���������Ʋ�ѯ��������
	List<Dept> findByDname(String dname);

	
	//ģ����ѯ
	List<Dept> findDeptsLike(Dept dept);

	//�޸�����
	int updateDept(Dept dept);

	//ɾ������
	int deleteDept(Integer did);

}
