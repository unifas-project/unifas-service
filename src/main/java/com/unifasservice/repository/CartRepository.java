package com.unifasservice.repository;

import com.unifasservice.entity.Cart;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartRepository  extends JpaRepository<Cart, Long> {
}
