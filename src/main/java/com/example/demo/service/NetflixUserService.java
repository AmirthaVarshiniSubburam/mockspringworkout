package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.NetflixUsers;

public interface NetflixUserService {

	public NetflixUsers registerUser(NetflixUsers netflixUser) throws UserAlreadyExistException; //post request - save a user record
	public NetflixUsers findUserbyId(String username) throws UserNotFoundException; // get request - retrieve a record
	public List<NetflixUsers> getAllUser(); //get request - to retrieve all records
	public NetflixUsers deleteAUser(String username); //delete request
	public NetflixUsers updateAUser(NetflixUsers netflixUser); // PUT - PATCH request
	
	
	
	
	
	
	
	
	
}
