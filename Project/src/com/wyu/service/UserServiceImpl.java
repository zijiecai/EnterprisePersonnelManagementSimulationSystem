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
	
	//添加用户
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return mapper.insert(user);
	}
	
	//通过手机号查询用户
	@Override
	public List<User> fineByPhone(String phone) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		
		return mapper.selectByExample(example);
	}

	//查询所有用户数据
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

	//更新用户
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(user);
	}

	//模糊查询
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

	//删除用户
	@Override
	public int deleteUser(String number) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(number);
	}
}
