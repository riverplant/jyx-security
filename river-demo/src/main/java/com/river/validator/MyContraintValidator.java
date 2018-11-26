package com.river.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.river.service.UserService;

/**
 * 
 * @author riverplant
 *
 */
public class MyContraintValidator implements ConstraintValidator<MyConstraint, Object> {
	@Autowired
	private UserService userService;

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("MyContraintValidator init...");

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		userService.greetin("riverplant");
		System.out.println(value);
		return false;
	}

}
