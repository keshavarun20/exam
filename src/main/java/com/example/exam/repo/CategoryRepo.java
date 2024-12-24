package com.example.exam.repo;

import com.example.exam.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {
}
