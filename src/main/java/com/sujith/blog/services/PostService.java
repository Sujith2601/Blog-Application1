package com.sujith.blog.services;

import java.util.List;

import com.sujith.blog.payloads.PostDto;
import com.sujith.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer id);
	PostDto getPost(Integer id);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	List<PostDto> getAllPostsByUser(Integer userId);
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	List<PostDto> searchPost(String keyword);
	void deletePost(Integer id);
	
}
