package com.example.exam.service;

import com.example.exam.Query;
import com.example.exam.model.Product;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsService implements Query<Void, List<ProductDTO>> {

    private final ProductRepo productRepo;

    public GetAllProductsService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> productLists = productRepo.findAll();

        List<ProductDTO> productDTOS = productLists.stream().map(ProductDTO::new).toList();

        return ResponseEntity.ok(productDTOS);
    }
}
