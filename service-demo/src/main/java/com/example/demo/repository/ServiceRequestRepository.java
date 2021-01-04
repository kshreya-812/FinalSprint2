package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {

	
	@Query("Select s From ServiceRequest s Join s.account a Where s.StatusOpened=true")
	public List<ServiceRequest> getJoin();
	
	@Modifying
	@Query("Update ServiceRequest s set s.StatusOpened=false,s.message='Service is Closed' where s.requestdate < :date")
	public void closeRequest(@Param("date") LocalDate date);
}

