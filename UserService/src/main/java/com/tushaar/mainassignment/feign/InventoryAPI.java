package com.tushaar.mainassignment.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tushaar.mainassignment.dtos.ProductDTO;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryAPI {
	
	@GetMapping("/products")
	public List<ProductDTO> getProducts();

}
