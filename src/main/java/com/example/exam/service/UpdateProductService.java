package com.example.exam.service;

import com.example.exam.Command;
import com.example.exam.UpdateCommand;
import com.example.exam.model.Product;
import com.example.exam.model.ProductDTO;
import com.example.exam.repo.CategoryRepo;
import com.example.exam.repo.ProductRepo;
import com.example.exam.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateCommand, ProductDTO> {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public UpdateProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateCommand command) {
        Optional<Product> optionalProduct =productRepo.findById(command.getId());

        if (optionalProduct.isPresent()){
            Product product =command.getProduct();
            product.setId(command.getId());
            ProductValidator.execute(product,categoryRepo.findAll());
            productRepo.save(product);

            return ResponseEntity.ok(new ProductDTO(product));
        }
        throw new RuntimeException("Product Nor Found");
    }
}
