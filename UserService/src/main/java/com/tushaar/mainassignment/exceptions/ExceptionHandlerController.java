package com.tushaar.mainassignment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(MobileAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> mobileAlreadyExistsException(MobileAlreadyExistsException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.CONFLICT.value(),
				new Date(),
				"User with same mobile already exists",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"No user found",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

}
