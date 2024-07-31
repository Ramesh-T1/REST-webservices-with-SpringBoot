package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;
import com.in28minutes.rest.webservices.user.UserDaoService;
import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
public class UserResource {
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	/*@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		 User user=service.findOne(id);
		 if(user==null)
			 throw new UserNotFoundException("id:"+id);
		 return user;
	}*/
	
	//Implementing Hateoas
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
	{
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel=EntityModel.of(user);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}

	// Create User or Post method
	@PostMapping("/users")
	public ResponseEntity<User> createUSer(@Valid @RequestBody User user) {
		User savedUser=service.save(user);
		URI Location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(Location).build();
	}
	//Delete User
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		service.deleteById(id);
	}
	

	

}
