package com.example.exam.request;

import com.example.exam.model.Category;
import com.example.exam.model.Region;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class ProductRequest {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private String manufacturer;
    private Category category;
    private Region region;
}
