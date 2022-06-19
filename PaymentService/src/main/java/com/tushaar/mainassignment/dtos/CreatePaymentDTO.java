package com.tushaar.mainassignment.dtos;

import com.tushaar.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDTO {

	private long orderId;
	private PaymentStatus status;
}
