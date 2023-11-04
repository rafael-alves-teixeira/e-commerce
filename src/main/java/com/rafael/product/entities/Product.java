package com.rafael.product.entities;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@NotBlank(message = "It is necessary to inform the product name!")	
	private String product;
	
	private int quantity;
	@NotNull(message = "Please, inform the price!")
	
	private double price;

	@NotBlank(message = "It is necessary to inform the product name!")
	@ManyToOne
	@JsonIgnoreProperties("product") //Evita looping!
	private Category category;
	
	public Product() {}
	
	public Product(UUID id, String product, int quantity, double price, Category category) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
	}	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	

	public String getProduct() {
		return product;
	}

	public void setProduct(String name) {
		this.product = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, product);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", product=" + product + ", quantity=" + quantity + ", price=" + price + ", category=" + category + "]";
	}
	
	
	
	
	
}
