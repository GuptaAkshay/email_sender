package com.akshdev.email_sender.api;

public class Message {

	private int code;
	private String type;
	private String message;

	public Message(int errorCode, String type, String errorMessage) {
		
		this.code = errorCode;
		this.type = type;
		this.message = errorMessage;
	}
	public int getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}	
	
}
