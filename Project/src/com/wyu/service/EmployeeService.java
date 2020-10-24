package com.wyu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wyu.pojo.Employee;
import com.wyu.pojo.EmployeeExample;




public interface EmployeeService {

	int addEmployee(Employee employee);

	List<Employee> findEmployees();

	int countByExample(EmployeeExample example);

	List<Employee> findUsersLike(Employee employee);

	int deleteEmployee(int id);

	int updateemployee(Employee employee);

	Employee findById(Integer id);
	}
