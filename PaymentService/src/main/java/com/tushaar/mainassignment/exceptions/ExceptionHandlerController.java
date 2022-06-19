package com.tushaar.mainassignment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(PaymentRecordNotFoundException.class)
	public ResponseEntity<ErrorMessage> paymentRecordNotFoundException(PaymentRecordNotFoundException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				"No record found",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentRecordAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> paymentRecordAlreadyExistsException(PaymentRecordAlreadyExistsException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"Payment Record already exists",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidOrderIdException.class)
	public ResponseEntity<ErrorMessage> invalidOrderIdException(InvalidOrderIdException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				"Order ID field is not found or is invalid",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidPaymentStatusException.class)
	public ResponseEntity<ErrorMessage> invalidPaymentStatusException(InvalidPaymentStatusException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				"Status field is not found or is not among the following accepted values - ACCEPTED, REJECTED, PENDING",
				req.getDescription(false));
		
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
}
