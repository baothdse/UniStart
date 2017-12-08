package com.unistart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unistart.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{
   Tag findById(int id);
   
   @Query("select t from Tag t where t.isActive = true")
   List<Tag> findAllTag();
   
   Tag findByTagName(String tagName);
}
