package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Errors.*;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customers;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;
import com.masai.repository.CustomerDao;
import com.masai.repository.OrderDao;
import com.masai.repository.PlantDao;
import com.masai.repository.PlanterDao;
import com.masai.repository.SeedDao;
import com.masai.repository.SessionDao;

@Service
public class CustomerServicesImpl implements CustomerServices{
	
	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private PlanterDao planterDao; 
	
	@Autowired
	private PlantDao plantDao; 
	
	@Autowired
	private SeedDao seedDao; 
	
	@Autowired
	private OrderDao orderDao; 
	
	@Autowired
	private CustomerDao customerDao; 
	

	@Override
	public Customers createCustomer(Customers cs) throws CustomerException {
		 Customers c = customerDao.save(cs);
		 if(cs == null) {
			 throw new CustomerException("Please provide valid customer object");
		 }
		  return c;
	}

	@Override
	public Customers updateCustomer(Integer customerId, Customers updatedCustomer, String key) throws CustomerException, LoginException {
	    // Step 1: Validate session
	    CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    if (loggedInUser == null) {
	        throw new LoginException("Please provide a valid key to update customer");
	    }

	    // Step 2: Fetch the existing customer
	    Customers existingCustomer = customerDao.findById(customerId)
	            .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));

	    // Step 3: Update fields
	    if (updatedCustomer.getCustomer_name() != null)
	        existingCustomer.setCustomer_name(updatedCustomer.getCustomer_name());

	    if (updatedCustomer.getCustomer_email() != null)
	        existingCustomer.setCustomer_email(updatedCustomer.getCustomer_email());

	    if (updatedCustomer.getUsername() != null)
	        existingCustomer.setUsername(updatedCustomer.getUsername());

	    if (updatedCustomer.getCustomer_password() != null)
	        existingCustomer.setCustomer_password(updatedCustomer.getCustomer_password());

	    if (updatedCustomer.getCustomer_address() != null)
	        existingCustomer.setCustomer_address(updatedCustomer.getCustomer_address());

	    // Step 4: Save and return
	    return customerDao.save(existingCustomer);
	}


	@Override
	public List<Planter> getPlanters() throws PlanterException {
		 List<Planter> p = planterDao.findAll();
		  if(p == null) {
			  throw new PlanterException("No planter found");  
		  }
		  return p;
	}

	@Override
	public List<Plant> getPlants() throws PlantException {
		 List<Plant> p = plantDao.findAll();
		  if(p == null) {
			  throw new PlantException("No plant found");  
		  }
		  return p;
	}

	@Override
	public List<Seeds> getSeeds() throws SeedException {
		 List<Seeds> p = seedDao.findAll();
		  if(p == null) {
			  throw new SeedException("No seed found");  
		  }
		  return p;
	}

	@Override
	public Order placeOrder(String key, Order or) throws OrderException, LoginException {
		   CurrentUserSession loggedInUser = sDao.findByUuid(key);
		    if (loggedInUser == null) {
		        throw new LoginException("Please provide a valid key to place order");
		    }

		    // Validate order object before processing
		    if (or == null) {
		        throw new OrderException("Please provide a valid order object");
		    }

		    Order savedOrder = orderDao.save(or);

		    return savedOrder;
	}

	@Override
	public List<Order> getOrders(String key) throws OrderException, LoginException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of orders");
		  List<Order> p  = orderDao.findAll();
		  if(p == null) {
			  throw new OrderException("No orders found");  
		  }
		  return p;
	}

}
