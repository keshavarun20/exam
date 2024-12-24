package com.example.exam.service;

import com.example.exam.Command;
import com.example.exam.model.Product;
import com.example.exam.repo.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<String, Void> {

    private final ProductRepo productRepo;

    public DeleteProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()){
            productRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new RuntimeException("Product Not Found");

    }
}
