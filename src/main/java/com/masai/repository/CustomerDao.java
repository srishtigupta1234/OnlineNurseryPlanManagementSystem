package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customers;

@Repository
public interface CustomerDao extends JpaRepository<Customers,Integer> {
	public Customers findByUsername(String username);
}
