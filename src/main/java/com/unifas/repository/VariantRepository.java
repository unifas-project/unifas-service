package com.unifas.repository;

import com.unifas.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VariantRepository  extends JpaRepository<Variant, Long> {
}
