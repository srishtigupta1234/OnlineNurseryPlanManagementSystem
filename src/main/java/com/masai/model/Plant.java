package com.masai.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Integer plant_id;

    private Integer plant_height;
    private Integer plant_spread;
    private String common_name;
    private LocalTime bloom_time;
    private String medicinal_use;
    private String difficulty_level;
    private String temperature;
    private String type_of_plant;
    private String plant_description;
    private Integer plant_stock;
    private Integer plant_cost;

    // Planters this plant belongs to (optional backreference)
    @ManyToMany(mappedBy = "plants_list")
    @JsonIgnore
    private List<Planter> planters;

	
}

