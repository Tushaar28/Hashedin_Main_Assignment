package com.tushaar.mainassignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.OrderResponse;
import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.exceptions.MobileAlreadyExistsException;
import com.tushaar.mainassignment.exceptions.UserNotFoundException;
import com.tushaar.mainassignment.feign.OrdersAPI;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private OrdersAPI ordersAPI;

	public ResponseEntity<Object> createUser(User user) {
		Optional<User> u = repository.findByMobile(user.getMobile());
		if (u.isPresent()) {
			throw new MobileAlreadyExistsException();
		}
		User newUser = repository.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> findUserById(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				throw new UserNotFoundException();
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> updateUserById(long id, UserUpdateDTO userChanges) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				throw new UserNotFoundException();
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
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> deleteUserById(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				throw new UserNotFoundException();
			}
			repository.deleteById(id);
			return new ResponseEntity<>("User deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrdersByUserId(long id) {
		try {
			Optional<User> user = repository.findById(id);
			if (user.isEmpty()) {
				throw new UserNotFoundException();
			}
			List<OrderResponse> response = ordersAPI.getAllOrdersByUserId(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
