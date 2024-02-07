package com.sujith.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujith.blog.payloads.ApiResponse;
import com.sujith.blog.payloads.CategoryDto;
import com.sujith.blog.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> postCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id) {
		CategoryDto retrievedCategory = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(retrievedCategory, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategories(), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deletedCategory(@PathVariable Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}
	
}
