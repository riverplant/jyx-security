package com.river;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.properties.SecurityProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author riverplant
 *
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {
	
	@Bean
    @ConfigurationProperties(prefix = "river.security")
    public SecurityProperties connectionSettings(){
        return new SecurityProperties();

    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
