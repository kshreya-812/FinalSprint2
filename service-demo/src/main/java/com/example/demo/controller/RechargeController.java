package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;

import com.example.demo.entity.Recharge;
import com.example.demo.payload.BaseResponse;
import com.example.demo.repository.RechargeRepository;
import com.example.demo.service.RechargeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
	@Autowired
	private RechargeService rechargeservice;
	
	@Autowired
	private RechargeRepository rechargeRepository;
	

	@PostMapping("/")
	@ApiOperation(value = "Add a recharge")
	public ResponseEntity<?> saveRecharge(@RequestBody Recharge recharge){
		
		Recharge requestObj = rechargeservice.saveRecharge(recharge);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(requestObj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a recharge")
	public ResponseEntity<?> updateRecharge(@PathVariable("id") Long id){
		
    Recharge requestObj = rechargeservice.updateRechargeDate(id);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(requestObj);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "find Recharges For User In DescendingOrder ByPurchasedDate")
	public ResponseEntity<?> findRechargesForUserInDescendingOrderByPurchasedDate(@PathVariable Long id){
		
    List<Recharge> recharge=rechargeservice.findRechargesForUserInDescendingOrderByPurchasedDate(id);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(recharge);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
	//working
	@GetMapping("/{startDate}/{endDate}")
	@ApiOperation(value = "Count recharges in given period")
	public ResponseEntity<?> countRechargesInPeriod(@PathVariable@DateTimeFormat (pattern="dd-MM-yyyy") LocalDate startDate,
			@PathVariable@DateTimeFormat (pattern="dd-MM-yyyy") LocalDate endDate ){
		
	int count=rechargeservice.countRechargesInPeriod(startDate, endDate);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(count);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
	//working
	@GetMapping("/CountInPeriod{id}")
	@ApiOperation(value = "Count recharges in given period")
	public ResponseEntity<?> rechargesForUserCount(@PathVariable("id") Long id){
		
    int count=rechargeservice.rechargesForUserCount(id);
    BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(count);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
	//working
	@GetMapping("/count{startDate}/{endDate}")
	@ApiOperation(value = "Count recharges in given period")
	public ResponseEntity<?> totalRevenueInPeriod(@PathVariable@DateTimeFormat (pattern="dd-MM-yyyy") LocalDate startDate,
			@PathVariable@DateTimeFormat (pattern="dd-MM-yyyy") LocalDate endDate ){
	
	double revenue=rechargeservice.totalRevenueInPeriod(startDate, endDate);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(revenue);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
	
/*	@GetMapping("/RechargeCount")
	@ApiOperation(value = "Count recharges")
	public ResponseEntity<?> rechargesCount(@RequestBody Pack pack){
		
	int count=rechargeservice.rechargesCount(pack);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(count);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }
	*/
	//working
	@GetMapping("/")
	@ApiOperation(value = "Count recharges")
	public ResponseEntity<?> getAllRecharge(){
	List<Recharge> recharge=rechargeservice.getAllRecharge();
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(recharge);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    
	}
	//working
	@GetMapping("/all{startDate}/{endDate}")
	@ApiOperation(value = "Count recharges")
	public ResponseEntity<?> findAllRechargesInPeriod(@PathVariable@DateTimeFormat (pattern="dd-MM-yyyy") LocalDate startDate,
			@PathVariable @DateTimeFormat (pattern="dd-MM-yyyy")LocalDate endDate ){
    List<Recharge> recharge=rechargeservice.findAllRechargesInPeriod(startDate, endDate);
    BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(recharge);		
	return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
}
