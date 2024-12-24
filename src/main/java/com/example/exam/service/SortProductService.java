package com.example.exam.service;

import com.example.exam.Query;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortProductService implements Query<String, List<ProductDTO>> {

    private final ProductRepo productRepo;

    public SortProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String sortBy) {
        return ResponseEntity.ok(productRepo.findProductsSorted(sortBy)
                .stream()
                .map(ProductDTO::new)
                .toList());
    }
}
