package com.tushaar.mainassignment.dtos;

import com.tushaar.mainassignment.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
	private long productId;
	private double quantity;
	private double price;
	private OrderStatus status;
	private long userId;
}
