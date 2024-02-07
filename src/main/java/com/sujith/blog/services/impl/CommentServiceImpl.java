package com.sujith.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujith.blog.entities.Comment;
import com.sujith.blog.entities.Post;
import com.sujith.blog.exceptions.ResourceNotFoundException;
import com.sujith.blog.payloads.CommentDto;
import com.sujith.blog.repositories.CommentRepo;
import com.sujith.blog.repositories.PostRepo;
import com.sujith.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		CommentDto commentDto1 = this.modelMapper.map(savedComment, CommentDto.class);
		return commentDto1;
	}

	@Override
	public void deleteComment(Integer id) {
		
		Comment comment = this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", id));
		this.commentRepo.delete(comment);
	}

}
