package com.example.exam.repo;

import com.example.exam.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findNameOrDescriptionContaining(@Param("keyword") String name);

    @Query("SELECT p FROM Product p WHERE p.category.name LIKE %:keyword%")
    List<Product> findByCategoryContaining(@Param("keyword") String name);

    @Query("SELECT p FROM Product p ORDER BY " +
            "CASE WHEN :sortBy = 'price' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'name' THEN p.name END ASC")
    List<Product> findProductsSorted(@Param("sortBy") String sortBy);
}
