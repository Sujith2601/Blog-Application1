package com.sujith.blog.services;

import java.util.List;

import com.sujith.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	CategoryDto getCategory(Integer id);
	List<CategoryDto> getAllCategories();
	void deleteCategory(Integer id);
	
}
