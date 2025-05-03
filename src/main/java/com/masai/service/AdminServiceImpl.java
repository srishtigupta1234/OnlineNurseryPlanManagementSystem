package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Errors.*;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customers;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seeds;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.OrderDao;
import com.masai.repository.PlantDao;
import com.masai.repository.PlanterDao;
import com.masai.repository.SeedDao;
import com.masai.repository.SessionDao;

@Service
public class AdminServiceImpl implements AdminServices{
	
	@Autowired
	private AdminDao aDao;
	
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
	public Planter registerPlanter(Planter planter, String key) throws LoginException, AdminException, PlanterException  {
		 CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to register planters");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can register new planters..");
		   }
		   if(planter == null) {
			   throw new PlanterException("No planter found");
		   }
		  Planter p = planterDao.save(planter);
		  return p;
		
	}

	@Override
	public Planter updatePlanter(Planter planter, String key) throws PlanterException, LoginException, AdminException {
	    // Session validation
	    CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    if (loggedInUser == null) {
	        throw new LoginException("Please provide a valid key to update planter");
	    }

	    // Admin check
	    if (!loggedInUser.getType().equalsIgnoreCase("Admin")) {
	        throw new AdminException("Only Admin can update planter details.");
	    }

	    // Ensure ID is provided
	    if (planter == null || planter.getPlanter_id() == null) {
	        throw new PlanterException("Planter ID must be provided for update");
	    }

	    // Fetch existing planter
	    Planter existing = planterDao.findById(planter.getPlanter_id())
	        .orElseThrow(() -> new PlanterException("No planter found with ID: " + planter.getPlanter_id()));

	    // Update only non-null fields
	    if (planter.getPlanter_height() != null) existing.setPlanter_height(planter.getPlanter_height());
	    if (planter.getCapacity() != null) existing.setCapacity(planter.getCapacity());
	    if (planter.getDrainage_holes() != null) existing.setDrainage_holes(planter.getDrainage_holes());
	    if (planter.getPlanter_color() != null) existing.setPlanter_color(planter.getPlanter_color());
	    if (planter.getPlanter_shape() != null) existing.setPlanter_shape(planter.getPlanter_shape());
	    if (planter.getPlanter_stock() != null) existing.setPlanter_stock(planter.getPlanter_stock());
	    if (planter.getPlanter_cost() != null) existing.setPlanter_cost(planter.getPlanter_cost());
	    if (planter.getAddress() != null) existing.setAddress(planter.getAddress());
	    if (planter.getPlants_list() != null) existing.setPlants_list(planter.getPlants_list());
	    if (planter.getSeeds_list() != null) existing.setSeeds_list(planter.getSeeds_list());

	    return planterDao.save(existing);
	}


	@Override
	public Planter deletePlanter(Integer id, String key) throws PlanterException, LoginException, AdminException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to delete planters");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can delete planters..");
		   }
		  Planter p = planterDao.getById(id);
		  if(p == null) {
			  throw new PlanterException("No planter found");  
		  }
		  planterDao.delete(p);
		  return p;
	}

	@Override
	public List<Planter> getPlanters(String key) throws PlanterException, LoginException, AdminException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of all planters");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can get all planters..");
		   }
		  List<Planter> p = planterDao.findAll();
		  if(p == null) {
			  throw new PlanterException("No planter found");  
		  }
		  return p;
	}

	@Override
	public Plant registerPlant(Plant plant, String key) throws PlantException, LoginException, AdminException  {
		 CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to register plant");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can register new plant..");
		   }
		   if(plant == null) {
			   throw new PlantException("No plant found");
		   }
		  Plant p = plantDao.save(plant);
		  return p;
	}

	@Override
	public Plant updatePlant(Plant plant, String key) throws PlantException, LoginException, AdminException {
	    // Validate session
	    CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    if (loggedInUser == null) {
	        throw new LoginException("Please provide a valid key to update plant");
	    }

	    // Check if user is Admin
	    if (!loggedInUser.getType().equalsIgnoreCase("Admin")) {
	        throw new AdminException("Only Admin can update plant details.");
	    }

	    // Ensure plant ID is provided
	    if (plant == null || plant.getPlant_id() == null) {
	        throw new PlantException("Plant ID must be provided for update");
	    }

	    // Fetch existing plant
	    Plant existingPlant = plantDao.findById(plant.getPlant_id())
	            .orElseThrow(() -> new PlantException("Plant not found with ID: " + plant.getPlant_id()));

	    // Update only non-null fields
	    if (plant.getPlant_height() != null) existingPlant.setPlant_height(plant.getPlant_height());
	    if (plant.getPlant_spread() != null) existingPlant.setPlant_spread(plant.getPlant_spread());
	    if (plant.getCommon_name() != null) existingPlant.setCommon_name(plant.getCommon_name());
	    if (plant.getBloom_time() != null) existingPlant.setBloom_time(plant.getBloom_time());
	    if (plant.getMedicinal_use() != null) existingPlant.setMedicinal_use(plant.getMedicinal_use());
	    if (plant.getDifficulty_level() != null) existingPlant.setDifficulty_level(plant.getDifficulty_level());
	    if (plant.getTemperature() != null) existingPlant.setTemperature(plant.getTemperature());
	    if (plant.getType_of_plant() != null) existingPlant.setType_of_plant(plant.getType_of_plant());
	    if (plant.getPlant_description() != null) existingPlant.setPlant_description(plant.getPlant_description());
	    if (plant.getPlant_stock() != null) existingPlant.setPlant_stock(plant.getPlant_stock());
	    if (plant.getPlant_cost() != null) existingPlant.setPlant_cost(plant.getPlant_cost());

	    // Save updated plant
	    return plantDao.save(existingPlant);
	}


	@Override
	public Plant deletePlant(Integer id, String key) throws PlantException, LoginException, AdminException  {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to delete plant");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can delete planters..");
		   }
		  Plant p = plantDao.getById(id);
		  if(p == null) {
			  throw new PlantException("No plant found");  
		  }
		  plantDao.delete(p);
		  return p;
	}

	@Override
	public List<Plant> getPlant(String key) throws PlantException, LoginException, AdminException  {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of plant");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can get all plants..");
		   }
		  List<Plant> p = plantDao.findAll();
		  if(p == null) {
			  throw new PlantException("No plant found");  
		  }
		  return p;
	}

	@Override
	public Seeds registerSeed(Seeds seed, String key) throws SeedException, LoginException, AdminException  {
		 CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to register seeds");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can register new seed..");
		   }
		   if(seed == null) {
			   throw new SeedException("No seed found");
		   }
		  Seeds p = seedDao.save(seed);
		  return p;
		
	}

	@Override
	public Seeds updateSeed(Seeds seed, String key) throws SeedException, LoginException, AdminException  {
	    // 1. Check if session key is valid
	    CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    if (loggedInUser == null) {
	        throw new LoginException("Please provide a valid key to update seed");
	    }

	    // 2. Check if user is Admin
	    if (!loggedInUser.getType().equalsIgnoreCase("Admin")) {
	        throw new AdminException("Only Admin can update seed details.");
	    }

	    // 3. Validate input
	    if (seed == null || seed.getSeed_id() == null) {
	        throw new SeedException("Seed ID must be provided for update");
	    }

	    // 4. Check if seed exists
	    Seeds existingSeed = seedDao.findById(seed.getSeed_id())
	            .orElseThrow(() -> new SeedException("Seed not found with ID: " + seed.getSeed_id()));

	    // 5. Update allowed fields
	    if (seed.getSeed_name() != null) existingSeed.setSeed_name(seed.getSeed_name());
	    if (seed.getBloom_time() != null) existingSeed.setBloom_time(seed.getBloom_time());
	    if(seed.getDifficulty_level() != null) existingSeed.setDifficulty_level(seed.getDifficulty_level());
	    if(seed.getPlanters() != null) existingSeed.setPlanters(seed.getPlanters());
	    if(seed.getSeeds_description() != null) existingSeed.setSeeds_description(seed.getSeeds_description());
	    if(seed.getSeeds_stock() != null) existingSeed.setSeeds_stock(seed.getSeeds_stock());
	    if(seed.getWatering() != null) existingSeed.setWatering(seed.getWatering());
	    if(seed.getType_of_seeds() != null) existingSeed.setType_of_seeds(seed.getType_of_seeds());
	    if(seed.getPrice() != null) existingSeed.setPrice(seed.getPrice());

	    // 6. Save and return
	    return seedDao.save(existingSeed);
	}


	@Override
	public Seeds deleteSeed(Integer id, String key) throws SeedException, LoginException, AdminException  {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to delete seed");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can delete seed..");
		   }
		  Seeds p = seedDao.getById(id);
		  if(p == null) {
			  throw new SeedException("No seed found");  
		  }
		  seedDao.delete(p);
		  return p;
	}

	@Override
	public List<Seeds> getSeeds(String key) throws LoginException, AdminException, SeedException {
	    CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    
	    if (loggedInUser == null) {
	        throw new LoginException("Please provide a valid key to get the list of seeds.");
	    }
	    
	    if (!loggedInUser.getType().equalsIgnoreCase("Admin")) {
	        throw new AdminException("Only Admin can access the list of seeds.");
	    }

	    List<Seeds> seeds = seedDao.findAll(); // ✅ Correct naming convention
	    
	    if (seeds == null || seeds.isEmpty()) {
	        throw new SeedException("No seeds found.");
	    }

	    return seeds;
	}


	@Override
	public List<Customers> getCustomers(String key) throws CustomerException, LoginException, AdminException  {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of customers");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can get all customers..");
		   }
		  List<Customers> p = customerDao.findAll();
		  if(p == null) {
			  throw new CustomerException("No customer found");  
		  }
		  return p;
	}

	@Override
	public List<Order> getOrder(String key) throws OrderException, LoginException, AdminException  {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of orders");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can get all orders..");
		   }
		  List<Order> p  = orderDao.findAll();
		  if(p == null) {
			  throw new OrderException("No orders found");  
		  }
		  return p;
	}

	@Override
	public Admin registerAdmin(Admin ad) throws AdminException {
		Admin admin = aDao.save(ad);
		if(ad==null) {
			throw new AdminException("No admin found");
		}
		return ad;
	}

	@Override
	public Customers deleteCustomer(Integer id, String key) throws CustomerException, LoginException, AdminException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		   if(loggedInUser == null)
			   throw new LoginException("Please provide a valid key to get list of customers");
		   if(!loggedInUser.getType().equalsIgnoreCase("Admin")) {
			   throw new AdminException("Only Admin can get all customers..");
		   }
		Customers p = customerDao.getById(id);
		  if(p == null) {
			  throw new CustomerException("No customer found");  
		  }
		  customerDao.delete(p);
		  return p;
	}


}
