package com.masai.service;

import java.util.List;

import com.masai.Errors.*;
import com.masai.model.Customers;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;

public interface CustomerServices {
	public Customers createCustomer(Customers cs) throws CustomerException;
	public Customers updateCustomer(Integer id, Customers cs, String key) throws CustomerException,LoginException;
	public List<Planter> getPlanters() throws PlanterException;
	public List<Plant> getPlants() throws PlantException;
	public List<Seeds> getSeeds() throws SeedException;
	public Order placeOrder(String key,Order or) throws OrderException,LoginException;
	public List<Order> getOrders(String key) throws OrderException,LoginException;
	
}
