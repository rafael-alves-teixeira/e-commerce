package com.rafael.product.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_orderItem")
public class OrderItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToOne
	private Order order;
	
	@OneToOne
	private Product product;	
	private Integer quantity;
	
	
	
	public OrderItem() {		
	}



	public OrderItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}

	
	
}
