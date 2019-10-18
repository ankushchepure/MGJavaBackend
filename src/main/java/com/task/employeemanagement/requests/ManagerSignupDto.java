package com.task.employeemanagement.requests;

import java.sql.Date;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ManagerSignupDto {

  @NotEmpty(message = "firstName can not be empty")
	private String firstName;

  @NotEmpty(message = "lastName can not be empty")
	private String lastName;

  @NotNull(message = "dateOfBirth can not be null")
	private Date dateOfBirth;

	@NotEmpty(message = "address can not be empty")
	private String address;

  @NotEmpty(message = "email can not be empty")
	private String email;

  @NotEmpty(message = "company can not be empty")
	private String company;


  @NotEmpty(message = "password can not be empty")
	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
