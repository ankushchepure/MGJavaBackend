package com.task.employeemanagement.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee_master")
public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="empid")
private int empId;

@Column(name="firstname")
private String firstName;

@Column(name="lastname")
private String lastName;

@Column(name="dob")
private Date dateOfBirth;

private String address;

@Column(name="mobile")
private String mobileNo;

@ManyToOne
@JoinColumn(name = "managerid")
private Manager manager;

public int getEmpId() {
	return empId;
}

public void setEmpId(int empId) {
	this.empId = empId;
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

public Date getDateOfBirth() {
	return dateOfBirth;
}

public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getMobileNo() {
	return mobileNo;
}

public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

@JsonIgnore
public Manager getManager() {
	return manager;
}
@JsonIgnore
public void setManager(Manager manager) {
	this.manager = manager;
}




}
