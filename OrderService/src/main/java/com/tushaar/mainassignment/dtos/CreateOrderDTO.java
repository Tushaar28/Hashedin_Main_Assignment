package com.tushaar.mainassignment.dtos;

import com.tushaar.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
	private Long productId;
	private double quantity;
	private Long userId;
	private PaymentStatus payment;
}
