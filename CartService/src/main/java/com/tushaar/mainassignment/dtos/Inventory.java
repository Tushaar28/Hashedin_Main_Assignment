package com.tushaar.mainassignment.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	
	private Long id;
	private String name;
	private String description;
	private double price;
	private double quantity;
}
