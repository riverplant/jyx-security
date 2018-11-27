package com.river.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.river.filter.XssFilter;
import com.river.interceptor.MyInterceptor;
/**
 * 
 * @author riverplant
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private MyInterceptor csrfInterceptor ;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(csrfInterceptor);
	}
//    @Bean
//	public FilterRegistrationBean registFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		
//		XssFilter xssFilter = new XssFilter();
//		//registrationFilter
//		registrationBean.setFilter(xssFilter);
//		List<String> urls = new ArrayList<String>();
//		urls.add("/*");
//		registrationBean.setUrlPatterns(urls);
//		return registrationBean;
//	}
	
	
	
}
