package com.rafael.product.controllers;

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

import com.rafael.product.entities.Order;
import com.rafael.product.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> getAll(){
		return ResponseEntity.ok(orderService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getById(@PathVariable(value="id") UUID id){		
		return orderService.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());				
	}
	
	@PostMapping
	public ResponseEntity<Order> post(@Valid @RequestBody Order order) {
		return orderService.saveOrder(order)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
	}	        

	@PutMapping
	public ResponseEntity<Optional<Order>> updateOrder(@Valid @RequestBody Order order) {
		
		return orderService.findById(order.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(orderService.saveOrder(order)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	  
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  @DeleteMapping("/{id}") 
		  public void delete(@PathVariable(value = "id") UUID id) {
		  
		  Optional<Order> order = orderService.findById(id);	  
		  
		  if(order.isEmpty()) 
			  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		  
		  orderService.delete(id);	  		
	  }
	  
	  
		
}
