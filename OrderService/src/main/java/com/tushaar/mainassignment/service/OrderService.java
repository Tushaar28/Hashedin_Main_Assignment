package com.tushaar.mainassignment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.dtos.CreatePaymentDTO;
import com.tushaar.mainassignment.dtos.PaymentResponse;
import com.tushaar.mainassignment.enums.OrderStatus;
import com.tushaar.mainassignment.enums.PaymentStatus;
import com.tushaar.mainassignment.feign.PaymentsAPI;
import com.tushaar.mainassignment.models.Order;
import com.tushaar.mainassignment.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentsAPI paymentsAPI;

	@Transactional
	public ResponseEntity<?> createOrder(CreateOrderDTO order) {
			Order o = new Order();
			o.setProductId(order.getProductId());
			o.setQuantity(order.getQuantity());
			o.setUserId(order.getUserId());
			o.setStatus(OrderStatus.PAYMENT_PENDING);
			Order savedOrder = repository.save(o);
			PaymentResponse response = paymentsAPI.createPayment(new CreatePaymentDTO(savedOrder.getId(), order.getPayment()));
			if (response.getStatus() == PaymentStatus.ACCEPTED) {
				savedOrder.setStatus(OrderStatus.ACCEPTED);
			} else if (response.getStatus() == PaymentStatus.REJECTED) {
				savedOrder.setStatus(OrderStatus.PAYMENT_REJECTED);
			} else if (response.getStatus() == PaymentStatus.PENDING) {
				savedOrder.setStatus(OrderStatus.PAYMENT_PENDING);
			}
			savedOrder = repository.save(savedOrder);
			return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
		}

	public ResponseEntity<?> getOrderById(Long id) {
		try {
			Optional<Order> order = repository.findById(id);
			if (order.isEmpty()) {
				return new ResponseEntity<>("No order found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrdersForUser(Long id) {
		try {
			List<Order> orders = repository.findByUserId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrders() {
		try {
			List<Order> orders = repository.findAll();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
