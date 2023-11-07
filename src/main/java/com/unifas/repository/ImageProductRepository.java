package com.unifas.repository;

import com.unifas.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageProductRepository  extends JpaRepository<ImageProduct, Long> {
}
