package com.unistart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	Provider findByProviderId(String providerId);
	
	
}
