package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.EmployeeMapper;
import com.wyu.pojo.Employee;
import com.wyu.pojo.EmployeeExample;
import com.wyu.pojo.EmployeeExample.Criteria;



@Service
//涓氬姟灞�
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper mapper;
	
	
	@Override
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return mapper.insert(employee);
	}


	@Override
	public List<Employee> findEmployees() {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		List<Employee> list = mapper.selectByExample(example);
				
		list.forEach(li->System.out.println("----------------------銆�"+li));
				
		return list;
	}


	@Override
	public int countByExample(EmployeeExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}


	@Override
	public List<Employee> findUsersLike(Employee employee) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameLike("%" + employee.getName()+"%");
		criteria.andPhoneLike("%" + employee.getPhone()+"%");
		
		example.or();
		
		return mapper.selectByExample(example);
	}


	@Override
	public int deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}


	@Override
	public int updateemployee(Employee employee) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(employee);
	}


	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}
}
