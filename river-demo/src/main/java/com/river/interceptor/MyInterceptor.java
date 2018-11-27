package com.river.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author riverplant
 *
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		 boolean flag = true;
	       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(multipartResolver.isMultipart(request)){ 
	            String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
				String requestToken = CSRFTokenManager.getTokenFromRequest(request);
				if (!sessionToken.equals(requestToken)) {
	                   flag = false;
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				}        	
			}
			return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex!=null)System.out.println(ex.getMessage());

	}

}
