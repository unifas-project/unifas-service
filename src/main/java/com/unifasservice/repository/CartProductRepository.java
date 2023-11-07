package com.unifasservice.repository;

import com.unifasservice.entity.CartProduct;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartProductRepository  extends JpaRepository<CartProduct, Long> {
}
