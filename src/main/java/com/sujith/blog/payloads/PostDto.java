package com.sujith.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	private String title;
	private String content;
	private String imageUrl;
	private Date date;
	private UserDto user;
	private CategoryDto category;
	private List<CommentDto> comments = new ArrayList<>();
	
}
