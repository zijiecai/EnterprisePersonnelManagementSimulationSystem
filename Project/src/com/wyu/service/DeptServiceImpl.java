package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.DeptMapper;
import com.wyu.pojo.Dept;
import com.wyu.pojo.DeptExample;
import com.wyu.pojo.DeptExample.Criteria;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper mapper;

	//��Ӳ���
	@Override
	public int addDept(Dept dept) {
		// TODO Auto-generated method stub
		return mapper.insert(dept);
	}
	
	//��ѯ���в���

	@Override
	public List<Dept> findDepts() {
		
		return mapper.selectByExample(new DeptExample());
	}

	@Override
	public int getDeptsCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//�������ݵ�����
	@Override
	public int countByExample(DeptExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}

	//ͨ�����ű�Ų�ѯ��������
	@Override
	public Dept findByDid(Integer did) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(did);
	}

	//ͨ���������Ʋ�ѯ��������
	@Override
	public List<Dept> findByDname(String dname) {
		// TODO Auto-generated method stub
		DeptExample example1 = new DeptExample();
		Criteria criteria = example1.createCriteria();
		criteria.andDnameEqualTo(dname);
		return mapper.selectByExample(example1);
	}

	//ģ����ѯ
	@Override
	public List<Dept> findDeptsLike(Dept dept) {
		// TODO Auto-generated method stub
		DeptExample example2 = new DeptExample();
		Criteria criteria = example2.createCriteria();
		System.out.println(dept.getDid());
		if(dept.getDid() != null) {
			criteria.andDidEqualTo(dept.getDid());
		}
		criteria.andDnameLike("%"+dept.getDname()+"%");
		
		example2.or();
		
		return mapper.selectByExample(example2);
	}

	//�޸Ĳ�������
	@Override
	public int updateDept(Dept dept) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(dept);
	}

	//ɾ����������
	@Override
	public int deleteDept(Integer did) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(did);
	}

}
