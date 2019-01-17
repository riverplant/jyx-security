package com.river.core.MultiThreads.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("com.river.core.MultiThreads.spring")
@EnableAsync//支持异步
public class Config {

}
