package com.exam.service.impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exam.entity.Users;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	//creating user
	@Override
	public Users createUser(Users user, Set<UserRole> userRoles) throws Exception {

		Users local = this.userRepository.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("user is already there!!");
			throw new Exception("User already present!!");
		} else {

			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}

		return local;
	}

	//getting user by User name
	@Override
	public Users getUser(String Username) {
		
		return this.userRepository.findByUsername(Username);
	}

	//delete user by id
	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
		
	}

}
