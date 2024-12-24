package com.example.exam.validator;

import ch.qos.logback.core.util.StringUtil;
import com.example.exam.model.Category;
import com.example.exam.model.Product;
import org.springframework.util.StringUtils;

import java.util.List;

public class ProductValidator {

    public static void execute(Product product, List<Category> availableCategory){
        // Validate product name
        if (StringUtils.isEmpty(product.getName())) {
            throw new RuntimeException("Name cannot be null or empty");
        }

        // Validate product price
        if (product.getPrice() == null || product.getPrice() < 0.00) {
            throw new RuntimeException("Price cannot be negative or null");
        }

        // Validate product category
        if (product.getCategory() == null ||
                availableCategory.stream().noneMatch(category -> category.getName().equals(product.getCategory().getName()))) {
            throw new RuntimeException("Invalid category: The product's category is not in the list of available categories");
        }
    }
}
