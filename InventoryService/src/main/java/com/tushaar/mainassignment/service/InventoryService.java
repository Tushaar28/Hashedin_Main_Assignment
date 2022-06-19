package com.tushaar.mainassignment.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushaar.mainassignment.dtos.UpdateInventoryDTO;
import com.tushaar.mainassignment.exceptions.InvalidPriceException;
import com.tushaar.mainassignment.exceptions.InvalidQuantityException;
import com.tushaar.mainassignment.exceptions.ProductNotFoundException;
import com.tushaar.mainassignment.models.Inventory;
import com.tushaar.mainassignment.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository repository;

	@PostConstruct
	public void initialLoad() {
		List<Inventory> inventory = Arrays.asList(new Inventory(1L, "Product1", "", 1000.12, 300),
				new Inventory(2L, "Product2", "Desc2", 100, 98000.12));
		repository.saveAll(inventory);
	}

	@Transactional
	public ResponseEntity<?> createInventory(Inventory inventory) {
		try {
			Inventory savedInventory = repository.save(inventory);
			return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
		} catch (Exception e) {
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
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<?> updateInventoryById(long id, UpdateInventoryDTO inventoryChanges) {
		try {
			Optional<Inventory> inventory = repository.findById(id);
			if (inventory.isEmpty()) {
				throw new ProductNotFoundException();
			}
			if (Double.valueOf(inventoryChanges.getPrice()) != null && inventoryChanges.getPrice() <= 0) {
				throw new InvalidPriceException();
			}
			if (Double.valueOf(inventoryChanges.getQuantity()) != null && inventoryChanges.getQuantity() < 0) {
				throw new InvalidQuantityException();
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
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllInventory() {
		List<Inventory> res = repository.findAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
