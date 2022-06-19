package com.tushaar.mainassignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryDTO {

	private String name;
	private String description;
	private double price;
	private double quantity;
}
