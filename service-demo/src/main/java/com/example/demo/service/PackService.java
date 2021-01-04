package com.example.demo.service;

import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pack;
import com.example.demo.exception.OperationFailedException;
import com.example.demo.repository.PackRepository;

@Service
public class PackService {
	@Autowired
	private PackRepository packRepository;
	
	@Transactional
	public Pack savePack(Pack pack) {
		Pack packObj = null;
		try {
			packObj = packRepository.save(pack);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return packObj;
	}

}
