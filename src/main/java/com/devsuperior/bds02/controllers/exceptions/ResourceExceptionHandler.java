package com.devsuperior.bds02.controllers.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		var err = new StandardError(LocalDateTime.now(), status.value(), "Resource Not Found" , e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DatabaseException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		var err = new StandardError(LocalDateTime.now(), status.value(), "Data integrity violation" , e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
