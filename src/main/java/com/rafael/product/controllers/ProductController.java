package com.rafael.product.controllers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.product.entities.Product;
import com.rafael.product.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable(value="id") UUID id){		
		return productService.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());				
	}
	
	@PostMapping
	public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
		return productService.saveProduct(product)
				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
				.orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());

	}

	        
	@PutMapping
	public ResponseEntity<Product> updateStudent(@Valid @RequestBody Product product) {
		SimpleEntry<Optional<Product>, Boolean> result = productService.updateProduct(product);
	    Optional<Product> updatedStudent = result.getKey();
	    boolean isConflict = result.getValue();

	    if (updatedStudent.isPresent()) {
	        return ResponseEntity.ok(updatedStudent.get());
	    } else if (isConflict) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	    	    	
	
	  
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  @DeleteMapping("/{id}") public void delete(@PathVariable(value = "id") UUID id) {
	  Optional<Product> product= productService.findById(id);
	  
	  if(product.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	  
	  productService.deleteById(id);
	  
	  }
	
}
