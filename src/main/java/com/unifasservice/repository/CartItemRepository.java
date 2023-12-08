package com.unifasservice.repository;

import com.unifasservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductIdAndVariantId(long productId, long variantId);
}
