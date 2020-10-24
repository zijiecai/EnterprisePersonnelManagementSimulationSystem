package com.wyu.service;

import java.util.List;

import com.wyu.pojo.Dept;
import com.wyu.pojo.DeptExample;

public interface DeptService {

	//添加部门
	int addDept(Dept dept);
	
	//通过主键(部门编号)查询部门数据
	Dept findByDid(Integer did);
	
	//查询所有部门的数据
	List<Dept> findDepts();
	
	int getDeptsCount();
	
	int countByExample(DeptExample example);

	//通过部门名称查询部门数据
	List<Dept> findByDname(String dname);

	
	//模糊查询
	List<Dept> findDeptsLike(Dept dept);

	//修改数据
	int updateDept(Dept dept);

	//删除数据
	int deleteDept(Integer did);

}
