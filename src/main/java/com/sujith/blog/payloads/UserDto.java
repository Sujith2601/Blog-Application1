package com.sujith.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty(message = "Enter a valid name")
	private String name;
	@Email(message = "Enter a valid email address")
	private String email;
	@NotEmpty(message = "Enter a valid password")
	private String password;
	@NotEmpty(message = "Say something about you!!")
	private String about;
	
}
