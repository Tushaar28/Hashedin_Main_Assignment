package com.tushaar.mainassignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/fallback")
public class GatewayController {
	
	@GetMapping("/user")
	public String userServiceFallbackMessage() {
		return "There is some error in connecting to User Service. Please try again later";
	}
	
	@GetMapping("/order")
	public String orderServiceFallbackMessage() {
		return "There is some error in connecting to Order Service. Please try again later";
	}
	
	@GetMapping("/cart")
	public String cartServiceFallbackMessage() {
		return "There is some error in connecting to Cart Service. Please try again later";
	}
	
	@GetMapping("/payment")
	public String paymentServiceFallbackMessage() {
		return "There is some error in connecting to Payment Service. Please try again later";
	}
	
	@GetMapping("/inventory")
	public String inventoryServiceFallbackMessage() {
		return "There is some error in connecting to Inventory Service. Please try again later";
	}

}
