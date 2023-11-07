package com.unifasservice.repository;

import com.unifasservice.entity.Product;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
