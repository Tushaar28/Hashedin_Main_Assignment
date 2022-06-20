package com.tushaar.mainassignment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.Inventory;
import com.tushaar.mainassignment.dtos.UpdateInventoryDTO;
import com.tushaar.mainassignment.exceptions.InvalidQuantityException;
import com.tushaar.mainassignment.feign.InventoryAPI;
import com.tushaar.mainassignment.models.Cart;
import com.tushaar.mainassignment.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository repository;
	
	@Autowired
	private InventoryAPI inventoryAPI;

	public ResponseEntity<?> getAllCartItems(Long userId) {
		List<Cart> items = repository.findByUserId(userId);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<?> updateItemToCart(Cart cart) {
		Long userId = cart.getUserId();
		Long productId = cart.getProductId();
		Optional<Cart> items = repository.findByUserIdAndProductId(userId, productId);
		if (items.isEmpty()) {
			Cart savedItems = repository.save(cart);
			return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
		}
		Cart oldItems = items.get();
		Inventory inventory = inventoryAPI.getInventoryById(productId);
		if (cart.getQuantity() > inventory.getQuantity()) {
			throw new InvalidQuantityException();
		}
		oldItems.setQuantity(cart.getQuantity());
		oldItems = repository.save(oldItems);
		UpdateInventoryDTO inventoryChanges = new UpdateInventoryDTO(inventory.getQuantity() - oldItems.getQuantity());
		inventoryAPI.updateInventory(productId, inventoryChanges);
		return new ResponseEntity<>(oldItems, HttpStatus.OK);
	}
}