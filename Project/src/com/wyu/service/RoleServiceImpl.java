package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.RoleMapper;
import com.wyu.pojo.Role;
import com.wyu.pojo.RoleExample;
import com.wyu.pojo.RoleExample.Criteria;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper mapper;
	@Override
	public int addRole(Role role)
	{
		//Èô½ÇÉ«ridÒÑ´æÔÚ
		if(mapper.selectByPrimaryKey( role.getRid() ) !=null) {
			return 0;
		}
		else {
			return mapper.insert(role);
		}
	}
	
	@Override
	public List<Role> selectRoleLike(Role role) {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		Integer rid=role.getRid();
		String rname = role.getRname();
		if(rid!=null) {
			criteria.andRidEqualTo(rid);
		}
		criteria.andRnameLike("%"+rname+"%");
		List<Role> roleList =mapper.selectByExample(example);
		return roleList;
	}
	@Override
	public boolean selectRoleByName(Role role){
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		String rname = role.getRname();
		criteria.andRnameLike(rname);
		List<Role> roleList =mapper.selectByExample(example);
		if(roleList==null||roleList.size()==0) {
			return false;
		}
		return true;
	}
	@Override
	public List<Role> findRoles(){
		return mapper.selectByExample(new RoleExample());
	}
	@Override
	public int updateRole(Role role) {
		return mapper.updateByPrimaryKey(role);
	}
	@Override
	public int deleteRole(Integer rid) {
		return mapper.deleteByPrimaryKey(rid);
	}
	@Override
	public int countByExample(RoleExample example) {
		return mapper.countByExample(example);
	}

}
