package com.sujith.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujith.blog.payloads.ApiResponse;
import com.sujith.blog.payloads.UserDto;
import com.sujith.blog.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Post_api
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	//Update_api
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, 
			@Valid @RequestBody UserDto userDto) {
		UserDto updatedUser = this.userService.updateUser(userDto, id);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	//Get_api
	@GetMapping("/getall")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}
	
	//Getall_api
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id) {
		UserDto retrievedUser = this.userService.getUser(id);
		return new ResponseEntity<UserDto>(retrievedUser, HttpStatus.OK);
	}
	
	//Delete_api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("User Deleted Succesfully", true), HttpStatus.OK);
	}
	
}
