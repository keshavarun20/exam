package com.example.exam.controller;

import com.example.exam.UpdateCommand;
import com.example.exam.model.Product;
import com.example.exam.model.ProductDTO;
import com.example.exam.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    private final CreateProductService createProductService;

    private final GetAllProductsService getAllProductsService;

    private final GetProductByIDService getProductByIDService;

    private final DeleteProductService deleteProductService;

    private final UpdateProductService updateProductService;

    private final SearchProductService searchProductService;

    private final SortProductService sortProductService;

    private final SearchByCategory searchByCategory;

    public ProductController(CreateProductService createProductService,
                             GetAllProductsService getAllProductsService,
                             GetProductByIDService getProductByIDService,
                             DeleteProductService deleteProductService,
                             UpdateProductService updateProductService,
                             SearchProductService searchProductService,
                             SortProductService sortProductService,
                             SearchByCategory searchByCategory) {
        this.createProductService = createProductService;
        this.getAllProductsService = getAllProductsService;
        this.getProductByIDService = getProductByIDService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.searchProductService = searchProductService;
        this.sortProductService = sortProductService;
        this.searchByCategory = searchByCategory;
    }

    @PostMapping("/product/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return getAllProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return getProductByIDService.execute(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        return deleteProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody Product product){
        return updateProductService.execute(new UpdateCommand(id, product));
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByNameOrDescription(@RequestParam String name){
        return searchProductService.execute(name);
    }

    @GetMapping("/product/category")
    public ResponseEntity<List<ProductDTO>> searchProductByCategory(@RequestParam String name){
        return searchByCategory.execute(name);
    }

    @GetMapping("/product/sort")
    public ResponseEntity<List<ProductDTO>> sortBy(@RequestParam String sortBy){
        return sortProductService.execute(sortBy);
    }
}
