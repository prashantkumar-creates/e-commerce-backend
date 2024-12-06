package com.backend.ecom.service;

import com.backend.ecom.dto.ProductDto;

import com.backend.ecom.model.Category;
import com.backend.ecom.model.Product;
import com.backend.ecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto , Category category) {

        Product product = getProductFromDto(productDto, category);
        productRepo.save(product);
    }

    public  Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }

    public List<ProductDto> getProduts() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    public ProductDto getProductById(Integer id) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            return new ProductDto(productOptional.get());
        } else {
            throw new Exception("Product not found for id :: " + id);
        }
    }

    public void updateProductById(Integer id, ProductDto productDto,Category category)throws Exception {

        Product product = getProductFromDto(productDto,category);
        product.setId(id);
        productRepo.save(product);

    }
}
