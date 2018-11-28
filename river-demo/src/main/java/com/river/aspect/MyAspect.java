package com.river.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author riverplant
 *
 */
@Aspect
@Component
public class MyAspect {
	/**
	 * 
	 * @param pjp : handler
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.river.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for(Object arg:args) {
			System.out.println(arg);
		}
		Object obj = pjp.proceed();
		return obj;
	}

}
