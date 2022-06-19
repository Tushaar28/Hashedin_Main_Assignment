package com.tushaar.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MobileAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MobileAlreadyExistsException(String msg) {
		super(msg);
	}
}
