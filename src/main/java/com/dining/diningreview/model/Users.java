package com.dining.diningreview.model;

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
public class Users {
    
    @Id
    @GeneratedValue
    private Long id;

    private String displayName;

    private String city;

    private String state;

    private String zipcode;

    private Boolean isAllergicToPeanuts;

    private Boolean isAllergicToEggs;

    private Boolean isAllergicToDairy;

}
