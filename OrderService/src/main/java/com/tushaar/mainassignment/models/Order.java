package com.tushaar.mainassignment.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tushaar.mainassignment.enums.OrderStatus;
import com.tushaar.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private long userId;

	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt = new Date();
	
	@Column(nullable = false)
	private long productId;
	
	@Column(nullable = false)
	private double quantity;
	
	@Column(nullable = false)
	private OrderStatus status;
}