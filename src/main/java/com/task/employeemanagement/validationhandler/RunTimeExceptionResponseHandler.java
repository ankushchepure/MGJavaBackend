package com.task.employeemanagement.validationhandler;

import java.util.Date;

public class RunTimeExceptionResponseHandler {

private Date timestamp;
private String message;
private String details;
private String url;

  public RunTimeExceptionResponseHandler(Date timestamp, String
message,String details,String url) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
    this.url=url;
  }

public Date getTimestamp() {
return timestamp;
}

public void setTimestamp(Date timestamp) {
this.timestamp = timestamp;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getDetails() {
return details;
}

public void setDetails(String details) {
this.details = details;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}





}
