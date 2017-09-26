package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Provider;
import com.unistart.entities.Users;
import com.unistart.repositories.ProviderRepository;
import com.unistart.services.interfaces.ProviderServiceInterface;

@Service
@Transactional
public class ProviderService implements ProviderServiceInterface {

	@Autowired
	private ProviderRepository providerRepo;
	
	@Override
	public Provider getById(String id) {
		// TODO Auto-generated method stub
		return providerRepo.findByProviderId(id);
	}

	@Override
	public Provider addProvider(String providerId, String providerName, Users user) {
		// TODO Auto-generated method stub
		Provider provider = new Provider();
		if (getById(providerId) == null) {
			
			provider.setProviderId(providerId);
			provider.setProviderName(providerName);
			provider.setUsers(user);
			providerRepo.save(provider);
		}
		return provider;
	}

}
