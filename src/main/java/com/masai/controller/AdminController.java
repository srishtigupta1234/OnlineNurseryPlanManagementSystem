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
import com.masai.model.Admin;
import com.masai.model.Customers;
import com.masai.model.LoginDTO;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;
import com.masai.service.AdminServices;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/OPNM/Admin")
public class AdminController {
	
	@Autowired
	private AdminServices aService;
	

	@Autowired
	private LoginService lService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin ad) throws AdminException{
		Admin admin = aService.registerAdmin(ad);
		return new ResponseEntity<>(admin, HttpStatus.CREATED);
	}
	
	@PostMapping("/planter/post/{key}")
	public ResponseEntity<Planter> registrationPlanterHandler (@RequestBody Planter cs, @PathVariable("key") String key) throws PlanterException, LoginException, AdminException {
		Planter c = aService.registerPlanter(cs, key);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/planter/update/{key}")
	public ResponseEntity<Planter> UpdationPlanterHandler( @RequestBody Planter cs, @PathVariable("key") String key) throws PlanterException, LoginException, AdminException {
		Planter c = aService.updatePlanter(cs, key);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	@GetMapping("/planters/{key}")
	public ResponseEntity<List<Planter>> GetListOfPlanters(@PathVariable("key") String key) throws PlanterException, LoginException, AdminException {
		List<Planter> c = aService.getPlanters(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/planter/delete/{id}/{key}")
	public ResponseEntity<Planter> DeletePlanter(@PathVariable("id") Integer id,@PathVariable("key") String key) throws PlanterException, LoginException, AdminException {
		Planter c = aService.deletePlanter(id, key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("/plant/post/{key}")
	public ResponseEntity<Plant> registrationPlantHandler (@RequestBody Plant cs, @PathVariable("key") String key) throws PlantException, LoginException, AdminException{
		Plant c = aService.registerPlant(cs, key);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/plant/update/{key}")
	public ResponseEntity<Plant> UpdationPlantHandler( @RequestBody Plant cs, @PathVariable("key") String key) throws PlantException, LoginException, AdminException{
		Plant c = aService.updatePlant(cs, key);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	@DeleteMapping("/plant/delete/{id}/{key}")
	public ResponseEntity<Plant> DeletePlant(@PathVariable("id") Integer id,@PathVariable("key") String key) throws PlantException, LoginException, AdminException{
		Plant c = aService.deletePlant(id, key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}

	
	@PostMapping("/seed/post/{key}")
	public ResponseEntity<Seeds> registrationSeedHandler (@RequestBody Seeds cs, @PathVariable("key") String key) throws SeedException, LoginException, AdminException{
		Seeds c = aService.registerSeed(cs, key);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/seed/update/{key}")
	public ResponseEntity<Seeds> UpdationSeedHandler( @RequestBody Seeds cs, @PathVariable("key") String key) throws SeedException, LoginException, AdminException{
		Seeds c = aService.updateSeed(cs, key);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	@DeleteMapping("/seed/delete/{id}/{key}")
	public ResponseEntity<Seeds> DeleteSeed(@PathVariable("id") Integer id,@PathVariable("key") String key) throws SeedException, LoginException, AdminException{
		Seeds c = aService.deleteSeed(id, key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/login")
	public ResponseEntity<String> LoginHandler( @RequestBody LoginDTO cs) throws LoginException{
		String c = lService.LogIn(cs);
		return new ResponseEntity<>(c, HttpStatus.FOUND);
	}
	
	@PutMapping("/logout/{key}/{type}")
	public ResponseEntity<String> LogoutHandler(@PathVariable("key") String key,@PathVariable("type") String type) throws LoginException{
		String c = lService.LogOut(key, type);
		return new ResponseEntity<>(c, HttpStatus.GONE);
	}

	
	@GetMapping("/plants/{key}")
	public ResponseEntity<List<Plant>> GetListOfPlants(@PathVariable("key") String key) throws PlantException, LoginException, AdminException {
		List<Plant> c = aService.getPlant(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customers/{key}")
	public ResponseEntity<List<Customers>> GetListOfCustomers(@PathVariable("key") String key) throws CustomerException, LoginException, AdminException{
		List<Customers> c = aService.getCustomers(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/customer/{id}/{key}")
	public ResponseEntity<String> DeleteCustomer(@PathVariable("id") Integer id,@PathVariable("key") String key) throws CustomerException, LoginException, AdminException {
		Customers c = aService.deleteCustomer(id,key);
		return new ResponseEntity<>("Customer deleted successfully",HttpStatus.ACCEPTED);
	}
	@GetMapping("/seeds/{key}")
	public ResponseEntity<List<Seeds>> GetListOfSeeds(@PathVariable("key") String key) throws SeedException, LoginException, AdminException {
		List<Seeds> c = aService.getSeeds(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/orders/{key}")
	public ResponseEntity<List<Order>> GetListOfOrder(@PathVariable("key") String key) throws OrderException, LoginException, AdminException {
		List<Order> c = aService.getOrder(key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	
}
