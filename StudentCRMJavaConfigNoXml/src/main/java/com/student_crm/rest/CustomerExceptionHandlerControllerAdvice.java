package com.student_crm.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.student_crm.rest.exception.CustomerErrorResponse;
import com.student_crm.rest.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandlerControllerAdvice {

	// Define exception handler method with @ExceptionHandler to handle StudentNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		// create CustomerErrorResponse object
		CustomerErrorResponse  errorResponse = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),
				exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	// Define exception handler method with @ExceptionHandler to handle Any Exception(Generic Exception)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		// create CustomerErrorResponse object
		CustomerErrorResponse  errorResponse = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
}
