package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidPriceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPriceException(String msg) {
		super(msg);
	}
}
