package com.tushaar.mainassignment.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tushaar.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {

	private long orderId;
	private PaymentStatus status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;
}
