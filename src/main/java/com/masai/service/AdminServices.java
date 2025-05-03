package com.masai.service;

import java.util.List;

import com.masai.Errors.*;
import com.masai.model.Admin;
import com.masai.model.Customers;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;

public interface AdminServices {
	public Admin registerAdmin(Admin ad)throws AdminException;
	public Planter registerPlanter(Planter planter, String key) throws  PlanterException, LoginException, AdminException;
	public Planter updatePlanter(Planter planter, String key) throws PlanterException, LoginException, AdminException;
	public Planter deletePlanter(Integer id, String key) throws PlanterException, LoginException, AdminException;
	public List<Planter> getPlanters(String key) throws PlanterException, LoginException, AdminException;
	public Plant registerPlant(Plant plant, String key) throws PlantException, LoginException, AdminException ;
	public Plant updatePlant(Plant plant,String key) throws PlantException, LoginException, AdminException ;
	public Plant deletePlant(Integer id, String key) throws PlantException, LoginException, AdminException ;
	public List<Plant> getPlant(String key) throws PlantException, LoginException, AdminException ;
	public Seeds registerSeed(Seeds seed,String key) throws SeedException, LoginException, AdminException ;
	public Seeds updateSeed(Seeds seed, String key) throws SeedException, LoginException, AdminException ;
	public Seeds deleteSeed(Integer id,String key) throws SeedException, LoginException, AdminException ;
	public List<Seeds> getSeeds(String key) throws SeedException, LoginException, AdminException ;
	public List<Customers> getCustomers(String key) throws CustomerException,  LoginException, AdminException ;
	public Customers deleteCustomer(Integer id, String key) throws CustomerException, LoginException, AdminException;
	public List<Order> getOrder(String key) throws OrderException, LoginException, AdminException ;

}
