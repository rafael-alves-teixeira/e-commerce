package com.rafael.product.services;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.entities.Product;
import com.rafael.product.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		List<Product> products = productRepository.findAll();
		return products;
				
	}
	
	
	public Optional<Product> findById(UUID id){
		Optional<Product> product = productRepository.findById(id);
		return product;
	}
	
	
	public Optional<Product> saveProduct(Product product) {

		if (productRepository.findByProduct(product.getProduct()).isPresent()) {
			return Optional.empty();
		}
		return Optional.of(productRepository.save(product));
	}

	
	
	public SimpleEntry<Optional<Product>, Boolean> updateProduct(Product product) {
	    Optional<Product> productById = productRepository.findById(product.getId());
	    Optional<Product> productByName = productRepository.findByProduct(product.getProduct());

	    if (productById.isPresent()) {
	        if (productByName.isPresent() && !productById.get().getId().equals(productByName.get().getId())) {
	            return new SimpleEntry<>(Optional.empty(), true); // Indica um conflito
	        }
	        return new SimpleEntry<>(Optional.ofNullable(productRepository.save(product)), false);
	    }
	    return new SimpleEntry<>(Optional.empty(), false); // Indica que o produto não foi encontrado
	}

	
	public void deleteById(UUID id) {

		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		}
	}

}
