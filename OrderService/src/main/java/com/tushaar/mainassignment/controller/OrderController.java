package com.tushaar.mainassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO order) {
		return service.createOrder(order);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable Long id) {
		return service.getOrderById(id);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getAllOrdersForUser(@PathVariable Long id) {
		return service.getAllOrdersForUser(id);
	}
}
