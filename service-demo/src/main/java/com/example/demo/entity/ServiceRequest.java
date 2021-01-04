package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="servicerequest")
public class ServiceRequest {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "message must not be empty")
	private String message;
	private boolean StatusOpened;
	
	@Column(name="request_date")
	@JsonFormat(pattern = "dd/MM/yyyy")	
	private LocalDate requestdate;
	
	
	@ManyToOne
	@JoinColumn(name ="accountid")
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(LocalDate requestdate) {
		this.requestdate = requestdate;
	}

	@JsonBackReference
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isStatusOpened() {
		return StatusOpened;
	}

	public void setStatusOpened(boolean statusOpened) {
		StatusOpened = statusOpened;
	}
	
	
	
}
