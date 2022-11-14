package com.dining.diningreview.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dining.diningreview.model.AdminReview;
import com.dining.diningreview.model.DiningReview;
import com.dining.diningreview.model.Restaurant;
import com.dining.diningreview.model.ReviewStatus;
import com.dining.diningreview.repository.DiningReviewRepository;
import com.dining.diningreview.repository.RestaurantRepository;
import com.dining.diningreview.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public AdminController(DiningReviewRepository diningReviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public List<DiningReview> getReviewByStatus(@RequestParam String review_status) {

        ReviewStatus reviewStatus = ReviewStatus.PENDING;

        try {
            reviewStatus = ReviewStatus.valueOf(review_status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.diningReviewRepository.findReviewsByStatus(reviewStatus);
    } 

    @PutMapping("/reviews/{reviewId}")
    public void performReviewAction(@PathVariable Long reviewId, @RequestBody AdminReview adminReview) {

        Optional<DiningReview> optionalReview = this.diningReviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DiningReview diningReview = optionalReview.get();

        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(diningReview.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (adminReview.getAccept()) {
            diningReview.setStatus(ReviewStatus.ACCEPTED);
        } else {
            diningReview.setStatus(ReviewStatus.REJECTED);
        }

        this.diningReviewRepository.save(diningReview);
        updateRestaurantReviewScores(optionalRestaurant.get());
    }

    private void updateRestaurantReviewScores(Restaurant restaurant) {

        List<DiningReview> reviews = this.diningReviewRepository.findReviewsByRestaurantIdandStatus(restaurant.getId(), ReviewStatus.ACCEPTED);
        if (reviews.size() == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int peanutSum = 0;
        int peanutCount = 0;
        int dairySum = 0;
        int dairyCount = 0;
        int eggSum = 0;
        int eggCount = 0;

        for (DiningReview review: reviews) {
            if (!ObjectUtils.isEmpty(review.getPeanutScore())) {
                peanutSum += review.getPeanutScore();
                peanutCount++;
            }

            if (!ObjectUtils.isEmpty(review.getDairyScore())) {
                dairySum += review.getDairyScore();
                dairyCount++;
            }

            if (!ObjectUtils.isEmpty(review.getEggScore())) {
                eggSum += review.getEggScore();
                eggCount++;
            }
        }

        int totalCount = peanutCount + dairyCount + eggCount;
        int totalSum =  peanutSum + dairySum + eggSum;

        float overallScore = (float) totalSum / totalCount;
        restaurant.setOverallScore(decimalFormat.format(overallScore));

        if (peanutCount > 0) {
            float peanutScore = (float) peanutSum / peanutCount;
            restaurant.setPeanutScore(decimalFormat.format(peanutScore));
        }

        if (dairyCount > 0) {
            float dairyScore = (float) dairySum / dairyCount;
            restaurant.setDairyScore(decimalFormat.format(dairyScore));
        }

        if (eggCount > 0) {
            float eggScore = (float) eggSum / eggCount;
            restaurant.setEggScore(decimalFormat.format(eggScore));
        }

        this.restaurantRepository.save(restaurant);
    }
}
