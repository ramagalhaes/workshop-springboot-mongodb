package com.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshop.domain.Post;
import com.workshop.domain.User;
import com.workshop.dto.AuthorDTO;
import com.workshop.dto.CommentDTO;
import com.workshop.repositories.PostRepository;
import com.workshop.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user1 = new User(null, "Walter Pereira", "walter@gmail.com");
		User user2 = new User(null, "Bob Brown", "bob@gmail.com");
		User user3 = new User(null, "Alex Green", "alex@gmail.com");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		Post post1 = new Post(null, new AuthorDTO(user1), sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para SP, abracos");
		Post post2 = new Post(null, new AuthorDTO(user1), sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!");
		
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("22/03/2018"), new AuthorDTO(user3));
		CommentDTO comment2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(user2));
		CommentDTO comment3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("22/03/2018"), new AuthorDTO(user3));
		
		user1.getPosts().add(post1);
		user1.getPosts().add(post2);
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.save(post1);
		postRepository.save(post2);
		
		userRepository.save(user1);
		
		
	}

}
