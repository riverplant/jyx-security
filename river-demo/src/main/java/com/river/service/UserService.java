package com.river.service;

import java.util.List;

import com.river.dto.User;
import com.river.dto.UserQueryCondition;

public interface UserService {
	
	boolean login(User user);
	
	void greetin(String name);
	
	User createUser(User user);
	 
	User updateUser(User user);
	
	Boolean deleteUser(String id);
	
	List<User> query(UserQueryCondition condition);
	
	 User getInfo(String id);

}
