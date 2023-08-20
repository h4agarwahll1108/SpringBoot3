package com.exam.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);
	

}
