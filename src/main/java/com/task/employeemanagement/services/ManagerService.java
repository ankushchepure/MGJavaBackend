package com.task.employeemanagement.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.employeemanagement.config.ResultSetConstants;
import com.task.employeemanagement.config.WebserviceResultSet;
import com.task.employeemanagement.entity.Manager;
import com.task.employeemanagement.repository.ManagerRepo;
import com.task.employeemanagement.requests.ManagerLoginDto;
import com.task.employeemanagement.requests.ManagerSignupDto;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepo managerRepo;

	public WebserviceResultSet managerSignup(ManagerSignupDto managerSignupDto) {
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		Manager manager = new Manager();
		manager.setFirstName(managerSignupDto.getFirstName());
		manager.setLastName(managerSignupDto.getLastName());
		manager.setAddress(managerSignupDto.getAddress());
		manager.setEmail(managerSignupDto.getEmail());
		manager.setDateOfBirth(managerSignupDto.getDateOfBirth());
		manager.setPassword(managerSignupDto.getPassword());
		manager.setCompany(managerSignupDto.getCompany());
		Manager m1=managerRepo.findByEmail(managerSignupDto.getEmail());
		System.out.print(m1);
		if (m1 ==null) {
			System.out.print(m1);
			try {
				managerRepo.save(manager);
				webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
				webserviceResultSet.setErrorCode("200");
				webserviceResultSet.setErrorMessage("User Added Sucessfully.");
			}catch (Exception e) {
				e.printStackTrace();
				webserviceResultSet.setErrorMessage(e.getMessage());
				webserviceResultSet.setErrorCode("500");
			}
		} else {
			webserviceResultSet.setErrorCode("400");
			webserviceResultSet.setErrorMessage("User already registered.");
		}
		return webserviceResultSet;

	}
	public WebserviceResultSet managerLogin(ManagerLoginDto managerLoginDto) {
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		 
		Manager manager= managerRepo.findByEmailAndPassword(managerLoginDto.getEmail(), managerLoginDto.getPassword());
		
		if(manager!=null) {
			System.out.print(manager.getLastName());
			Map<String, Object> map  = new HashMap<String, Object>();
			map.put("firstName",manager.getFirstName());
			map.put("lastName",manager.getLastName());
			map.put("email",manager.getEmail());
			map.put("company",manager.getCompany());
			map.put("id",manager.getId());
			
			webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
			webserviceResultSet.setErrorCode("200");
			webserviceResultSet.setErrorMessage("User Logged in Sucessfully.");
			webserviceResultSet.addData("data", map);
			
		
		} else {
			webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
			webserviceResultSet.setErrorCode("404");
			webserviceResultSet.setErrorMessage("Invalid Email or password");
		}
		
		return webserviceResultSet;
	}
	
	public Optional<Manager> getAllMamagerEmployeeList(Integer id) {
		
		return managerRepo.findById(id) ;
		
	} 

} 
