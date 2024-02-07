package com.sujith.blog.services;

import java.util.List;

import com.sujith.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	UserDto getUser(Integer id);
	List<UserDto> getAllUsers();
	void deleteUser(Integer id);
	
}
