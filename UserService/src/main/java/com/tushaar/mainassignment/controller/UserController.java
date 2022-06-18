package com.tushaar.mainassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable String id) {
		return service.findUserById(id);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable String id, @RequestBody UserUpdateDTO userChanges) {
		return service.updateUserById(id, userChanges);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable String id) {
		return service.deleteUserById(id);
	}
}
