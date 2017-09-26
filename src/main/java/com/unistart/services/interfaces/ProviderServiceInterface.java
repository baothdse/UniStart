package com.unistart.services.interfaces;

import com.unistart.entities.Provider;
import com.unistart.entities.Users;

public interface ProviderServiceInterface {
	Provider getById(String id);
	Provider addProvider(String providerId, String providerName, Users user);
}
