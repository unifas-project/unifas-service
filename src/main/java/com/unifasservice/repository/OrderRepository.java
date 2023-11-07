package com.unifasservice.repository;

import com.unifasservice.entity.Order;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
