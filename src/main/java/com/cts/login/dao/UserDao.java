package com.cts.login.dao;

import java.util.List;

import com.cts.common.domain.Role;
import com.cts.common.domain.User;

public interface UserDao {
	
	public Role getUserRoles(String userName);
	public boolean isValidUser(String username,String password);

}
