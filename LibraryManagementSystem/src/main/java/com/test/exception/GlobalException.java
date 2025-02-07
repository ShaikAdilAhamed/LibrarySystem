package com.test.exception;


import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(exception = ResourseNotFoundException.class)
	public ResponseEntity< CustomExceptionResponse>resourceNotFoundException(ResourseNotFoundException me,WebRequest we) {
		
		CustomExceptionResponse response= new CustomExceptionResponse();
		response.setDate(new Date());
		response.setDetails(we.getDescription(false));
		response.setMessage(me.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	@ExceptionHandler(exception = AccessDeniedException.class)
	public ResponseEntity< CustomExceptionResponse>resourceNotFoundException(AccessDeniedException me,WebRequest we) {
		
		CustomExceptionResponse response= new CustomExceptionResponse();
		response.setDate(new Date());
		response.setDetails(we.getDescription(false));
		response.setMessage(me.getMessage());
		return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity< CustomExceptionResponse>resourceNotFoundException(Exception exception,WebRequest we) {
		
		CustomExceptionResponse errorDetails= new CustomExceptionResponse();
		errorDetails.setDate(new Date());
		errorDetails.setDetails(we.getDescription(false));
		errorDetails.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String,String>errors= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
			
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
