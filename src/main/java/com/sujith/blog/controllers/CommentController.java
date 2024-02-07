package com.sujith.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujith.blog.payloads.ApiResponse;
import com.sujith.blog.payloads.CommentDto;
import com.sujith.blog.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, 
			@PathVariable Integer postId) {
		
		CommentDto savedComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(savedComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id) {
		
		this.commentService.deleteComment(id);
		return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
	}
}
