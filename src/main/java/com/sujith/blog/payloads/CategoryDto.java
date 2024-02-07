package com.sujith.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	@NotEmpty(message = "Please enter Category name")
	private String categoryName;
	@NotEmpty(message = "Say something about the Category")
	private String categoryDescription;
	
}
