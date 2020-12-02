package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import net.guides.springboot2.springboot2jpacrudexample.model.Car;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllCars() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cars",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetCarById() {
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/1", Car.class);
		System.out.println(car.getBrand());
		assertNotNull(car);
	}

	@Test
	public void testCreateCar() {
		Car car = new Car();
		car.setColor("Red");
		car.setBrand("BMW");
		car.setYearOfProduction("2020");

		ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/cars", car, Car.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateCar() {
		int id = 1;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		car.setBrand("BMW");
		car.setYearOfProduction("2020");

		restTemplate.put(getRootUrl() + "/cars/" + id, car);

		Car updatedCar = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		assertNotNull(updatedCar);
	}

	@Test
	public void testDeleteCar() {
		int id = 2;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		assertNotNull(car);

		restTemplate.delete(getRootUrl() + "/cars/" + id);

		try {
			car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
