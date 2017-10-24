package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Users;

@Repository
public interface UserRepository extends JpaRepository <Users, Integer> {
	
	Users findByUsername(String username);
	Users findByEmail(String email);
	Users findById(int id);
	
	@Query("select new com.unistart.entities.Users(u.id, u.role, u.username, u.name, u.image, u.email, u.password) "
			+ "from Users u where u.username = ?1 and u.isActive = true")
	Users checkLogin(String username);

	@Modifying
	@Query("update Users u set u.name = ?1, u.email= ?2, u.password= ?3, u.image= ?4 where u.id = ?5 ")
	void updateProfile(String name, String email, String password, String image, int id);
}
