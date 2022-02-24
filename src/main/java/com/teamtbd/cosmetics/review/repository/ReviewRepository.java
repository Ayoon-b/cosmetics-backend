package com.teamtbd.cosmetics.review.repository;

import com.teamtbd.cosmetics.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
