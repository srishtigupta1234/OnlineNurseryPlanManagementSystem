package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Planter;
import com.masai.model.Seeds;

@Repository
public interface SeedDao extends JpaRepository<Seeds, Integer> {

}
