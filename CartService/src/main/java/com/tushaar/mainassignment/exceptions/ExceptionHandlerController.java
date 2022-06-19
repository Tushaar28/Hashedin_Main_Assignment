package com.tushaar.mainassignment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<ErrorMessage> invalidQuantityException(InvalidQuantityException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"Requested quantity is more than available quantity",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

}
