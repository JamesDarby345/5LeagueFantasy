package com.example.LeagueFantasy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class FiveLeagueFantasyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FiveLeagueFantasyException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFiveLeagueFantasyException(FiveLeagueFantasyException exception) {
      ErrorResponse error = new ErrorResponse();
      error.setMessage(exception.getMessage());
      error.setStatus(exception.getStatus());
		  return new ResponseEntity<>(error, exception.getStatus());
	}

  public class ErrorResponse {
    
    private String message;
    private HttpStatus status;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
}
