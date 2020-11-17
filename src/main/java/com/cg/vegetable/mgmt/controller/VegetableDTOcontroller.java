package com.cg.vegetable.mgmt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.exceptions.ResourceNotFoundException;
import com.cg.vegetable.mgmt.model.VegetableDTO;
import com.cg.vegetable.mgmt.service.VegetableMgmtService;

@RestController
public class VegetableDTOcontroller {

	@Autowired
	private VegetableMgmtService vegMgmtservice;

	@PostMapping("/vegetables/newVegetable")
	public VegetableDTO addVegetable(@RequestBody VegetableDTO veg)
	{
		return vegMgmtservice.addVegetable(veg);			
	}

	@PutMapping("/vegetables/update/{id}")
	public ResponseEntity<VegetableDTO> updateVegetable(@PathVariable(value = "id") Integer vegId,
			@RequestBody VegetableDTO veg) throws ResourceNotFoundException 
	{ 
		
		return vegMgmtservice.updateVegetable(vegId, veg);
	}
	
	@DeleteMapping("/vegetables/removeVegetable/{id}")
	public Map<String, Boolean> removeVegetable(@PathVariable(value = "id") Integer vegId)
			throws ResourceNotFoundException 
	{
		
		return vegMgmtservice.removeVegetable(vegId);
	}
	@GetMapping("/vegetables/all")
	public List<VegetableDTO> viewAllVegetable() 
	{
		return vegMgmtservice.viewAllVegetable();
	}
	
	@GetMapping("/vegetables/{category}")
	public List<VegetableDTO> viewVegetableList(@PathVariable(value="category") String category) 
	{
		return vegMgmtservice.viewVegetableList(category);
	}
	
}