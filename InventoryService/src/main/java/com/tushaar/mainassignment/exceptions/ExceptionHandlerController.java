package com.tushaar.mainassignment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> productNotFoundException(ProductNotFoundException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				"No product found",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<?> invalidQuantityException(InvalidQuantityException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"Invalid quantity for product",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidPriceException.class)
	public ResponseEntity<?> invalidPriceException(InvalidPriceException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"Invalid price for product",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
