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
public class DiningReview {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long restaurantId;

    private Integer peanutScore;

    private Integer eggScore;

    private Integer dairyScore;

    private String review;

    private ReviewStatus status;

}
