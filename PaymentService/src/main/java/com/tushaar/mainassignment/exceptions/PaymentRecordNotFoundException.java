package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentRecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PaymentRecordNotFoundException(String msg) {
		super(msg);
	}
}
