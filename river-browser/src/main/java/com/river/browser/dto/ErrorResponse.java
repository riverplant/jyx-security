package com.river.browser.dto;
/**
 * 
 * @author riverplant
 *
 */
public class ErrorResponse {

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public ErrorResponse(Object content) {
		super();
		this.content = content;
	}
	
}
