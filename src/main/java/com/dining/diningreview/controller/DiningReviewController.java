package com.dining.diningreview.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dining.diningreview.model.DiningReview;
import com.dining.diningreview.model.Restaurant;
import com.dining.diningreview.model.ReviewStatus;
import com.dining.diningreview.model.Users;
import com.dining.diningreview.repository.DiningReviewRepository;
import com.dining.diningreview.repository.RestaurantRepository;
import com.dining.diningreview.repository.UserRepository;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {

    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addUserReview(@RequestBody DiningReview review) {

        validatedUserReview(review);

        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(review.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        review.setStatus(ReviewStatus.PENDING);
        this.diningReviewRepository.save(review);
    }

    private void validatedUserReview(DiningReview review) {

        if (ObjectUtils.isEmpty(review.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(review.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(review.getPeanutScore()) && 
            ObjectUtils.isEmpty(review.getDairyScore()) && 
            ObjectUtils.isEmpty(review.getEggScore())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Users> optionalUser = this.userRepository.findByDisplayName(review.getName());
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } 
    } 

}
