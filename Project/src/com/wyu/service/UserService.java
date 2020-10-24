package com.wyu.service;

import java.util.List;

import com.wyu.pojo.User;
import com.wyu.pojo.UserExample;

public interface UserService {

	//1.登录
	User login(String number);
	
	//2.添加用户
	int addUser(User user);

	//根据手机号码查询用户
	List<User> fineByPhone(String phone);

	//查询所有的数据
	List<User> findUsers();
	
	int getUsersCount();
	
	//计数
	int countByExample(UserExample example);

	//更新用户
	int updateUser(User user);

	//模糊查询
	List<User> findUsersLike(User user);

	//删除用户
	int deleteUser(String number);

}
