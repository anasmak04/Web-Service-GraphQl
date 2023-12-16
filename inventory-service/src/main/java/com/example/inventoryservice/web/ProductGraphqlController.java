package com.example.inventoryservice.web;


import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller

public class ProductGraphqlController {
    private ProductRepository productRepository;

    public ProductGraphqlController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryMapping
    public List<Product> productList(){
       return productRepository.findAll();
    }

    public Product productById(@PathVariable String id){
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("product %s not found",id)));
    }
}
