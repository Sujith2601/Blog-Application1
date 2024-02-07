package com.sujith.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sujith.blog.payloads.ApiResponse;
import com.sujith.blog.payloads.PostDto;
import com.sujith.blog.payloads.PostResponse;
import com.sujith.blog.services.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, 
			@PathVariable Integer userId, 
			@PathVariable Integer categoryId) {
		
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/get")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		
		List<PostDto> postDtos = this.postService.getAllPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/get")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		
		List<PostDto> postDtos = this.postService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize, 
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy) {
		
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		
		PostDto postDto = this.postService.getPost(id);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword) {
		List<PostDto> postDtos = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> putPost(@PathVariable Integer id, @RequestBody PostDto postDto) {
		
		PostDto updatedPost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
		
		this.postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}
	
}
