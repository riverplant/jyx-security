package com.river.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author riverplant
 *
 */
public class CSRFTokenManager {
	public static final String CSRF_PARAM_NAME = "MyCSRFToken";
	/**
	 * The location on the session which stores the token
	 */
	private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";
	
	public static String getTokenForSession (HttpSession session) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		synchronized (session) {
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (null==token) {
				token=UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}
	
	/**
	 * Extracts the token value from the session
	 * @param request
	 * @return
	 */
	public static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}

	private CSRFTokenManager() {};
	
	
}
