package com.river.browser.listener;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.river.browser.utils.IpUtils;
@Component
public class AutenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		 String ip = IpUtils.getIp(request);
		 logger.info(ip+"loging faild...");

	}

}
