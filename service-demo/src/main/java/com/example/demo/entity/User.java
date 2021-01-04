package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table (name="user_tbl")
public class User {

	
	@Id
	private long id;

	@NotEmpty(message = "User name must not be empty")
    private String userName;

	@NotEmpty(message = "User First name must not be empty")
    private String firstName;

	@NotEmpty(message = "User Last name must not be empty")
    private String lastName;

	@NotEmpty(message = "User password must not be empty")
    private String password;
    
	@NotEmpty(message = "User role must not be empty")
    private String role;
   
    @OneToOne(mappedBy="user",fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="accountId")
    private Account account;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}