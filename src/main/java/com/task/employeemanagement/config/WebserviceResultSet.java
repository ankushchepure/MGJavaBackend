package com.task.employeemanagement.config;

import java.util.Map;

import com.task.employeemanagement.entity.Manager;

import java.util.HashMap;
public class WebserviceResultSet {


	private String errorMessage;

	private String errorCode;

	private Map<String, Object> data = new HashMap<String,Object>();

	private boolean status=false;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void addData(String key, Object value) {
		this.data.put(key, value);
	}

}
