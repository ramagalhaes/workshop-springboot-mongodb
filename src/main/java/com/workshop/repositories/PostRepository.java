package com.workshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.workshop.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
