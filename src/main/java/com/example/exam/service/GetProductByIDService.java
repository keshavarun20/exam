package com.example.exam.service;

import com.example.exam.Query;
import com.example.exam.model.Product;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductByIDService implements Query<String, ProductDTO> {

    private final ProductRepo productRepo;

    public GetProductByIDService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(String id) {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()){

            return ResponseEntity.ok(new ProductDTO(optionalProduct.get()));
        }

        throw new RuntimeException("Product Not Found");

    }
}
