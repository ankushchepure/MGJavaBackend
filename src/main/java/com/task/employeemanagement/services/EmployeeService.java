package com.task.employeemanagement.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.employeemanagement.config.ResultSetConstants;
import com.task.employeemanagement.config.WebserviceResultSet;
import com.task.employeemanagement.entity.Manager;
import com.task.employeemanagement.entity.Employee;
import com.task.employeemanagement.repository.EmployeeRepo;
import com.task.employeemanagement.repository.ManagerRepo;
import com.task.employeemanagement.requests.EmployeeAddDto;

import com.task.employeemanagement.requests.UpdateEmployeeDto;


@Service
public class EmployeeService {
      
	@Autowired
	private ManagerRepo managerRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public WebserviceResultSet employeeAdd(EmployeeAddDto employeeAddDto) {
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		Manager manager = managerRepo.getOne(employeeAddDto.getManagerId());
		if(manager !=null) {
			Employee employee=new Employee();
		    employee.setFirstName(employeeAddDto.getFirstName());
		    employee.setLastName(employeeAddDto.getLastName());
		    employee.setAddress(employeeAddDto.getAddress());
		    employee.setDateOfBirth(employeeAddDto.getDateOfBirth());
		    employee.setMobileNo(employeeAddDto.getMobileNo());
		    employee.setManager(manager);
		    try {
		    	employeeRepo.save(employee);
				webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
				webserviceResultSet.setErrorCode("200");
				webserviceResultSet.setErrorMessage("Employee Added Sucessfully.");
			}catch (Exception e) {
				e.printStackTrace();
				webserviceResultSet.setErrorMessage(e.getMessage());
				webserviceResultSet.setErrorCode("500");
			}
		    
		} else {
			webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
         	webserviceResultSet.setErrorCode("404");
			webserviceResultSet.setErrorMessage("Invalid Manager");
		}
	    
		return webserviceResultSet;

	}
	public List<Employee> getAllEmployee(Integer managerid){
		return employeeRepo.findByManagerIdOrderByFirstNameDesc(managerid);
	}
	public Employee getEmpDetails(@Valid Integer managerid, Integer empid) {
		
		return employeeRepo.findByManagerIdAndEmpId(managerid,empid);
	}
	
public WebserviceResultSet deleteEmp(Integer managerid, Integer empid) {
		
	WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
	Employee employee = employeeRepo.findByManagerIdAndEmpId(managerid,empid);
	if(employee !=null) {
		 try {
		employeeRepo.deleteById(empid);
		webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
		webserviceResultSet.setErrorCode("200");
		webserviceResultSet.setErrorMessage("Employee Deleted Sucessfully.");
		
		 } catch (Exception e) {
				e.printStackTrace();
				webserviceResultSet.setErrorMessage(e.getMessage());
				webserviceResultSet.setErrorCode("500");
			}
	}
	else {
		webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
     	webserviceResultSet.setErrorCode("404");
		webserviceResultSet.setErrorMessage("Invalid Manager or empid");
	}
    
	return webserviceResultSet;
	}

	public WebserviceResultSet employeeUpdate(UpdateEmployeeDto updateEmployeeDto) {
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		Employee employee = employeeRepo.findByManagerIdAndEmpId(updateEmployeeDto.getManagerId(),updateEmployeeDto.getEmpId());
		if(employee !=null) {
		    employee.setFirstName(updateEmployeeDto.getFirstName());
		    employee.setLastName(updateEmployeeDto.getLastName());
		    employee.setAddress(updateEmployeeDto.getAddress());
		    employee.setDateOfBirth(updateEmployeeDto.getDateOfBirth());
		    employee.setMobileNo(updateEmployeeDto.getMobileNo());
		    try {
		    	employeeRepo.save(employee);
				webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
				webserviceResultSet.setErrorCode("200");
				webserviceResultSet.setErrorMessage("Employee Updated Sucessfully.");
			}catch (Exception e) {
				e.printStackTrace();
				webserviceResultSet.setErrorMessage(e.getMessage());
				webserviceResultSet.setErrorCode("500");
			}
		    
		} else {
			webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
         	webserviceResultSet.setErrorCode("404");
			webserviceResultSet.setErrorMessage("Invalid Manager or empid");
		}
	    
		return webserviceResultSet;

	}
	
}
