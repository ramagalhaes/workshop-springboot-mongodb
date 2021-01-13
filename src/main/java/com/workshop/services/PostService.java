package com.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.domain.Post;
import com.workshop.domain.User;
import com.workshop.dto.UserDTO;
import com.workshop.repositories.PostRepository;
import com.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		if(post == null) {
			throw new ObjectNotFoundException("Post not found");
		}
		else return post.get();
	}
	
}
