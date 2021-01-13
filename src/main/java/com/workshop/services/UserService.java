package com.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.domain.User;
import com.workshop.dto.UserDTO;
import com.workshop.repositories.UserRepository;
import com.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		if(user == null) {
			throw new ObjectNotFoundException("User not found");
		}
		else return user.get();
	}
	
	public User insert(User user) {	
		return repository.insert(user);	
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User user = findById(obj.getId());
		User updatedData = updateData(user, obj);
		return repository.save(updatedData );
		
	}
	
	private static User updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		return user;
	}
}
