package com.river.core.properties;

/**
 * 
 * @author riverplant
 *
 */
//@Component
//@ConfigurationProperties(prefix = "river.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	
	
}
