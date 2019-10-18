package com.task.employeemanagement.requests;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class ManagerLoginDto {
  
	@NotEmpty(message = "email can not be empty")
	private String email;
 
	  @NotEmpty(message = "password can not be empty")
		private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
