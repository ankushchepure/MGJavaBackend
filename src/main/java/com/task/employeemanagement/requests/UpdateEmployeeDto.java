package com.task.employeemanagement.requests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
public class UpdateEmployeeDto {
	
	@NotEmpty(message = "firstName can not be empty")
	private String firstName;

	@NotEmpty(message = "lastName can not be empty")
	private String lastName;

	@NotNull(message = "dateOfBirth can not be null")
	private Date dateOfBirth;

	@NotEmpty(message = "address can not be empty")
	private String address;

	@NotEmpty(message = "mobileNo can not be empty")
	private String mobileNo;

	@NotNull(message = "managerid can not be null")
	private Integer managerid;
	
	@NotNull(message = "empid can not be null")
	private Integer empId;

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

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerId) {
		this.managerid = managerId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

}
