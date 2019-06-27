package com.hcl.ox.exceptionhandller;

import java.util.Date;

public class ErrorResponse {
	
	private Date date;
	private String message;
	public ErrorResponse(Date date, String message) {
		super();
		this.date = date;
		this.message = message;
	}
	

}
