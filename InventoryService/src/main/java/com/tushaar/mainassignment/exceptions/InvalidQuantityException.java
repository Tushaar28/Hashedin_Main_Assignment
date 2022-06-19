package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidQuantityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidQuantityException(String msg) {
		super(msg);
	}
}
