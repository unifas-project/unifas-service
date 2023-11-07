package com.unifas.repository;

import com.unifas.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository  extends JpaRepository<OrderLine, Long> {
}
