package com.tushaar.mainassignment.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tushaar.mainassignment.dtos.CreateOrderDTO;
import com.tushaar.mainassignment.dtos.OrderResponse;

@FeignClient("ORDER-SERVICE")
public interface OrdersAPI {
	
	@GetMapping("/orders/user/{id}")
	public List<OrderResponse> getAllOrdersByUserId(@PathVariable long id);
	
	@PostMapping("/orders")
	public OrderResponse createOrder(@RequestBody CreateOrderDTO order);

}
