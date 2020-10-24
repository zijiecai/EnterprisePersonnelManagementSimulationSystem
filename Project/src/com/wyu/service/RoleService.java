package com.wyu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wyu.pojo.Role;
import com.wyu.pojo.RoleExample;
import com.wyu.pojo.UploadfileExample;

@Service
public interface RoleService {
	public int addRole(Role role) ;
	public List<Role> selectRoleLike(Role role);
	public List<Role> findRoles();
	public int updateRole(Role role);
	public int deleteRole(Integer rid);
	public int countByExample(RoleExample example);
	public boolean selectRoleByName(Role role);
}