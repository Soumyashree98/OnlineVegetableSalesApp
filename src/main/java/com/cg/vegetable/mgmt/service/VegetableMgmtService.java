package com.cg.vegetable.mgmt.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cg.vegetable.mgmt.exceptions.ResourceNotFoundException;
import com.cg.vegetable.mgmt.model.VegetableDTO;
import com.cg.vegetable.mgmt.repository.VegetableMgmtRepository;

@Service
public class VegetableMgmtService {
	@Autowired
	private VegetableMgmtRepository vegRepo;
	

	public VegetableDTO addVegetable( VegetableDTO veg) 
	{
		return vegRepo.save(veg);		
	}
	
	public ResponseEntity<VegetableDTO> updateVegetable( int vegId,VegetableDTO vegDetails) throws ResourceNotFoundException 
	{
		VegetableDTO veg = vegRepo.findById(vegId).orElseThrow(() -> new ResourceNotFoundException("No Vegetable Found with this Id : " + vegId));
		veg.setName(vegDetails.getName());
		veg.setType(vegDetails.getType());
		veg.setCategory(vegDetails.getCategory());
		veg.setPrice(vegDetails.getPrice());
		veg.setQuantity(vegDetails.getQuantity());
		vegRepo.save(veg);
		return ResponseEntity.ok(veg);
	}
	
	public Map<String, Boolean> removeVegetable(int vegId) throws ResourceNotFoundException
	{
		VegetableDTO veg = vegRepo.findById(vegId)
				.orElseThrow(() -> new ResourceNotFoundException("No Vegetable Found with this Id : " + vegId));
		vegRepo.delete(veg);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	public List<VegetableDTO> viewVegetableList( String category)
	{
	return vegRepo.findByCategory(category);
	}
	
	public List<VegetableDTO> viewAllVegetable()
	{
		return vegRepo.findAll();
	}


}

