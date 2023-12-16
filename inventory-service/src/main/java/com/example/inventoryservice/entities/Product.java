package com.example.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor

public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne()
    private Category category;
}
