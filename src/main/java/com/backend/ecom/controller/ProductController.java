package com.backend.ecom.controller;


import com.backend.ecom.common.ApiResponse;
import com.backend.ecom.dto.ProductDto;
import com.backend.ecom.model.Category;
import com.backend.ecom.repository.CategoryRepo;
import com.backend.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepo categoryRepo;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(optionalCategory.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "category does not exist"),HttpStatus.NOT_ACCEPTABLE);
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(    true, "product created successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getProduts(){
         List<ProductDto> result =  productService.getProduts();
         return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable @RequestBody Integer id){
       try {
            ProductDto result = productService.getProductById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>( HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("/updateProduct/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable  Integer id ,@RequestBody ProductDto productDto) throws Exception {
        //update product logic
        // agar category id change kiya hua toh ::: first check for genuine category id
      try  {
            Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
            if (optionalCategory.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(false, "catergory not found "),HttpStatus.NOT_FOUND);
            }
          Category category = optionalCategory.get();
            productService.updateProductById(id, productDto,category);
            return new ResponseEntity<>(new ApiResponse(true, "product updated successfully"), HttpStatus.OK);
        }catch (Exception e){
          return new ResponseEntity<>(new ApiResponse(false, "error occurred while updating product"), HttpStatus.INTERNAL_SERVER_ERROR);

      }

    }



}
