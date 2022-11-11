package com.dining.diningreview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dining.diningreview.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    Optional<Restaurant> findRestaurantByNameAndZipcode(String name, String zipcode);
    List<Restaurant> findRestaurantByZipcodeAndPeanutScoreNotNullOrderByPeanutScore(String zipcode);
    List<Restaurant> findRestaurantByZipcodeAndDairyScoreNotNullOrderByDairyScore(String zipcode);
    List<Restaurant> findRestaurantByZipcodeAndEggScoreNotNullOrderByEggScore(String zipcode);
}
