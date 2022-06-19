package com.tushaar.mainassignment.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tushaar.mainassignment.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

	private long id;
	private long userId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	private long productId;
	private double quantity;
	private double price;
	private OrderStatus status;

}
