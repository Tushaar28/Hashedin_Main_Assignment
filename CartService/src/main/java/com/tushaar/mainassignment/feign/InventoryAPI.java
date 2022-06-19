package com.tushaar.mainassignment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tushaar.mainassignment.dtos.Inventory;
import com.tushaar.mainassignment.dtos.UpdateInventoryDTO;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryAPI {
	
	@GetMapping("/products/{id}")
	public Inventory getInventoryById(@PathVariable Long id);
	
	@PatchMapping("/products/{id}")
	public Object updateInventory(@PathVariable Long id, @RequestBody UpdateInventoryDTO inventoryChanges);

}
