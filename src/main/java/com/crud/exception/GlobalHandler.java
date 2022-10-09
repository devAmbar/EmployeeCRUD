package com.crud.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandler {
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<ErrorDetails> handleEmployeeNotFound(EmployeeNotFound enf, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), enf.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}

}
