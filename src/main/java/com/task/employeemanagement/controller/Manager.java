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
import com.task.employeemanagement.requests.ManagerLoginDto;
import com.task.employeemanagement.requests.ManagerSignupDto;
import com.task.employeemanagement.security.JwtGenerator;
import com.task.employeemanagement.services.ManagerService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/manager")
class Manager {

	@Autowired
	private JwtGenerator jwtUtils;

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value= {"/test"}, method = RequestMethod.GET)
	public Map<String, Object> testService(){
		Map<String, Object> response = new HashMap<String, Object>();
		com.task.employeemanagement.entity.Manager man = new com.task.employeemanagement.entity.Manager();
		response.put("jwtToken", jwtUtils.generate(man));
		return response;
	}

	@RequestMapping(value= {"/signup"}, method = RequestMethod.POST)
	public WebserviceResultSet signup(@Valid @RequestBody ManagerSignupDto managerSignupDto, BindingResult result){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		if (result.hasErrors()) {
				 List<FieldError> errors = result.getFieldErrors();
				 map.put("Response", result.getAllErrors());
				 webserviceResultSet.setErrorCode("401");
				 webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
				 webserviceResultSet.setErrorMessage("Validation Failed");
				 webserviceResultSet.addData("data",result.getAllErrors());
				 return webserviceResultSet;
			 }
		return managerService.managerSignup(managerSignupDto);
	}
	@RequestMapping(value= {"/login"}, method = RequestMethod.POST)
	public WebserviceResultSet login(@Valid @RequestBody ManagerLoginDto managerLoginDto, BindingResult result){
		Map<String, Object> map  = new HashMap<String, Object>();
			WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		if (result.hasErrors()) {
				 List<FieldError> errors = result.getFieldErrors();
				 map.put("Response", result.getAllErrors());
				 webserviceResultSet.setErrorCode("401");
				 webserviceResultSet.setStatus(ResultSetConstants.STATUS_FAIL);
				 webserviceResultSet.setErrorMessage("Validation Failed");
				 webserviceResultSet.addData("data",result.getAllErrors());
				 return webserviceResultSet;
			 }
		return managerService.managerLogin(managerLoginDto);
	}

	@RequestMapping(value= {"/getAllEmployee"}, method = RequestMethod.GET)
	public WebserviceResultSet getAllEmployee(@Valid @RequestParam(name = "id") Integer id){
		Optional<com.task.employeemanagement.entity.Manager> manager= managerService.getAllMamagerEmployeeList(id);
		WebserviceResultSet webserviceResultSet = new WebserviceResultSet();
		webserviceResultSet.setErrorCode("200");
		webserviceResultSet.setStatus(ResultSetConstants.STATUS_OK);
		 webserviceResultSet.setErrorMessage("data fetched");
		 webserviceResultSet.addData("data",manager);

		 return webserviceResultSet;

	}


}
