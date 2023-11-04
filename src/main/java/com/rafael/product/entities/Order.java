package com.rafael.product.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.rafael.product.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private OrderStatus status = OrderStatus.PROCESSING;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderItem> items = new ArrayList<>();	
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(
//	  name = "tb_order_product", 
//	  joinColumns = @JoinColumn(name = "order_id"), 
//	  inverseJoinColumns = @JoinColumn(name = "product_id")
//	  )
//	@JsonIgnoreProperties({"price", "product", "category"})
//	private List<Product> products = new ArrayList<>();

// INSERIR USER, DATE-TIME, 

	public Order() {}

	
	public Order(UUID id, List<OrderItem> items, OrderStatus status) {
		super();
		this.id = id;
		this.items = items;
		this.status = status;
	}
	
	public UUID getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
//	public void setStatus() {
//		status = OrderStatus.FINISHED;
//	}

	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", items=" + items + "]";
	}

	
	
	
	
	
}
