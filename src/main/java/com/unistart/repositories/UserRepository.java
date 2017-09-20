package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Users;

@Repository
public interface UserRepository extends JpaRepository <Users, Integer> {
	
	Users findByUsername(String username);
	
	@Query("select new com.unistart.entities.Users(u.role, u.username, u.name, u.image, u.email, u.password) "
			+ "from Users u where u.username = ?1 and u.isActive = true")
	Users checkLogin(String username);
}
