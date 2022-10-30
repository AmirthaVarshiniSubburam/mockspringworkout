package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.JWTRequest;
import com.example.demo.model.JWTResponse;
import com.example.demo.model.NetflixUsers;
import com.example.demo.service.MyUserDetailService;
import com.example.demo.service.NetflixUserService;
import com.example.demo.util.JWTUtility;


@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
public class NetflixController {
	
	@Autowired
	NetflixUserService netflixServ;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	MyUserDetailService userDetailService;
	
	@Autowired
	JWTUtility jwtUtitlity;


	
	@PostMapping("/netflixusers")
	public ResponseEntity<NetflixUsers> resgisterUser(@RequestBody NetflixUsers netflixUsers) throws UserAlreadyExistException{
		return new ResponseEntity<NetflixUsers>(netflixServ.registerUser(netflixUsers),HttpStatus.CREATED);
	}
	
	@GetMapping("/netflixusers")
	public ResponseEntity<List<NetflixUsers>> getAllUser(){
		return new ResponseEntity<List<NetflixUsers>>(netflixServ.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/netflixusers/{username}")
	public ResponseEntity<NetflixUsers> findAUserbyId(@PathVariable String username) throws UserNotFoundException{
		return new ResponseEntity<NetflixUsers>(netflixServ.findUserbyId(username), HttpStatus.OK);
	}
	
	@DeleteMapping("/netflixusers/{username}")
	public ResponseEntity<NetflixUsers> deleteAUserbyId(@PathVariable String username){
		return new ResponseEntity<NetflixUsers>(netflixServ.deleteAUser(username),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/netflixusers")
	public ResponseEntity<NetflixUsers> updateAUser(@RequestBody NetflixUsers updatedNetflixUser){
		return new ResponseEntity<NetflixUsers>(netflixServ.updateAUser(updatedNetflixUser),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public JWTResponse login(@RequestBody JWTRequest request) throws Exception {
		
try {
			
			manager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())	
					);
			
		}
		catch( BadCredentialsException e ){
			throw new Exception("Wrong_Username_or_Password");
		}
		
		UserDetails userdetail = userDetailService.loadUserByUsername(request.getUsername());
		
		String generateToken = jwtUtitlity.generateToken(userdetail);
		return new JWTResponse(generateToken);

		
	}
	
	
	
	
}
