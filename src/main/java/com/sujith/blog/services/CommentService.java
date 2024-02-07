package com.sujith.blog.services;

import org.springframework.stereotype.Service;

import com.sujith.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer id);
	
}
