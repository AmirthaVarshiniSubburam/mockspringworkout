package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.NetflixUsers;
import com.example.demo.repository.NetflixUserRepo;

@Service
public class NetflixUserServiceImpl implements NetflixUserService {
	
	@Autowired
	NetflixUserRepo NetflixRepo;
	
	@Override
	public NetflixUsers registerUser(NetflixUsers netflixUser) throws UserAlreadyExistException {
		return NetflixRepo.save(netflixUser);
	}

	@Override
	public NetflixUsers findUserbyId(String username) throws UserNotFoundException {
		return NetflixRepo.findById(username).get();
	}

	@Override
	public List<NetflixUsers> getAllUser() {
		return NetflixRepo.findAll();
	}

	@Override
	public NetflixUsers deleteAUser(String username) {
		NetflixUsers deletedNetflixUser = null;
		
		Optional optional = NetflixRepo.findById(username);
		
		if(optional.isPresent()) {
		deletedNetflixUser = NetflixRepo.findById(username).get();
		NetflixRepo.deleteById(username);
		}
		
		return deletedNetflixUser;
	}

	@Override
	public NetflixUsers updateAUser(NetflixUsers netflixUser) {
		NetflixUsers updatedNetflixUser = null;
		
		Optional optional = NetflixRepo.findById(netflixUser.getId());
		
		if (optional.isPresent()) {
			NetflixUsers getUser = NetflixRepo.findById(netflixUser.getId()).get();
			getUser.setEmailid(netflixUser.getEmailid());
			getUser.setMobno(netflixUser.getMobno());
			getUser.setName(netflixUser.getName());
			getUser.setPassword(netflixUser.getPassword());
			
			updatedNetflixUser = NetflixRepo.save(getUser);
		}
		
		return updatedNetflixUser;
	}

	
	
}
