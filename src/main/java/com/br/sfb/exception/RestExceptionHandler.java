package com.br.sfb.exception;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;
import lombok.Setter;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{


	   @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
	   	 ErrorDetail errorDetail = new ErrorDetail();
		 errorDetail.setTimeStamp(new Date().getTime());
		 errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		 errorDetail.setTitle("Não encontrado");
		 errorDetail.setDetail(rfnException.getMessage());
		 errorDetail.setDeveloperMessage(rfnException.getClass().getName());
	        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	    }
	

	    
	    @ExceptionHandler(CustomException.class)
	    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
	      res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	    }

	    
	   
	   
public static class ErrorDetail {
	 @Getter @Setter
	 private String title;
	 @Getter @Setter
	 private int status;
	 @Getter @Setter
	 private String detail;
	 @Getter @Setter
	 private long timeStamp;
	 @Getter @Setter
	 private String path;
	 @Getter @Setter
	 private String developerMessage;


	}
}
