package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidPaymentStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPaymentStatusException(String msg) {
		super(msg);
	}
}
