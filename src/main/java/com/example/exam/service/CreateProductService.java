package com.example.exam.service;

import com.example.exam.Command;
import com.example.exam.model.Category;
import com.example.exam.model.Product;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.CategoryRepo;
import com.example.exam.repo.ProductRepo;
import com.example.exam.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public CreateProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        ProductValidator.execute(product,categoryRepo.findAll());

        Product savedproduct = productRepo.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedproduct));
    }
}
