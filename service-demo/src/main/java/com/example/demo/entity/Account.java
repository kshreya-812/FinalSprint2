package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="account")
public class Account {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	private Long id;
	
	@Column(name="registerd_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate registerd;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="account",fetch=FetchType.LAZY)
	List<ServiceRequest> request;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="account",fetch=FetchType.LAZY)
    List<Recharge> recharge;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "packid")
	private Pack currentPack;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRegisterd() {
		return registerd;
	}

	@JsonManagedReference
    public List<Recharge> getRecharge() {
		return recharge;
	}

	public void setRecharge(List<Recharge> recharge) {
		this.recharge = recharge;
	}

	public void setRegisterd(LocalDate registerd) {
		this.registerd = registerd;
	}

	@JsonManagedReference
	public List<ServiceRequest> getRequest() {
		return request;
	}

	public void setRequest(List<ServiceRequest> request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pack getCurrentPack() {
		return currentPack;
	}

	public void setCurrentPack(Pack currentPack) {
		this.currentPack = currentPack;
	}
	
	
	
}
