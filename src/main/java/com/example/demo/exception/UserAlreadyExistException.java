package com.example.demo.exception;

public class UserAlreadyExistException extends Exception {
	
	private String message;

	public UserAlreadyExistException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistException [message=" + message + "]";
	}
	
	
	
	
}
