package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table (name="Pack_tbl")
public class Pack {
	
	@Id
	@Column(name="packid")
    private long id;
	
	@PositiveOrZero(message="Cost should be Positive Numbers only")
	private Double cost;
	
	@NotNull(message="Days Validity Should not be blank")
    private Integer daysValidity;
    
    @NotEmpty(message = "Pack Description must not be empty")
    private String description;
    
    @NotEmpty(message = "Pack Plan name must not be empty")
    private String planName;
    
    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Pack_Channels_tbl",joinColumns=@JoinColumn(name="Pack_id"))
	@Column(name = "Channel_id")
    private List<String> channels;

  /* @OneToMany(cascade=CascadeType.ALL,mappedBy="currentPack",fetch=FetchType.LAZY)
	List<Account> account;
	
    public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}*/

	public long getId() {
        return id;
    }

    public void setId(long i) {
        this.id = i;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getDaysValidity() {
        return daysValidity;
    }

    public void setDaysValidity(Integer daysValidity) {
        this.daysValidity = daysValidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }


}