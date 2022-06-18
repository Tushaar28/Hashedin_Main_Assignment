package com.tushaar.mainassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public ResponseEntity<?> createUser(User user) {
		try {
			User newUser = repository.save(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> findUserById(String id) {
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

	public ResponseEntity<?> updateUserById(String id, UserUpdateDTO userChanges) {
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

	public ResponseEntity<?> deleteUserById(String id) {
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
}