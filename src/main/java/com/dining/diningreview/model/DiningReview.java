package com.dining.diningreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REVIEWS")
public class DiningReview {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="PEANUT_SCORE")
    private Integer peanutScore;

    @Column(name="EGG_SCORE")
    private Integer eggScore;

    @Column(name="DAIRY_SCORE")
    private Integer dairyScore;

    @Column(name="COMMENT")
    private String comment;


    public DiningReview() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPeanutScore() {
        return this.peanutScore;
    }

    public void setPeanutScore(Integer peanutScore) {
        this.peanutScore = peanutScore;
    }

    public Integer getEggScore() {
        return this.eggScore;
    }

    public void setEggScore(Integer eggScore) {
        this.eggScore = eggScore;
    }

    public Integer getDairyScore() {
        return this.dairyScore;
    }

    public void setDairyScore(Integer dairyScore) {
        this.dairyScore = dairyScore;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
