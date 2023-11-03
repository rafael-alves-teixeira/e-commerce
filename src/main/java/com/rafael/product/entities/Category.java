package com.rafael.product.entities;

import java.util.Arrays;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@NotBlank(message = "It is necessary to inform the category!")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, mappedBy = "ProductName", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("curso") //Evita looping!
	private List<Product> products;

	public Category() {}

	public Category(UUID id, String name, Product[] products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + Arrays.toString(products) + "]";
	}
	
	
	
	
}
