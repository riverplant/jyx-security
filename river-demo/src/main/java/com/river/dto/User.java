package com.river.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class User {
	public interface UserSimpleView{};//simple
	public interface UserDetailView extends UserSimpleView {};//detail

	@JsonView(UserSimpleView.class)
	private String username;
	
	@JsonView(UserDetailView.class)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
