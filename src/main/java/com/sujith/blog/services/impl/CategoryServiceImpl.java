package com.sujith.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujith.blog.entities.Category;
import com.sujith.blog.exceptions.ResourceNotFoundException;
import com.sujith.blog.payloads.CategoryDto;
import com.sujith.blog.repositories.CategoryRepo;
import com.sujith.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(category);
		CategoryDto categoryDto1 = this.modelMapper.map(updatedCategory, CategoryDto.class);
		
		return categoryDto1;
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream()
				.map(category->modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		this.categoryRepo.delete(category);
	}

}
