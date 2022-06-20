package com.tushaar.mainassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.mainassignment.dtos.AuthenticationRequest;
import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	private final static String USER_SERVICE = "userService";
	
	public ResponseEntity<?> fallbackOrderService(Exception e) throws Exception{
		return new ResponseEntity<>("Order service is down. Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) throws Exception {
		return service.login(request);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable long id) {
		return service.findUserById(id);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable long id, @RequestBody UserUpdateDTO userChanges) {
		return service.updateUserById(id, userChanges);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable long id) {
		return service.deleteUserById(id);
	}
	
	@GetMapping("/{id}/orders")
	@CircuitBreaker(name = USER_SERVICE, fallbackMethod = "fallbackOrderService")
	public ResponseEntity<?> getAllOrdersByUserId(@PathVariable long id) {
		return service.getAllOrdersByUserId(id);
	}
	
	@PostMapping("/{id}/order")
	public ResponseEntity<?> createOrder(@PathVariable Long id) {
		return service.createOrder(id);
	}
}
