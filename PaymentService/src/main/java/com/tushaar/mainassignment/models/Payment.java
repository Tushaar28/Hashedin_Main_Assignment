package com.tushaar.mainassignment.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tushaar.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	@Id
	private long orderId;

	@Column(nullable = false)
	private PaymentStatus status;

	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt = new Date();

	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt = this.createdAt;
}
