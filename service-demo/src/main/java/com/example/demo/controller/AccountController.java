package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.payload.BaseResponse;
import com.example.demo.service.AccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/account")

public class AccountController {
	@Autowired
	private AccountService accountservice;
	
	@PostMapping("/")
	@ApiOperation(value = "Add a account")
	public ResponseEntity<?> saveAccount(@RequestBody Account account){
		
		Account addressbj = accountservice.saveAccount(account);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(addressbj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
}
