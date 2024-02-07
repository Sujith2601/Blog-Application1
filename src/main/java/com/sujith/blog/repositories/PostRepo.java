package com.sujith.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujith.blog.entities.Category;
import com.sujith.blog.entities.Post;
import com.sujith.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
