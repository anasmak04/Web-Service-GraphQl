package com.example.inventoryservice;

import com.example.inventoryservice.entities.Category;
import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.CategoryRepo;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                        CategoryRepo categoryRepo){
        return args -> {
            Random random = new Random();
            List.of("computer", "printer", "smarthphone").forEach(cat -> {
                Category category = Category.builder().name(cat).build();
                categoryRepo.save(category);
            });
          categoryRepo.findAll().forEach(category -> {
              for (int i = 0 ; i < 10 ; i++){
                  Product product = Product.builder()
                          .id(UUID.randomUUID().toString())
                          .name("computer "+ i)
                          .price(100 + Math.random() * 50000)
                          .quantity(new Random().nextInt(100))
                          .category(category)
                          .build();

                  productRepository.save(product);
              }
          });
        };
    }

}
