package com.dining.diningreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dining.diningreview.model.DiningReview;
import com.dining.diningreview.model.ReviewStatus;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findReviewsByRestaurantIdandStatus(Long restaurantId, ReviewStatus reviewStatus);
    List<DiningReview> findReviewsByStatus(ReviewStatus reviewStatus);
}
