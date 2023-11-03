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

import com.rafael.product.entities.Category;
import com.rafael.product.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
	
	@Autowired
	private CategoryService categorytService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(categorytService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable(value="id") UUID id){		
		return categorytService.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());				
	}
	
	@PostMapping
	public ResponseEntity<Category> post(@Valid @RequestBody Category category) {
		return categorytService.saveCategory(category)
				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
				.orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());

	}

	        
	@PutMapping
	public ResponseEntity<Category> updateStudent(@Valid @RequestBody Category category) {
		SimpleEntry<Optional<Category>, Boolean> result = categorytService.updateCategory(category);
	    Optional<Category> updatedStudent = result.getKey();
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
	  Optional<Category> category= categorytService.findById(id);
	  
	  if(category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	  
	  categorytService.deleteById(id); }
	
}
