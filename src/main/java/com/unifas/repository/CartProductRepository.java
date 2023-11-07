package com.unifas.repository;

import com.unifas.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartProductRepository  extends JpaRepository<CartProduct, Long> {
}
