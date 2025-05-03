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
public class Seeds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Integer seed_id;

    private String seed_name;
    private LocalTime bloom_time;
    private String watering;
    private String difficulty_level;
    private String temperature;
    private String type_of_seeds;
    private String seeds_description;
    private Integer seeds_stock;
    private Boolean seeds_powerpack;
    private Integer price;

    @ManyToMany(mappedBy = "seeds_list")
	@JsonIgnore
    private List<Planter> planters;

}
