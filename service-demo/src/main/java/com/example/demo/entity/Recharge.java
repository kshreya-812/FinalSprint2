package com.example.demo.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Recharge {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name ="accountid")
	//@PositiveOrZero(message="Price should be Positive Numbers only")
    private Account account;
	
	@PositiveOrZero(message="Amount should be Positive Numbers only")
    private Double amount;
	
	@PositiveOrZero(message="Validity should be Positive Numbers only")
    private Integer daysValidity;
	
    @NotEmpty(message = "PlanDescription name must not be empty")
    private String planDescription;
    
    @NotEmpty(message = "planName name must not be empty")
    private String planName;
    
  //  @Future(message="purchasedDate should be the Future Date.")
    @Column(name="request_date")
	@JsonFormat(pattern = "dd/MM/yyyy")	
    private LocalDate purchasedDate;
    
    @ElementCollection
    private List<String> channels=new ArrayList<>();
    
   // private Pack pack;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonBackReference
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDaysValidity() {
        return daysValidity;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setDaysValidity(Integer daysValidity) {
        this.daysValidity = daysValidity;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
/*
    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
*/
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
