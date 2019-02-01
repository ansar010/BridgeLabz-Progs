package com.bridgelabz.hibernateDemo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//	persistence class/POJO class/bean class 
//	it used to store data

@Entity  
@Table
public class Employee 
{
	@Id   
	//@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;    
	//@Column(name="firtName")
	private String firstName;
	//@Column(name="lastName")
	private String lastName;    

	public int getId() {    
		return id;    
	}    
	public void setId(int id) {    
		this.id = id;    
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
}   

