package com.example.exam;

import com.example.exam.model.Product;
import lombok.Data;

@Data
public class UpdateCommand {

    private String Id;
    private Product product;

    public UpdateCommand(String id, Product product) {
        Id = id;
        this.product = product;
    }
}
