package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Planter;

@Repository
public interface PlanterDao extends JpaRepository<Planter, Integer> {

}

