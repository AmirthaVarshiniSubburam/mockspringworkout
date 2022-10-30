package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.NetflixUsers;
import com.example.demo.repository.NetflixUserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	
	@Autowired
	NetflixUserRepo netflixUserRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 NetflixUsers netflixUser = netflixUserRepo.findById(username).get();
		 
		return new User(netflixUser.getId(), netflixUser.getPassword(), new ArrayList<>()) ;
	}

}
