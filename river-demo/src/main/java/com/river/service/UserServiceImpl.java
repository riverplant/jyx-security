package com.river.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.river.dto.User;
import com.river.dto.UserQueryCondition;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public boolean login(User user) {
		return (user.getUsername().equalsIgnoreCase("admin")&&user.getPassword().equalsIgnoreCase("admin"));
	}

	@Override
	public void greetin(String name) {
		System.out.println("hello,"+name);
		
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> query(UserQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
