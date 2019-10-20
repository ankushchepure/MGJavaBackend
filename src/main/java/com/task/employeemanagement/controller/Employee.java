package com.task.employeemanagement.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.employeemanagement.config.ResultSetConstants;
import com.task.employeemanagement.config.WebserviceResultSet;
import com.task.employeemanagement.requests.EmployeeAddDto;
import com.task.employeemanagement.requests.ManagerSignupDto;
import com.task.employeemanagement.requests.UpdateEmployeeDto;
import com.task.employeemanagement.security.JwtGenerator;
import com.task.employeemanagement.services.EmployeeService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class Employee {
	@Autowired
	JwtGenerator jwtUtil;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value= {"/add"}, method = RequestMethod.POST)
	public WebserviceResultSet employeeAdd(@Valid @RequestBody EmployeeAddDto employeeAddDto, BindingResult result){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		if (result.hasErrors()) {
				 List<FieldError> errors = result.getFieldErrors();
				 map.put("Response", result.getAllErrors());
				 webserviceResultSet.setErrorCode("401");
				 webserviceResultSet.setErrorMessage("Validation Failed");
				 webserviceResultSet.addData("data",result.getAllErrors());
				 return webserviceResultSet;
			 }
		return employeeService.employeeAdd(employeeAddDto);
	}

	@RequestMapping(value= {"/getEmployeeList"}, method = RequestMethod.GET)
	public WebserviceResultSet getAllEmployee(@Valid @RequestParam(name = "managerid") Integer managerid){
		List<com.task.employeemanagement.entity.Employee> employeeList= employeeService.getAllEmployee(managerid);
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		webserviceResultSet.setErrorCode("200");
		webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
		 webserviceResultSet.setErrorMessage("data fetched");
		 webserviceResultSet.addData("data",employeeList);
		 return webserviceResultSet;

	}

	@RequestMapping(value= {"/getEmployeeDetails"}, method = RequestMethod.GET)
	public WebserviceResultSet getEmployeeDetails(@RequestParam(name = "managerid") Integer managerid,@RequestParam(name = "empid") Integer empid){
		com.task.employeemanagement.entity.Employee employee= employeeService.getEmpDetails(managerid,empid);
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		webserviceResultSet.setErrorCode("200");
		webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
		 webserviceResultSet.setErrorMessage("data fetched");
		 webserviceResultSet.addData("data",employee);
		 return webserviceResultSet;

	}
	@RequestMapping(value= {"/deleteEmployee"}, method = RequestMethod.GET)
	public WebserviceResultSet deleteEmp(@RequestParam(name = "managerid") Integer managerid,@RequestParam(name = "empid") Integer empid){

		 return employeeService.deleteEmp(managerid,empid);

	}
	@RequestMapping(value= {"/update"}, method = RequestMethod.POST)
	public WebserviceResultSet employeeUpdate(@Valid @RequestBody UpdateEmployeeDto updateEmployeeDto, BindingResult result){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		if (result.hasErrors()) {
				 List<FieldError> errors = result.getFieldErrors();
				 map.put("Response", result.getAllErrors());
				 webserviceResultSet.setErrorCode("401");
				 webserviceResultSet.setErrorMessage("Validation Failed");
				 webserviceResultSet.addData("data",result.getAllErrors());
				 return webserviceResultSet;
			 }
		return employeeService.employeeUpdate(updateEmployeeDto);
	}

	@RequestMapping(value= {"/gettest"}, method = RequestMethod.GET)
	public WebserviceResultSet employeeAdd(@RequestParam int managerid){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
			webserviceResultSet.setErrorMessage("success");
		return webserviceResultSet;
	}

	@RequestMapping(value= {"/posttest"}, method = RequestMethod.POST)
	public WebserviceResultSet postEmpl(@RequestBody com.task.employeemanagement.entity.Manager man){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
			webserviceResultSet.setErrorMessage("success");

			webserviceResultSet.addData("empl", jwtUtil.generate(man));;
		return webserviceResultSet;
	}
}
