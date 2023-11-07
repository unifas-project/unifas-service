package com.unifas.repository;

import com.unifas.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository  extends JpaRepository<Review, Long> {
}
