package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.unistart.entities.Mbtiresult;
import com.unistart.entities.University;

public interface MBTIResultRepository extends JpaRepository<Mbtiresult, Integer>{

	Mbtiresult findById(int id);
	
	@Query("select m from Mbtiresult m where m.users.id = ?1 order by m.testDate desc")
	List<Mbtiresult> getMBTIResult(int userId);
	
	@Modifying
	@Query("update Mbtiresult m set m.mbtitype.id = ?2 where m.users.id = ?1")
	void updateMBTIResult(int userId, int mbtiId);
}
