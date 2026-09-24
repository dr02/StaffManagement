package com.flm.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StaffServiceGlobalExceptionHandler {
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(404));
	}
	
	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<String> handleStaffNotFoundException(StaffNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(404));
	}
	
	@ExceptionHandler(DoctorUnavailableException.class)
	public ResponseEntity<String> handleDoctorUnavailableException(DoctorUnavailableException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(409));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}
