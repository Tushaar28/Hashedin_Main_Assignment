package com.tushaar.mainassignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.models.Order;
import com.tushaar.mainassignment.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public ResponseEntity<?> createOrder(CreateOrderDTO order) {
		try {
			Order o = new Order();
			o.setPrice(order.getPrice());
			o.setProductId(order.getProductId());
			o.setQuantity(order.getQuantity());
			Order savedOrder = repository.save(o);
			return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getOrderById(Long id) {
		try {
			Optional<Order> order = repository.findById(id);
			if (order.isEmpty()) {
				return new ResponseEntity<>("No order found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrdersForUser(Long id) {
		try {
			List<Order> orders = repository.findByUserId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}