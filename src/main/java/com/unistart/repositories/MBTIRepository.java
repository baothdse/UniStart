package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Mbtiquestion;

@Repository
public interface MBTIRepository extends JpaRepository<Mbtiquestion, Integer> {

	@Query("SELECT q FROM Mbtiquestion q")
	public List<Mbtiquestion> showByMBTIGroup();
}
