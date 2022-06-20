package com.tushaar.mainassignment.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.AuthenticationRequest;
import com.tushaar.mainassignment.dtos.CartRequestDTO;
import com.tushaar.mainassignment.dtos.CartResponseDTO;
import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.dtos.OrderResponse;
import com.tushaar.mainassignment.dtos.ProductDTO;
import com.tushaar.mainassignment.dtos.UserUpdateDTO;
import com.tushaar.mainassignment.enums.PaymentStatus;
import com.tushaar.mainassignment.exceptions.MobileAlreadyExistsException;
import com.tushaar.mainassignment.exceptions.UserNotFoundException;
import com.tushaar.mainassignment.feign.CartAPI;
import com.tushaar.mainassignment.feign.InventoryAPI;
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
	private InventoryAPI inventoryAPI;

	@Autowired
	private JwtUtil jwtUtil;

	public boolean authenticate(Map<String, String> headers, Long id) {
		String authHeader = headers.getOrDefault("authorization", null);
		if (authHeader == null) {
			return false;
		}
		String jwt = authHeader.substring(7);
		return jwtUtil.validateToken(jwt, String.valueOf(id));
	}

	public ResponseEntity<Object> createUser(User user) {
		Optional<User> u = repository.findByMobile(user.getMobile());
		if (u.isPresent()) {
			throw new MobileAlreadyExistsException();
		}
		User newUser = repository.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> findUserById(Map<String, String> headers, long id) {
		if (authenticate(headers, id) == false) {
			return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateUserById(long id, UserUpdateDTO userChanges) {
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
	}

	public ResponseEntity<Object> deleteUserById(long id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		repository.deleteById(id);
		return new ResponseEntity<>("User deleted", HttpStatus.OK);

	}

	public ResponseEntity<?> getAllOrdersByUserId(long id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException();
		}
		List<OrderResponse> response = ordersAPI.getAllOrdersByUserId(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
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
		for (CartResponseDTO item : cartItems) {
			CreateOrderDTO order = new CreateOrderDTO();
			order.setPayment(PaymentStatus.PENDING);
			order.setProductId(item.getProductId());
			order.setQuantity(item.getQuantity());
			order.setUserId(id);
			ordersAPI.createOrder(order);
		}
		return new ResponseEntity<>("Order created", HttpStatus.CREATED);
	}

	public ResponseEntity<?> getProductsSortedByPrice(Map<String, String> headers, Long id, String sortByPrice) {
		if (authenticate(headers, id) == false) {
			return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
		List<ProductDTO> products = inventoryAPI.getProducts();
		if (sortByPrice.equals("asc")) {
			Collections.sort(products, new Comparator<ProductDTO>() {
				@Override
				public int compare(ProductDTO p1, ProductDTO p2) {
					return (int) (p1.getPrice() - p2.getPrice());
				}
			});
		} else if (sortByPrice.equals("desc")) {
			Collections.sort(products, new Comparator<ProductDTO>() {
				@Override
				public int compare(ProductDTO p1, ProductDTO p2) {
					return (int) (p2.getPrice() - p1.getPrice());
				}
			});
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<?> addItemToCart(Map<String, String> headers, Long id, CartRequestDTO cart) {
		if (authenticate(headers, id) == false) {
			return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
		CartResponseDTO response = cartAPI.addItemToCart(cart);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
