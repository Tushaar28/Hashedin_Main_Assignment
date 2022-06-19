package com.tushaar.mainassignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.mainassignment.dtos.UpdateInventoryDTO;
import com.tushaar.mainassignment.models.Inventory;
import com.tushaar.mainassignment.service.InventoryService;

@RestController
@RequestMapping("/products")
public class InventoryController {

	@Autowired
	private InventoryService service;
	
	@PostMapping
	public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
		return service.createInventory(inventory);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getInventoryById(@PathVariable long id) {
		return service.getInventoryById(id);
	} 
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateInventoryById(@PathVariable long id, @RequestBody UpdateInventoryDTO inventoryChanges) {
		return service.updateInventoryById(id, inventoryChanges);
	}
}
