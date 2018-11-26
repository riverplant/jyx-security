package com.river.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonView;
import com.river.validator.MyConstraint;

public class User {
	public interface UserSimpleView {
	};// simple

	public interface UserDetailView extends UserSimpleView {
	};// detail

	@MyConstraint(message = "this is myConstraint")
	private String username;
	
	
	@JsonView(UserSimpleView.class)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Email
	private String email;

	@JsonView(UserSimpleView.class)
	private String id;

	@Past
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	private String password;

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
