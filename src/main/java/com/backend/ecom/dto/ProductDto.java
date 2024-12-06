package com.backend.ecom.dto;


import com.backend.ecom.model.Product;
import jakarta.persistence.GeneratedValue;

public class ProductDto {


    private Integer id;
    private  String name;
    private  String imageURL;
    private  double price;
    private  String description;
    private  Integer categoryId;


    public ProductDto() {
    }

    public ProductDto(String name, String imageURL, double price, String description, Integer categoryId) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.imageURL = product.getImageURL();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categoryId = product.getCategory().getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
