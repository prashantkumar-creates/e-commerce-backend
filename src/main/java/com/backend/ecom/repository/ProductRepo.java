package com.backend.ecom.repository;

import com.backend.ecom.model.Category;
import com.backend.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Additional custom methods for Product repository
//    List<Product> findByCategory(Category category);
}
