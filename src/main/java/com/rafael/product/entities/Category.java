package com.rafael.product.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@NotBlank(message = "It is necessary to inform the category!")
	private String category;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "category", cascade = CascadeType.PERSIST.ALL)
	@JsonIgnoreProperties("category") //Evita looping!
	private List<Product> products;

	public Category() {}

	public Category(UUID id, String category, List<Product> products) {
		super();
		this.id = id;
		this.category = category;
		this.products = products;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(category, other.category);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}
	
	
	
	
}
