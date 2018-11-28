package com.river.web.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.river.dto.User;
import com.river.dto.UserQueryCondition;
import com.river.service.UserService;

/**
 * 
 * @author riverplant
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
	private UserService userService;
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(UserQueryCondition condition) {
        
		return userService.query(condition);
	}

	/**
	 * 
	 * @param id:only
	 *            for Integer
	 * @return
	 */
	// @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
        System.out.println("getInfo.......");
		//throw new UserNotExistException(id);
        
		return userService.getInfo(id);
	}

	@PostMapping
	public User createUser(@Valid @RequestBody User user, BindingResult erros) {
		if (erros.hasErrors()) {
			erros.getAllErrors().stream().forEach(error ->{
				FieldError fieldError = (FieldError) error;
				String message = fieldError.getField()+":"+" "+error.getDefaultMessage();
				System.out.println(message);
			});
		}
		user.setId(UUID.randomUUID().toString());
		return userService.createUser(user);
	}
	
	@PutMapping("/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User user, BindingResult erros) {
		if (erros.hasErrors()) {
			erros.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{id:\\d+}")
	public Boolean deleteUser( @PathVariable String id) {
		System.out.println("delete..."+id);
		return userService.deleteUser(id);
	}
}
