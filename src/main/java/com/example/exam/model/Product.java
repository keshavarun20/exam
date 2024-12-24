package com.example.exam.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name= "price")
    private Double price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Region region;

    @CreationTimestamp
    @Column(name="created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updatedAt;
}
