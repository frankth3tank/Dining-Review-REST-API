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
@Table(name="USERS")
public class Users {
    
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name="DISPLAY_NAME")
    private String displayName;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private String zipcode;

    @Column(name="IS_ALLERGIC_TO_PEANUTS")
    private Boolean isAllergicToPeanuts;

    @Column(name="IS_ALLERGIC_TO_EGGS")
    private Boolean isAllergicToEggs;

    @Column(name="IS_ALLERGIC_TO_DAIRY")
    private Boolean isAllergicToDairy;

}
