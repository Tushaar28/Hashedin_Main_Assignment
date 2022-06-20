package com.tushaar.mainassignment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.AuthenticationRequest;
import com.tushaar.mainassignment.dtos.CartResponseDTO;
import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.dtos.OrderResponse;
import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.enums.PaymentStatus;
import com.tushaar.mainassignment.exceptions.MobileAlreadyExistsException;
import com.tushaar.mainassignment.exceptions.UserNotFoundException;
import com.tushaar.mainassignment.feign.CartAPI;
import com.tushaar.mainassignment.feign.OrdersAPI;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.repository.UserRepository;
import com.tushaar.mainassignment.utils.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private OrdersAPI ordersAPI;
	
	@Autowired
	private CartAPI cartAPI;
	
	@Autowired
	private JwtUtil jwtUtil;

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

	public ResponseEntity<?> login(AuthenticationRequest request) {
		Optional<User> user = repository.findById(request.getId());
		if (user.isEmpty()) {
			return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
		}
		String token = jwtUtil.generateToken(request.getId());
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> createOrder(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		List<CartResponseDTO> cartItems = cartAPI.getAllItemsInCart(id);
		for (CartResponseDTO item: cartItems) {
			CreateOrderDTO order = new CreateOrderDTO();
			order.setPayment(PaymentStatus.ACCEPTED);
			order.setProductId(item.getProductId());
			order.setQuantity(item.getQuantity());
			order.setUserId(id);
			ordersAPI.createOrder(order);
		}
		return new ResponseEntity<>("Order created", HttpStatus.CREATED);
	}
}
