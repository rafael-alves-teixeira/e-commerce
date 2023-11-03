package com.rafael.product.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{
	
	public Optional<Product> findByName(String name);

}
