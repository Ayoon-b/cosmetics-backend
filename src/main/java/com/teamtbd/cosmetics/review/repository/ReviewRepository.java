package com.teamtbd.cosmetics.review.repository;

import com.teamtbd.cosmetics.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
