package com.example.demo.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.ServiceRequest;
import com.example.demo.exception.OperationFailedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RechargeRepository;
import com.example.demo.repository.ServiceRequestRepository;


import static com.example.demo.util.AppConstants.REQUEST_NOT_FOUND_CONST;
import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;
import static com.example.demo.util.AppConstants.OPEN_SERVICE_FOUND_CONST;
import static com.example.demo.util.AppConstants.RECHARGE_NOT_FOUND;




@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
	
	private static Logger logger=LoggerFactory.getLogger(ServiceRequestServiceImpl.class);
	@Autowired
	private ServiceRequestRepository requestRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private RechargeRepository rechargeRepository;

	
	/*
	 * This method Create  Service Request When there is no other Open service Request Request in Account.
	 */
	@Override
	public ServiceRequest findServiceRequestById(Long id) {
		logger.info("Enter ServiceRequestController :: method=findServiceRequestById");
		Optional<ServiceRequest> request;
		try {
			request=requestRepository.findById(id);
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}	
		 if (!request.isPresent())
		 {
		      throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
	     }
		 logger.info("Enter ServiceRequestController :: method=findServiceRequestById");   
			
			return request.get();
		}
	
	
	 /** This method Gives all the Service Request which are Open in Account.
      */
	@Override
	public List<ServiceRequest> getAllOpenServiceRequest() {
		logger.info("Enter ServiceRequestController :: method=getAllOpenServiceRequest");
		List<ServiceRequest>req =null;
		List<ServiceRequest> req1=null;
		try {
		
		req=requestRepository.findAll();
		req1=req.stream().filter(x->x.isStatusOpened()==true).collect(Collectors.toList());
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}	
		logger.info("Exit ServiceRequestController :: method=getAllOpenServiceRequest");
		return req1;
	}


	/*
	 * This method will close/update the service request if new Recharge is Done.
	 */
	@Transactional
	@Override
	public ServiceRequest update(Long id) {
		logger.info("Enter ServiceRequestController :: method=update");
		Optional<ServiceRequest> request=requestRepository.findById(id);
		Account account=request.get().getAccount();
		if (!request.isPresent()) {
			throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
		} else {
			//int days=account.getCurrentPack().getDaysValidity();
			LocalDate date=rechargeRepository.PurchesedDate(account.getId());
			//LocalDate date=LocalDate.of(2021, 02, 01);
			if(request.get().getRequestdate().isAfter(date)){
				throw new ResourceNotFoundException(RECHARGE_NOT_FOUND + id);
			}
			else
			try {
				  requestRepository.closeRequest(date);
			}
			catch(Exception e) {
				throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
			}			
		}
		logger.info("Exit ServiceRequestController :: method=update");
		return request.get();
		
		
	}

	/*
	 * This method will delete the ServiceRequest.

	 */
	@Override
	public ServiceRequest deleteRequest(Long id) {
		logger.info("Enter ServiceRequestController :: method=deleteRequest");
		Optional<ServiceRequest> request=requestRepository.findById(id);
		if (!request.isPresent()) {
			throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
		} else {
			try {
				requestRepository.delete(request.get());
			
			}catch(Exception e) {
				throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
			}			
		}
		logger.info("Exit ServiceRequestController :: method=deleteRequest");
		return request.get();
	}
	

	/*
	 * This method Create  Service Request When there is no other Open service Request Request in Account.
	 */
   @Transactional
	@Override
	public ServiceRequest create(Long reqid,Long id) {
	   logger.info("Enter ServiceRequestController :: method=create");
		ServiceRequest req=null;
		LocalDate purchaseDate=rechargeRepository.PurchesedDate(id);
		boolean flag=false;
		Optional<Account> account=accountRepository.findById(id);
		int days=account.get().getCurrentPack().getDaysValidity();	
		if(! account.isPresent()) {
			throw new ResourceNotFoundException(OPERATION_FAILED_CONST + id);
		}else {
		List<ServiceRequest> list=account.get().getRequest();
		for(ServiceRequest a:list) {
		if(a.isStatusOpened()==true)
			flag=true;
		}if (flag == true) {
			throw new ResourceNotFoundException(OPEN_SERVICE_FOUND_CONST + id);
		}
		else  {
	    req=new ServiceRequest();
        
		req.setId(reqid);
		req.setMessage("Service is opened");
		req.setRequestdate(purchaseDate.plusDays(days));
		req.setStatusOpened(true);
		req.setAccount(account.get());
		try {		 
			
			list.add(req);
			account.get().setRequest(list);
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}			
		}}
		logger.info("Exit ServiceRequestController :: method=create");
		return req;
		
   }
   }
