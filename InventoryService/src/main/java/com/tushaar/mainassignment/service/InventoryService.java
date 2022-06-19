package com.tushaar.mainassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.UpdateInventoryDTO;
import com.tushaar.mainassignment.models.Inventory;
import com.tushaar.mainassignment.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository repository;

	public ResponseEntity<?> createInventory(Inventory inventory) {
		try {
			Inventory savedInventory = repository.save(inventory);
			return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getInventoryById(long id) {
		try {
			Optional<Inventory> inventory = repository.findById(id);
			if (inventory.isEmpty()) {
				return new ResponseEntity<>("No product with " + id + " found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(inventory.get(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> updateInventoryById(long id, UpdateInventoryDTO inventoryChanges) {
		try {
			Optional<Inventory> inventory = repository.findById(id);
			if (inventory.isEmpty()) {
				return new ResponseEntity<>("No product with " + id + " found", HttpStatus.BAD_REQUEST);
			}
			Inventory oldInventory = inventory.get();
			if (inventoryChanges.getDescription() != null) {
				oldInventory.setDescription(inventoryChanges.getDescription());
			}
			if (inventoryChanges.getName() != null) {
				oldInventory.setName(inventoryChanges.getName());
			}
			if (Double.valueOf(inventoryChanges.getPrice()) != null) {
				oldInventory.setPrice(inventoryChanges.getPrice());
			}
			if (Double.valueOf(inventoryChanges.getQuantity()) != null) {
				oldInventory.setQuantity(inventoryChanges.getQuantity());
			}
			Inventory updatedInventory = repository.save(oldInventory);
			return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
