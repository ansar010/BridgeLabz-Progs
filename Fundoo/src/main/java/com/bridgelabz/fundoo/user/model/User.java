package com.bridgelabz.fundoo.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User 
{
	@Column(name = "first_name")
	@NotEmpty(message="please provide your first name")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message="please provide your last name")
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@Column(name = "mobileNumber", nullable = false, unique = true)
	@NotEmpty(message = "Please provide mobile Number")
	@Pattern(regexp ="[0-9]{10}", message = "provide valid mobile number")
	private long mobileNumber;
	
	@Column(name="password")
	@Transient
	private String password;
	
	public User() {}
	
}
