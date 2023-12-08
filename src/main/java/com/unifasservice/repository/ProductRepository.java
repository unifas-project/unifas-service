package com.unifasservice.repository;

import com.unifasservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameLike(String name);

    List<Product> findProductByCategoryId(long categoryId);

    List<Product> findProductBySubCategoryId(long subCategoryId);
}
