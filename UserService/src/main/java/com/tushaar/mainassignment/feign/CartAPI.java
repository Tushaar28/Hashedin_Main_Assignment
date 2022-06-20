package com.tushaar.mainassignment.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tushaar.mainassignment.dtos.CartRequestDTO;
import com.tushaar.mainassignment.dtos.CartResponseDTO;

@FeignClient("CART-SERVICE")
public interface CartAPI {

	@GetMapping("/cart/{id}")
	public List<CartResponseDTO> getAllItemsInCart(@PathVariable Long id);
	
	@PostMapping("/cart")
	public CartResponseDTO addItemToCart(@RequestBody CartRequestDTO request);
}
