package com.river.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.core.enums.LoginType;
import com.river.core.properties.SecurityProperties;

/**
 * login Success
 * 
 * @author riverplant
 *
 */
@Component("riverAuthenticationSuccessHandler")
// public class MyAuthenticationSuccessHandler implements
// AuthenticationSuccessHandler {
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	SecurityProperties securityProperties;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info(authentication.getPrincipal() + "loging success...");
		if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {// json
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);// redirect
		}

		/**
		 * { "authorities": [ { "authority": "admin" } ], "details": { "remoteAddress":
		 * "0:0:0:0:0:0:0:1", "sessionId": "98BD7ABBB1D69C57C55EAA02A007D9DE" },
		 * "authenticated": true, "principal": { "password": null, "username":
		 * "liuyin2003", "authorities": [ { "authority": "admin" } ],
		 * "accountNonExpired": true, "accountNonLocked": true, "credentialsNonExpired":
		 * true, "enabled": true }, "credentials": null, "name": "liuyin2003" }
		 */

	}

}
