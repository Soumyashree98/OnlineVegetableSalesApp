package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.vegetable.mgmt.model.VegetableDTO;

@Repository
public interface VegetableMgmtRepository extends JpaRepository<VegetableDTO,Integer> {
	
	@Query(value = "SELECT * FROM VEGETABLEDTO WHERE CATEGORY = ?", nativeQuery = true)
	  List<VegetableDTO> findByCategory(String category);
	
    @Query(value = "SELECT * FROM VEGETABLEDTO WHERE NAME = ?", nativeQuery = true)
	List<VegetableDTO> findByName(String name);
	
	
}
