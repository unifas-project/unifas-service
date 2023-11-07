package com.unifasservice.repository;

import com.unifasservice.entity.OrderLine;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderLineRepository  extends JpaRepository<OrderLine, Long> {
}
