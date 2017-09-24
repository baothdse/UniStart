package com.unistart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.entities.Provider;
import com.unistart.repositories.ProviderRepository;
import com.unistart.services.interfaces.ProviderServiceInterface;

@Service
@Transactional
public class ProviderService implements ProviderServiceInterface {

	@Autowired
	private ProviderRepository providerRepo;
	
	@Override
	public Provider getById(int id) {
		// TODO Auto-generated method stub
		return providerRepo.findByProviderId(id);
	}

}
