package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidOrderIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidOrderIdException(String msg) {
		super(msg);
	}
}
