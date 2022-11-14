package com.dining.diningreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String state;

    private String zipcode;

    private String peanutScore; 

    private String eggScore; 

    private String dairyScore; 

    private String overallScore; 
}
