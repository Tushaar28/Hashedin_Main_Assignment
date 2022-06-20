package com.tushaar.mainassignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

	private Long id;
	private Long userId;
	private Long productId;
	private Double quantity;
}
