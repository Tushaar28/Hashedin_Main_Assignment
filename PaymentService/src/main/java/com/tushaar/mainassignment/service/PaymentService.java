package com.tushaar.mainassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.models.Payment;
import com.tushaar.mainassignment.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	public ResponseEntity<?> getPaymentById(long id) {
		try {
			Optional<Payment> payment = repository.findById(id);
			if (payment.isEmpty()) {
				return new ResponseEntity<>("No payment for Order ID " + id + " found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(payment.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
