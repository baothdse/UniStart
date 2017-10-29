package com.unistart.services.interfaces;

import java.util.List;

import com.unistart.entities.MajorMbti;
import com.unistart.entities.customentities.Correlate;
import com.unistart.entities.customentities.Pearson;

public interface CorrelateServiceInterface {
	public List<Pearson> count(int unversityId);
	public List<Pearson> getTop5Uni(List<Pearson> list);
	public List<Pearson> getTop5UniMBTI(List<MajorMbti> list);
	public List<Correlate> getTop20Uni(List<MajorMbti> listMajorId);
}
