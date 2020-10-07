package com.bank.exception;


public class ErrorResponse {
	public ErrorResponse(String type, String message) {
		super();
		this.message = message;
		this.type = type;
	}

	private String message;
	private String type;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
