package com.river.core.properties;

import com.river.core.enums.LoginType;

public class BrowserProperties {
	
	private String loginPage = "/login.html";//default login page
	
	private LoginType loginType = LoginType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	

}
