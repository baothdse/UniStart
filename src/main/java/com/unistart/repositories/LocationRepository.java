package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
	Location findById(int id);
	
	@Query("select new com.unistart.entities.Location(l.id, l.locationName, l.locationCode) "
			+ "from Location l")
	List<Location> findByLocationName();
}
