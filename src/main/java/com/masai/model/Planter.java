package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Planter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Integer planter_id;

    private Integer planter_height;
    private Integer capacity;
    private String drainage_holes;
    private String planter_color;
    private String planter_shape;
    private Integer planter_stock;
    private Double planter_cost;

    // 🔁 Many-to-Many with Plant
    @ManyToMany
	@JsonIgnore
    private List<Plant> plants_list;

    // 🔁 Many-to-Many with Seeds
    @ManyToMany
	@JsonIgnore
    private List<Seeds> seeds_list;

    // 🔁 Many-to-One with Address
    @ManyToOne
	@JsonIgnore
    private Address address;
    
    
    
    
}
