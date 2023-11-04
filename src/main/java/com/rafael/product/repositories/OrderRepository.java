package com.rafael.product.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.product.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{
	
}
