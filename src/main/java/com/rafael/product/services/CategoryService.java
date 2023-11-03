package com.rafael.product.services;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.entities.Category;
import com.rafael.product.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
				
	}
	
	
	public Optional<Category> findById(UUID id){
		Optional<Category> category = categoryRepository.findById(id);
		return category;
	}
	
	
	public Optional<Category> saveCategory(Category category) {

		if (categoryRepository.findByName(category.getName()).isPresent()) {
			return Optional.empty();
		}
		return Optional.of(categoryRepository.save(category));
	}

	
	
	public SimpleEntry<Optional<Category>, Boolean> updateCategory(Category category) {
	    Optional<Category> categoryById = categoryRepository.findById(category.getId());
	    Optional<Category> categoryByName = categoryRepository.findByName(category.getName());

	    if (categoryById.isPresent()) {
	        if (categoryByName.isPresent() && !categoryById.get().getId().equals(categoryByName.get().getId())) {
	            return new SimpleEntry<>(Optional.empty(), true); // Indica um conflito
	        }
	        return new SimpleEntry<>(Optional.ofNullable(categoryRepository.save(category)), false);
	    }
	    return new SimpleEntry<>(Optional.empty(), false); // Indica que a categoria não foi encontrado
	}

	
	
	public void deleteById(UUID id) {

		if (categoryRepository.findById(id).isPresent()) {
			categoryRepository.deleteById(id);
		}
	}


}
