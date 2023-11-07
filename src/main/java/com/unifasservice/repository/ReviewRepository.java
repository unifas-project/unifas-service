package com.unifasservice.repository;

import com.unifasservice.entity.Review;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReviewRepository  extends JpaRepository<Review, Long> {
}
