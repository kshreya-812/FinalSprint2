package com.example.demo.service;

import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.OperationFailedException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		User userObj = null;
		try {
			userObj = userRepository.save(user);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return userObj;
	}
}
