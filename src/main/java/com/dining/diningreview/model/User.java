package com.dining.diningreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="DISPLAY_NAME")
    private String displayName;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private Integer zipcode;

    @Column(name="IS_ALERGIC_TO_PEANUTS")
    private Boolean isAllergicToPeanuts;

    @Column(name="IS_ALERGIC_TO_EGGS")
    private Boolean isAllergicToEggs;

    @Column(name="IS_ALERGIC_TO_DAIRY")
    private Boolean isAllergicToDairy;


    public User() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Boolean isIsAllergicToPeanuts() {
        return this.isAllergicToPeanuts;
    }

    public Boolean getIsAllergicToPeanuts() {
        return this.isAllergicToPeanuts;
    }

    public void setIsAllergicToPeanuts(Boolean isAllergicToPeanuts) {
        this.isAllergicToPeanuts = isAllergicToPeanuts;
    }

    public Boolean isIsAllergicToEggs() {
        return this.isAllergicToEggs;
    }

    public Boolean getIsAllergicToEggs() {
        return this.isAllergicToEggs;
    }

    public void setIsAllergicToEggs(Boolean isAllergicToEggs) {
        this.isAllergicToEggs = isAllergicToEggs;
    }

    public Boolean isIsAllergicToDairy() {
        return this.isAllergicToDairy;
    }

    public Boolean getIsAllergicToDairy() {
        return this.isAllergicToDairy;
    }

    public void setIsAllergicToDairy(Boolean isAllergicToDairy) {
        this.isAllergicToDairy = isAllergicToDairy;
    }

}
