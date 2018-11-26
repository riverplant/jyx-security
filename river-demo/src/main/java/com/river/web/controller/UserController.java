package com.river.web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.river.dto.User;
/**
 * 
 * @author riverplant
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	//@RequestMapping(value = "/user",method = RequestMethod.GET)
	@GetMapping
	@JsonView(User.UserSimpleView.class)
    public List<User>query(){
		
		return null;
	}
	
	/**
	 * 
	 * @param id:only for Integer
	 * @return
	 */
	//@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
	@GetMapping("/{id:\\\\d+}")
	@JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id){
		
		return null;
	}
}
