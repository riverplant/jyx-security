package com.river.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
	
	/**
	 * for asyn
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.setDefaultTimeout(1000);
//		configurer.registerCallableInterceptors(interceptors)
//		configurer.registerDeferredResultInterceptors(interceptors)
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
