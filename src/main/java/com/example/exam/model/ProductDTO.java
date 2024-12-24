package com.example.exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private Category category;
    private Region region;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.manufacturer = product.getManufacturer();
        this.category = product.getCategory();
        this.region = product.getRegion();
    }
}
