package com.tushaar.mainassignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tushaar.mainassignment.dtos.OrderResponse;
import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	public ResponseEntity<?> createUser(User user) {
		try {
			User newUser = repository.save(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> findUserById(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> updateUserById(long id, UserUpdateDTO userChanges) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
			}
			User u = user.get();
			if (userChanges.getAddress() != null) {
				u.setAddress(userChanges.getAddress());
			}
			if (userChanges.getName() != null) {
				u.setName(userChanges.getName());
			}
			User updatedUser = repository.save(u);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> deleteUserById(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
			}
			repository.deleteById(id);
			return new ResponseEntity<>("User deleted", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrdersByUserId(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				return new ResponseEntity<>("No user found", HttpStatus.BAD_REQUEST);
			}
			List<Object> response = (ArrayList<Object>) restTemplate.getForObject("http://ORDER-SERVICE/orders/user/" + id, Object.class);
			List<OrderResponse> orders = objectMapper.convertValue(response, new TypeReference<List<OrderResponse>>() { });
			return new ResponseEntity<>(orders, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
