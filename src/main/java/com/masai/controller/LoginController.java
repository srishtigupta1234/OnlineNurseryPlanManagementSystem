package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.masai.Errors.CustomerException;
import com.masai.model.Customers;
import com.masai.service.CustomerServices;
import com.masai.service.LoginService;

public class LoginController {
	
	@Autowired
	private LoginService lService;
	
	@Autowired
	private CustomerServices cService;
//	
//	@PostMapping
//	public ResponseEntity<Customers> registrationHandler (@RequestBody Customers cs) throws CustomerException {
//		Customers c = cService.createCustomer(cs);
//		return new ResponseEntity<>(c, HttpStatus.CREATED);
//	}
	
	
}
