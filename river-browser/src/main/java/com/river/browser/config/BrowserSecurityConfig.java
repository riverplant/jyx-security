package com.river.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.river.browser.authentication.MyAhthenticationFaildHandler;
import com.river.browser.authentication.MyAuthenticationSuccessHandler;
import com.river.core.properties.SecurityProperties;

/**
 * 
 * @author riverplant
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private MyAuthenticationSuccessHandler riverAuthenticationSuccessHandler;
	
	@Autowired
	private MyAhthenticationFaildHandler riverAhthenticationFaildHandler;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.httpBasic()
		http.formLogin()
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/loginForm")
		.successHandler(riverAuthenticationSuccessHandler)
		.failureHandler(riverAhthenticationFaildHandler)
		    .and()
		    .authorizeRequests()
		    .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()
		    .antMatchers("/js/**","/css/**","/images/*","/fonts/**","/**/*.png","/**/*.jpg").permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .csrf().disable();
	}
	
	
}
