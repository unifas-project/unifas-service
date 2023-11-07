package com.unifasservice.repository;

import com.unifasservice.entity.Category;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository  extends JpaRepository<Category, Long> {

}