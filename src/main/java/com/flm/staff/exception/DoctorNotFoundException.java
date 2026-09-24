package com.flm.staff.exception;

public class DoctorNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6267037643738425663L;

	public DoctorNotFoundException(String message) {
		super(message);
	}
}
