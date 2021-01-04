package com.example.demo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.ServiceRequest;
import com.example.demo.payload.BaseResponse;
import com.example.demo.service.ServiceRequestService;


import io.swagger.annotations.ApiOperation;



/*

@RestMapping is used to map the web requests.*
 *
 * @author 
 *
 */
@RestController
@RequestMapping("/servicerequest")
public class ServiceRequestController {
	
	private static Logger logger=LoggerFactory.getLogger(ServiceRequestController.class);
	@Autowired
	private ServiceRequestService requestService;
	
	/**
	 * 
	 * @GetMapping: is used to handle GET type of request method. 
	 * This method will give ServiceRequest with provided id in URL.
	 */
	@GetMapping("/{id}")
	@ApiOperation(value="find Service by Id")
    public ResponseEntity<ServiceRequest> fetchRequestById(@PathVariable("id") Long id) {
		
		logger.info("Enter ServiceRequestController :: method=fetchRequestById");
		ServiceRequest request = requestService.findServiceRequestById(id);			
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);
		logger.info("Exit ServiceRequestController :: method=fetchRequestById");
		return new ResponseEntity<>(request,HttpStatus.CREATED);
		
	}
	
	/**
	 * @PathVariable: It is used to extract the value from URL.
	 * This method Create  Service Request When there is no other Open service Request Request in Account.
	 * 
	 */
	
	@GetMapping("/{reqid}/{id}")
	@ApiOperation(value = "create new service Request")
	public ResponseEntity<?> createRequest(@PathVariable("reqid") Long reqid,@PathVariable("id") Long id){
		
		logger.info("Enter ServiceRequestController :: method=createRequest");
		ServiceRequest requestObj = requestService.create(reqid,id);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(requestObj);
		logger.info("Exit ServiceRequestController :: method=createRequest");
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
	/**
	 * @GetMapping: is used to handle GET type of request method.
	 * This method Gives all the Service Request which are Open.
	 */
	@GetMapping("/all")
	@ApiOperation(value = "Show All Open Service Request")
	public ResponseEntity<?> fetchAllOpenRequest() {
		
		logger.info("Enter ServiceRequestController :: method=fetchAllOpenRequest");
		List<ServiceRequest> request = requestService.getAllOpenServiceRequest();
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		logger.info("Exit ServiceRequestController :: method=fetchAllOpenRequest");
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);		
		
	}
	
	/**
	 * 
	 * @PutMapping is usd to create a web service endpoints that create or update.
	 * This method will close/update the service request if new Recharge is Done.
	 */
	@PutMapping("/close Request{id}")
	@ApiOperation(value = "close the request")
	public ResponseEntity<?> closeRequest(@PathVariable("id") Long id) {
		logger.info("Enter ServiceRequestController :: method=closeRequest");
		ServiceRequest request =requestService.update(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		logger.info("Exit ServiceRequestController :: method=closeRequest");
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);			
	}
	
	/**
	 * 
	 * @DeleteMapping is used to create a web service endpoint that delete a resource.
	 * This method will delete the ServiceRequest.
	 */
	@DeleteMapping("/delete Request{id}")
	@ApiOperation(value = "delete  request")
	public ResponseEntity<?> deleteRequest(@PathVariable("id") Long id) {
		logger.info("Enter ServiceRequestController :: method=deleteRequest");
		ServiceRequest request =requestService.deleteRequest(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		logger.info("Exit ServiceRequestController :: method=deleteRequest");
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);			
	}
	
}
