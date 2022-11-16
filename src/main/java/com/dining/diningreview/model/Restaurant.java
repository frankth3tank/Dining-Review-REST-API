package com.dining.diningreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="RESTAURANTS")
public class Restaurant {
    
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private String zipcode;

    @Column(name="PEANUT_SCORE")
    private String peanutScore; 

    @Column(name="EGG_SCORE")
    private String eggScore; 

    @Column(name="DAIRY_SCORE")
    private String dairyScore; 

    @Column(name="OVERALL_SCORE")
    private String overallScore; 
}
