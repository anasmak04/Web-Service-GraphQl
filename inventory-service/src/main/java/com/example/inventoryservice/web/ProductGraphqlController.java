package com.example.inventoryservice.web;


import com.example.inventoryservice.dto.ProductRequestDTO;
import com.example.inventoryservice.entities.Category;
import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.CategoryRepo;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller

public class ProductGraphqlController {
    private ProductRepository productRepository;
    private CategoryRepo categoryRepo;

    public ProductGraphqlController(ProductRepository productRepository , CategoryRepo categoryRepo) {
        this.productRepository = productRepository;
        this.categoryRepo = categoryRepo;
    }

    @QueryMapping
    public List<Product> productList(){
       return productRepository.findAll();
    }


    @QueryMapping
    public Product productById(@Argument String id) {
        return productRepository.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("Product %s not found ",id))
        );
    }

    @QueryMapping
    public List<Category> categoryList(){
        return categoryRepo.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Category %s not found",id)));
    }


    @MutationMapping
    public Product saveProduct(@Argument ProductRequestDTO product){
        Category category=categoryRepo.findById(product.categoryId()).orElse(null);
        Product productToSave=new Product();
        productToSave.setId(UUID.randomUUID().toString());
        productToSave.setName(product.name());
        productToSave.setPrice(product.price());
        productToSave.setQuantity(product.quantity());
        productToSave.setCategory(category);
        return productRepository.save(productToSave);
    }


    @MutationMapping
    public Product updateProduct(@Argument String id, @Argument ProductRequestDTO product){
        Category category=categoryRepo.findById(product.categoryId()).orElse(null);
        Product productToSave=new Product();
        productToSave.setId(id);
        productToSave.setName(product.name());
        productToSave.setPrice(product.price());
        productToSave.setQuantity(product.quantity());
        productToSave.setCategory(category);
        return productRepository.save(productToSave);
    }
    @MutationMapping
    public void deleteProduct(@Argument String id) {
        productRepository.deleteById(id);
    }



}
