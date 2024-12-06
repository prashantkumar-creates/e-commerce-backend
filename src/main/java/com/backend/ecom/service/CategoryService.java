package com.backend.ecom.service;


import com.backend.ecom.model.Category;
import com.backend.ecom.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepo categoryRepo;

    /*   remmebr from chagpt
    * You define an interface like CategoryRepo that extends JpaRepository.
    Spring Data JPA generates a proxy (implementation class) at runtime that implements the CategoryRepo interface.
    When Spring sees @Autowired CategoryRepo, it injects an instance of this proxy class, which contains all the logic for
*    interacting with the database (provided by Spring Data JPA).
    * */


    public void createCategory(Category category){
        categoryRepo.save(category);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    public void editCategory(Integer categoryId, Category updateCategory) {
        Category category = categoryRepo.getReferenceById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepo.save(category);

    }


    public boolean findById(Integer categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }


}
