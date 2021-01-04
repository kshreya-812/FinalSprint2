package com.example.demo.service;

import com.example.demo.entity.ServiceRequest;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Account;

public interface ServiceRequestService {
	public ServiceRequest create(Long reqid,Long id);
	public ServiceRequest update(Long id);
    public List<ServiceRequest> getAllOpenServiceRequest();
	public ServiceRequest findServiceRequestById(Long id);
	public ServiceRequest deleteRequest(Long id);

}
