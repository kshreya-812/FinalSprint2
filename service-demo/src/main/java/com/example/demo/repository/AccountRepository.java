package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

	//@Query("Select new com.example.demo.dto.ServiceRequestDto(a.id,s.id) From Address a Join a.ServiceRequest s")
	//	public List<ServiceRequestDto> getJoin();

}
