package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.customentities.Correlate;

public interface CorrelateServiceInterface {
	public List<Correlate> getTop10Uni(int unversityId);
}
