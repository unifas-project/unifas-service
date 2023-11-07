package com.unifasservice.repository;

import com.unifasservice.entity.ImageProduct;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ImageProductRepository  extends JpaRepository<ImageProduct, Long> {
}
