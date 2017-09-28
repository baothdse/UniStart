package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unistart.entities.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{
	
	Role findById(int roleId);
}
