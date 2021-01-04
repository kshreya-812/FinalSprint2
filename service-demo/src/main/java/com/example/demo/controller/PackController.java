package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pack;
import com.example.demo.payload.BaseResponse;
import com.example.demo.service.PackService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pack")
public class PackController {
	@Autowired
	private PackService packservice;
	
	@PostMapping("/")
	@ApiOperation(value = "Add a account")
	public ResponseEntity<?> savePack(@RequestBody Pack pack){
		
		Pack packObj = packservice.savePack(pack);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(packObj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
}