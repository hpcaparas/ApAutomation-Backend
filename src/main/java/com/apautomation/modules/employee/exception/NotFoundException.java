package com.apautomation.modules.employee.exception;

public class NotFoundException extends RuntimeException{
	
	public NotFoundException(Long id, String dataType) {
		super("Could not found the "+dataType+" with id "+ id);
	}

}
