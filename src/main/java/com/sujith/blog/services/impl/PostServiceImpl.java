package com.sujith.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sujith.blog.entities.Category;
import com.sujith.blog.entities.Post;
import com.sujith.blog.entities.User;
import com.sujith.blog.exceptions.ResourceNotFoundException;
import com.sujith.blog.payloads.PostDto;
import com.sujith.blog.payloads.PostResponse;
import com.sujith.blog.repositories.CategoryRepo;
import com.sujith.blog.repositories.PostRepo;
import com.sujith.blog.repositories.UserRepo;
import com.sujith.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setCategory(category);
		post.setDate(new Date());
		post.setUser(user);
		post.setImageUrl("default.png");
		
		Post savedPost = this.postRepo.save(post);
		PostDto postDto1 = this.modelMapper.map(savedPost, PostDto.class);
		
		return postDto1;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		
		Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "postId", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageUrl(postDto.getImageUrl());
		
		Post updatedPost = this.postRepo.save(post);
		PostDto postDto1 = this.modelMapper.map(updatedPost, PostDto.class);
		
		return postDto1;
	}

	@Override
	public PostDto getPost(Integer id) {
		
		Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "postId", id));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse(postDtos, 
				pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalElements(), 
				pagePosts.getTotalPages(), pagePosts.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("User", "userId", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream()
				.map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public void deletePost(Integer id) {
		
		Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "postId", id));
		this.postRepo.delete(post);
	}

}
