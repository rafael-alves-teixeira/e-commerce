package com.rafael.product.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.product.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	public Optional<Category> findByCategory(String category);

}
