package com.webapp.dto;

import java.util.List;

import com.webapp.entity.Registration;

public class ReadRegistrationDto {
	
	private List<Registration> registration;
	private String message;
	public List<Registration> getRegistration() {
		return registration;
	}
	public void setRegistration(List<Registration> registration) {
		this.registration = registration;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
