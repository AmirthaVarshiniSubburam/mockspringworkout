package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> userAlreadyExistsException(){
		return new ResponseEntity<String>("User Already Exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(){
		return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
	}
	
	
}
