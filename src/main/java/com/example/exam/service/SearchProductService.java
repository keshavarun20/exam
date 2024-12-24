package com.example.exam.service;

import com.example.exam.Query;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private final ProductRepo productRepo;

    public SearchProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {

        List<ProductDTO> productDTOList = productRepo.findNameOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList();


        return ResponseEntity.ok(productDTOList);
    }
}
