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

	//添加部门
	@Override
	public int addDept(Dept dept) {
		// TODO Auto-generated method stub
		return mapper.insert(dept);
	}
	
	//查询所有部门

	@Override
	public List<Dept> findDepts() {
		
		return mapper.selectByExample(new DeptExample());
	}

	@Override
	public int getDeptsCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//部门数据的数量
	@Override
	public int countByExample(DeptExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}

	//通过部门编号查询部门数据
	@Override
	public Dept findByDid(Integer did) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(did);
	}

	//通过部门名称查询部门数据
	@Override
	public List<Dept> findByDname(String dname) {
		// TODO Auto-generated method stub
		DeptExample example1 = new DeptExample();
		Criteria criteria = example1.createCriteria();
		criteria.andDnameEqualTo(dname);
		return mapper.selectByExample(example1);
	}

	//模糊查询
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

	//修改部门数据
	@Override
	public int updateDept(Dept dept) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(dept);
	}

	//删除部门数据
	@Override
	public int deleteDept(Integer did) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(did);
	}

}
