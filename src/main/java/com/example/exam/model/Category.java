package com.example.exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "name")
    private String name;
}
