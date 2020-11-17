package com.cg.vegetable.mgmt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.vegetable.mgmt.model.VegetableDTO;

@SpringBootTest(classes=OnlineVegetableSalesAppApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VegetableMgmtControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl(){
		return "http://localhost:"+port;
	}
	@Test
	public void testGetAllVegetables() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl() + "/vegetables/all",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testaddVegetable() {
		VegetableDTO veg= new VegetableDTO();
		veg.setName("Raddish");
		veg.setType("Fresh Stock");
		veg.setCategory("Stock");
		veg.setPrice(50.00);
		veg.setQuantity(5);
		ResponseEntity<VegetableDTO> postResponse = testRestTemplate.postForEntity(getRootUrl() + "/vegetables/newVegetables/",veg, VegetableDTO.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	  @Test
	  void testAddVegetablePositive() {
		  VegetableDTO veg= new VegetableDTO();
		  veg.setName("Brinjal");
			veg.setType("Fresh Stock");
			veg.setCategory("Stock");
			veg.setPrice(50.00);
			veg.setQuantity(5);
			ResponseEntity<VegetableDTO> postResponse = testRestTemplate
					.postForEntity(getRootUrl() + "/vegetables/newVegetables/",veg, VegetableDTO.class);
			assertNotNull(postResponse);
			assertNotNull(postResponse.getBody());
	  }
	  @Test
	  void testAddOrderNegative() {
		  VegetableDTO veg= new VegetableDTO();
		  veg.setName("Brinjal");
			veg.setType("Fresh Stock");
			veg.setCategory("Stock");
			veg.setPrice(50.00);
			veg.setQuantity(5);
			ResponseEntity<VegetableDTO> postResponse = testRestTemplate
					.postForEntity(getRootUrl() + "/vegetables/newVegetables/",veg, VegetableDTO.class);
			assertNotNull(postResponse);
			assertNotNull(postResponse.getBody());
	  }
	@Test
	public void testUpdateVegetable() {
		int vegid = 1;
		VegetableDTO veg = testRestTemplate.getForObject(getRootUrl() + "/vegetables/getVegetableDTO/" + vegid, VegetableDTO.class);
		veg.setName("Bottle Gourd");
		veg.setType("Fresh Stock");
		veg.setCategory("Stock");
		veg.setPrice(50.00);
		veg.setQuantity(5);
		testRestTemplate.put(getRootUrl() + "/vegetables/update" + vegid, veg);
		VegetableDTO updatedVegetable = testRestTemplate.getForObject(getRootUrl() + "/vegetables/getVegetableDTO/" + vegid, VegetableDTO.class);
		assertNotNull(updatedVegetable);
	}

	@Test
	public void testDeleteVegetable() {

		int vegid = 2;
		VegetableDTO veg = testRestTemplate.getForObject(getRootUrl() + "/vegetables/getVegetableDTO/" + vegid, VegetableDTO.class);
		assertNotNull(veg);
		testRestTemplate.delete(getRootUrl() + "/vegetables/removeVegetable/" + vegid);
		try {
			veg= testRestTemplate.getForObject(getRootUrl() + "/vegetables/getVegetableDTO/" + vegid, VegetableDTO.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}


	@Test
	public void testGetVegetableByCategory() {
		VegetableDTO veg  = testRestTemplate.getForObject(getRootUrl() + "/vegetables/getVegetableDTO/Stock", VegetableDTO.class);
		System.out.println(veg.getCategory());
		assertNotNull(veg);
	}


}

