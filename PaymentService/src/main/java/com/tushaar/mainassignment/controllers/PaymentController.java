package com.tushaar.mainassignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.mainassignment.dtos.CreatePaymentDTO;
import com.tushaar.mainassignment.enums.PaymentStatus;
import com.tushaar.mainassignment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable long id) {
		return service.getPaymentById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> createPayment(@RequestBody CreatePaymentDTO payment) {
		return service.createPayment(payment);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePaymentById(@PathVariable Long id, @RequestBody PaymentStatus status) {
		return service.updatePaymentById(id, status);
	}
}
