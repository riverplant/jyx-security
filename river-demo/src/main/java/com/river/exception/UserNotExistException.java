package com.river.exception;

public class UserNotExistException extends RuntimeException{
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	
	public UserNotExistException(String id) {
		
		super("user not exist");
		this.id = id;
	}

}
