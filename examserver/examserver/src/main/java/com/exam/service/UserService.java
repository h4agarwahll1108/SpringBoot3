package com.exam.service;

import java.util.Set;
import com.exam.entity.Users;
import com.exam.entity.UserRole;

public interface UserService {

	// creating user
	public Users createUser(Users user, Set<UserRole> userRoles) throws Exception;
	
	//get user
	public Users getUser(String Username);
	
	//delete user
	public void deleteUser(Long userId);

}
