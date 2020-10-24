package com.wyu.service;

import java.util.List;

import com.wyu.pojo.User;
import com.wyu.pojo.UserExample;

public interface UserService {

	//1.��¼
	User login(String number);
	
	//2.����û�
	int addUser(User user);

	//�����ֻ������ѯ�û�
	List<User> fineByPhone(String phone);

	//��ѯ���е�����
	List<User> findUsers();
	
	int getUsersCount();
	
	//����
	int countByExample(UserExample example);

	//�����û�
	int updateUser(User user);

	//ģ����ѯ
	List<User> findUsersLike(User user);

	//ɾ���û�
	int deleteUser(String number);

}
