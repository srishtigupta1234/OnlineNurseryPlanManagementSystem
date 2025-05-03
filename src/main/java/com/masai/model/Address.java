package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Integer address_id; // ✅ This is correct

    private String house_no;
    private String colony;
    private String city;
    private String pincode;
    private String state;

    @OneToOne(mappedBy = "customer_address")
    private Customers customer;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Planter> planters;
}

