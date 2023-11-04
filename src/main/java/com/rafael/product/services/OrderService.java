package com.rafael.product.services;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.entities.Order;
import com.rafael.product.entities.Product;
import com.rafael.product.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll() {
		List<Order> orders = orderRepository.findAll();
		return orders;
				
	}
	
	
	public Optional<Order> findById(UUID id){
		Optional<Order> order = orderRepository.findById(id);
		return order;
	}
	
	
	public Optional<Order> saveOrder(Order order) {

		if (orderRepository.findById(order.getId()).isPresent()) {
			return Optional.empty();
		}
		return Optional.of(orderRepository.save(order));					
		
	}

	
	public Optional<Order> updateOrder(Order order) {			
	    if(orderRepository.findById(order.getId()).isPresent()){
	        
	        return Optional.ofNullable(orderRepository.save(order));
	    }	    	   
	    return Optional.empty(); 
	}
		

	
	public void delete(UUID id) {

		if (orderRepository.findById(id).isPresent()) {
			orderRepository.deleteById(id);
		}
	}
}
