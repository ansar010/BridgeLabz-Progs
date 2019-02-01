package com.bridgelabz.HibernateDemoNew;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EmployeeTable
{
	@Id
	private int id;
	
	private String name;
	
	private long sal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSal() {
		return sal;
	}
	public void setSal(Long salary) {
		this.sal = salary;
	}
}
