package com.sujith.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujith.blog.entities.User;
import com.sujith.blog.exceptions.*;
import com.sujith.blog.payloads.UserDto;
import com.sujith.blog.repositories.UserRepo;
import com.sujith.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		
		User user = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", " id ", id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUser(Integer id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", " id ", id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream()
				.map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		
		User user = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", " id ", id));
		this.userRepo.delete(user);
		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

}
