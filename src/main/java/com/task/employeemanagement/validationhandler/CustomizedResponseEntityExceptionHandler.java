package com.task.employeemanagement.validationhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends
ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public final ResponseEntity<RunTimeExceptionResponseHandler>
handleUserNotFoundException(Exception ex, WebRequest request) {

RunTimeExceptionResponseHandler errorDetails = new
RunTimeExceptionResponseHandler(new Date(),
ex.getMessage(),
ex.getCause().getCause().getLocalizedMessage(),
request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }


}
