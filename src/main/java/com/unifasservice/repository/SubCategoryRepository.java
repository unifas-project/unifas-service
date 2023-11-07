package com.unifasservice.repository;

import com.unifasservice.entity.SubCategory;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SubCategoryRepository  extends JpaRepository<SubCategory, Long> {
}
