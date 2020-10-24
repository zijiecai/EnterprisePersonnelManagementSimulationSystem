package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.UserMapper;
import com.wyu.pojo.User;
import com.wyu.pojo.UserExample;
import com.wyu.pojo.UserExample.Criteria;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	@Override
	public User login(String number) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(number);
	}
	
	//����û�
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return mapper.insert(user);
	}
	
	//ͨ���ֻ��Ų�ѯ�û�
	@Override
	public List<User> fineByPhone(String phone) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		
		return mapper.selectByExample(example);
	}

	//��ѯ�����û�����
	@Override
	public List<User> findUsers() {
		return mapper.selectByExample(new UserExample());
	}

	@Override
	public int getUsersCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByExample(UserExample example) {
		// TODO Auto-generated method stub
		return mapper.countByExample(example);
	}

	//�����û�
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(user);
	}

	//ģ����ѯ
	@Override
	public List<User> findUsersLike(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumberLike("%" + user.getNumber()+"%");
		criteria.andUsernameLike("%" + user.getUsername()+"%");
		criteria.andPhoneLike("%" + user.getPhone()+"%");
		if(user.getRoleId()!=null) {
			criteria.andRoleIdEqualTo(user.getRoleId());
		}
		
		example.or();

		return mapper.selectByExample(example);
	}

	//ɾ���û�
	@Override
	public int deleteUser(String number) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(number);
	}
}
