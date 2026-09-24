package com.flm.staff.exception;

public class StaffNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7091862168279835454L;

	public StaffNotFoundException(String message) {
		super(message);
	}
}
