package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Errors.*;
import com.masai.model.Customers;
import com.masai.model.LoginDTO;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;
import com.masai.service.CustomerServices;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/OPNM/Customer")
public class CustomerController {
	

	@Autowired
	private CustomerServices cService;
	
	@Autowired
	private LoginService lService;
	
	
	@PostMapping
	public ResponseEntity<String> registrationHandler (@RequestBody Customers cs) throws CustomerException {
		Customers c = cService.createCustomer(cs);
		return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
	}

	@PostMapping("/order/{key}")
	public ResponseEntity<Order> placeOrderHandler (@RequestBody Order or, @PathVariable("key") String key) throws OrderException, LoginException{
		Order c = cService.placeOrder(key, or);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<String> UpdationHandler(Integer id, @RequestBody Customers cs, @PathVariable("key") String key) throws CustomerException, LoginException {
		Customers c = cService.updateCustomer(id,cs, key);
		return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
	}
	
	@PutMapping("/login")
	public ResponseEntity<String> LoginHandler( @RequestBody LoginDTO cs) throws LoginException{
		String c = lService.LogIn(cs);
		return new ResponseEntity<>(c, HttpStatus.FOUND);
	}
	
	@PutMapping("/logout/{key}/{type}")
	public ResponseEntity<String> LogoutHandler(@PathVariable("key") String key,@PathVariable("type") String type) throws LoginException {
		String c = lService.LogOut(key, type);
		return new ResponseEntity<>(c, HttpStatus.GONE);
	}
	
	
	@GetMapping("/plants")
	public ResponseEntity<List<Plant>> GetListOfPlants() throws PlantException {
		List<Plant> c = cService.getPlants();
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> GetListOfPlanters() throws PlanterException {
		List<Planter> c = cService.getPlanters();
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
 
	@GetMapping("/seeds")
	public ResponseEntity<List<Seeds>> GetListOfSeeds() throws SeedException {
		List<Seeds> c = cService.getSeeds();
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/orders/{key}")
	public ResponseEntity<List<Order>> GetListOfOrder(@PathVariable("key") String key) throws OrderException, LoginException {
		List<Order> c = cService.getOrders(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}


}
